package com.cniao.activity;

import android.content.Intent;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cniao.CNiaoApplication;
import com.cniao.R;
import com.cniao.bean.User;
import com.cniao.contants.Contants;
import com.cniao.contants.HttpContants;
import com.cniao.msg.LoginRespMsg;
import com.cniao.utils.CountTimerView;
import com.cniao.utils.DESUtil;
import com.cniao.utils.LogUtil;
import com.cniao.utils.ToastUtils;
import com.cniao.widget.CNiaoToolBar;
import com.cniao.widget.ClearEditText;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import dmax.dialog.SpotsDialog;
import okhttp3.Call;

/**
 * Created by 高磊华
 * Time  2017/8/12
 * Describe: 接收验证码的注册界面,即注册界面二
 */

public class RegSecondActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    CNiaoToolBar mToolBar;

    @BindView(R.id.txtTip)
    TextView mTxtTip;

    @BindView(R.id.btn_reSend)
    Button mBtnResend;

    @BindView(R.id.edittxt_code)
    ClearEditText mEtCode;

    private String phone;
    private String pwd;
    private String countryCode;

    private EventHandler eventHandler;
    private SpotsDialog  dialog;
    private Gson mGson = new Gson();

    @Override
    protected void init() {

        initToolBar();
        dialog = new SpotsDialog(this);

        phone = getIntent().getStringExtra("phone");
        pwd = getIntent().getStringExtra("pwd");
        countryCode = getIntent().getStringExtra("countryCode");

        String formatedPhone = "+" + countryCode + " " + splitPhoneNum(phone);
        String text = getString(R.string.smssdk_send_mobile_detail) + formatedPhone;
        mTxtTip.setText(Html.fromHtml(text));

        CountTimerView timerView = new CountTimerView(mBtnResend);    //倒计时
        timerView.start();

        initSms();
    }

    @Override
    protected int getContentResourseId() {
        return R.layout.activity_reg_second;
    }

    private void initSms() {

        // 创建EventHandler对象
        eventHandler = new EventHandler() {
            public void afterEvent(int event, int result, Object data) {

                if (result == SMSSDK.RESULT_COMPLETE) {
                    //回调完成
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        //提交验证码成功
                        doReg();
                        //                        dialog.setMessage("正在提交注册信息");
                        //                        dialog.show();
                    }
                } else if (data instanceof Throwable) {
                    Throwable throwable = (Throwable) data;
                    String msg = throwable.getMessage();
                    ToastUtils.showSafeToast(RegSecondActivity.this, msg);
                } else {
                    ((Throwable) data).printStackTrace();
                }
            }
        };

        // 注册监听器
        SMSSDK.registerEventHandler(eventHandler);
    }

    /**
     * 标题栏 完成
     */
    private void initToolBar() {
        mToolBar.setRightButtonOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitCode();
            }
        });
    }

    /**
     * 提交验证码
     */
    private void submitCode() {

        String vCode = mEtCode.getText().toString().trim();    //验证码

        if (TextUtils.isEmpty(vCode)) {
            ToastUtils.showSafeToast(RegSecondActivity.this, "请填写验证码");
            return;
        }

        SMSSDK.submitVerificationCode(countryCode, phone, vCode);
    }


    /**
     * 注册.与后台交互
     */
    private void doReg() {

        String pwdEncode = DESUtil.encode(Contants.DES_KEY, pwd);
        String url = HttpContants.REG + "?phone=" + phone + "&password=" + pwdEncode;
        OkHttpUtils.post().url(url).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                LogUtil.e("注册", "失败", true);
            }

            @Override
            public void onResponse(String response, int id) {
                LogUtil.e("注册", "成功", true);

                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }

                LoginRespMsg<User> loginRespMsg = mGson.fromJson(response, new
                        TypeToken<LoginRespMsg<User>>() {
                        }.getType());
                CNiaoApplication application = CNiaoApplication.getInstance();
                application.putUser(loginRespMsg.getData(), loginRespMsg.getToken());

                startActivity(new Intent(RegSecondActivity.this, MainActivity.class));
                finish();

            }
        });


    }

    /**
     * 分割电话号码
     */
    private String splitPhoneNum(String phone) {
        StringBuilder builder = new StringBuilder(phone);
        builder.reverse();
        for (int i = 4, len = builder.length(); i < len; i += 5) {
            builder.insert(i, ' ');
        }
        builder.reverse();
        return builder.toString();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eventHandler);
    }
}
