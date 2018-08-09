package com.enjoyshop.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.enjoyshop.EnjoyshopApplication;
import com.enjoyshop.R;
import com.enjoyshop.bean.User;
import com.enjoyshop.contants.Contants;
import com.enjoyshop.contants.HttpContants;
import com.enjoyshop.msg.LoginRespMsg;
import com.enjoyshop.utils.DESUtil;
import com.enjoyshop.utils.ToastUtils;
import com.enjoyshop.widget.EnjoyshopToolBar;
import com.enjoyshop.widget.ClearEditText;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by 高磊华
 * Time  2017/8/9
 * Describe: 登录界面
 */

public class LoginActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    EnjoyshopToolBar  mToolBar;
    @BindView(R.id.etxt_phone)
    ClearEditText mEtxtPhone;
    @BindView(R.id.etxt_pwd)
    ClearEditText mEtxtPwd;
    @BindView(R.id.txt_toReg)
    TextView      mTxtToReg;

    @Override
    protected void init() {
        initToolBar();
    }

    @Override
    protected int getContentResourseId() {
        return R.layout.activity_login;
    }

    private void initToolBar() {

        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.this.finish();
            }
        });
    }


    @OnClick({R.id.btn_login, R.id.txt_toReg})
    public void viewclick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                login();   //登录
                break;
            case R.id.txt_toReg:
                Intent intent = new Intent(this, RegActivity.class);
                startActivity(intent);
                break;
        }
    }

    /**
     * 登录
     */
    private void login() {

        String phone = mEtxtPhone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            ToastUtils.showSafeToast(LoginActivity.this,"请输入手机号码");
            return;
        }

        String pwd = mEtxtPwd.getText().toString().trim();
        if (TextUtils.isEmpty(pwd)) {
            ToastUtils.showSafeToast(LoginActivity.this,"请输入密码");
            return;
        }

        Map<String, String> params = new HashMap<>();
        params.put("phone", phone);
        params.put("password", DESUtil.encode(Contants.DES_KEY, pwd));

        OkHttpUtils.post().url(HttpContants.LOGIN).params(params).build().execute(new Callback<LoginRespMsg<User>>() {
            @Override
            public LoginRespMsg<User> parseNetworkResponse(Response response, int id) throws
                    Exception {

                String string = response.body().string();
                LoginRespMsg loginRespMsg = new Gson().fromJson(string, LoginRespMsg.class);
                return loginRespMsg;

            }

            @Override
            public void onError(Call call, Exception e, int id) {
            }

            @Override
            public void onResponse(LoginRespMsg<User> response, int id) {

                EnjoyshopApplication application = EnjoyshopApplication.getInstance();
                application.putUser(response.getData(), response.getToken());
                if (application.getIntent()==null) {
                    setResult(RESULT_OK);
                    finish();
                }else {
                    application.jumpToTargetActivity(LoginActivity.this);
                    finish();
                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
