package com.enjoyshop.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.enjoyshop.R;
import com.enjoyshop.utils.LogUtil;
import com.enjoyshop.utils.ToastUtils;
import com.enjoyshop.widget.EnjoyshopToolBar;
import com.enjoyshop.widget.ClearEditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;


/**
 * Created by 高磊华
 * Time  2017/8/12
 * Describe: 注册activity
 */

public class RegActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    EnjoyshopToolBar  mToolBar;
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

}

