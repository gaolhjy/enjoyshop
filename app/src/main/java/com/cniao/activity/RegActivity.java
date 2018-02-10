package com.cniao.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.cniao.R;
import com.cniao.utils.LogUtil;
import com.cniao.utils.ToastUtils;
import com.cniao.widget.CNiaoToolBar;
import com.cniao.widget.ClearEditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * Created by 高磊华
 * Time  2017/8/12
 * Describe: 注册activity
 */

public class RegActivity extends BaseActivity {

    private EventHandler eventHandler;
    @BindView(R.id.toolbar)
    CNiaoToolBar  mToolBar;
    @BindView(R.id.txtCountry)
    TextView      mTxtCountry;
    @BindView(R.id.txtCountryCode)
    TextView      mTxtCountryCode;
    @BindView(R.id.edittxt_phone)
    ClearEditText mEtxtPhone;
    @BindView(R.id.edittxt_pwd)
    ClearEditText mEtxtPwd;

    @Override
    protected int getContentResourseId() {
        return R.layout.activity_reg;
    }


    @Override
    protected void init() {
        initToolBar();
        initSms();
    }


    private void initSms() {

        // 创建EventHandler对象
        eventHandler = new EventHandler() {
            public void afterEvent(int event, int result, Object data) {

                if (result == SMSSDK.RESULT_COMPLETE) {
                    //回调完成
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        //提交验证码成功
                    } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        //获取验证码成功
                        afterVerificationCodeRequested((Boolean) data);
                    } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                        //返回支持发送验证码的国家列表
                    }
                } else if (data instanceof Throwable) {
                    Throwable throwable = (Throwable) data;
                    String msg = throwable.getMessage();
                    ToastUtils.showSafeToast(RegActivity.this,msg);
                } else {
                    ((Throwable) data).printStackTrace();
                }
            }
        };

        // 注册监听器
        SMSSDK.registerEventHandler(eventHandler);
    }


    /**
     * 跳转到注册界面二
     *
     * @param data
     */

    private void afterVerificationCodeRequested(Boolean data) {

        LogUtil.e("注册界面", "代码是否执行", true);

        String phone = mEtxtPhone.getText().toString().trim().replaceAll("\\s*", "");
        String code = mTxtCountryCode.getText().toString().trim();
        String pwd = mEtxtPwd.getText().toString().trim();

        if (code.startsWith("+")) {
            code = code.substring(1);
        }

        Intent intent = new Intent(this, RegSecondActivity.class);
        intent.putExtra("phone", phone);
        intent.putExtra("pwd", pwd);
        intent.putExtra("countryCode", code);

        startActivity(intent);
    }


    private void initToolBar() {

        mToolBar.setRightButtonOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getCode();
            }
        });
    }

    /**
     * 获取手机号 密码等信息
     */
    private void getCode() {

        String phone = mEtxtPhone.getText().toString().trim().replaceAll("\\s*", "");
        String code = mTxtCountryCode.getText().toString().trim();
        String pwd = mEtxtPwd.getText().toString().trim();

        checkPhoneNum(phone, code);

        SMSSDK.getVerificationCode(code, phone);

    }

    /**
     * 对手机号进行验证
     */
    private void checkPhoneNum(String phone, String code) {
        if (code.startsWith("+")) {
            code = code.substring(1);
        }

        if (TextUtils.isEmpty(phone)) {
            ToastUtils.showSafeToast(RegActivity.this,"请输入手机号码");
            return;
        }

        if (code == "86") {
            if (phone.length() != 11) {
                ToastUtils.showSafeToast(RegActivity.this,"手机号码长度不对");
                return;
            }
        }

        String rule = "^1(3|5|7|8|4)\\d{9}";
        Pattern p = Pattern.compile(rule);
        Matcher m = p.matcher(phone);

        if (!m.matches()) {
            ToastUtils.showSafeToast(RegActivity.this,"您输入的手机号码格式不正确");
            return;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eventHandler);
    }
}

