package com.cniao.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.cniao.R;
import com.cniao.utils.PreferencesUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by:高磊华
 * Email: 984992087@qq.com
 * Time:  2017/8/22
 * Describe:欢迎界面
 * 无标题栏和全屏必须写在 setContentView前面
 */

public class SplashActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.tv_time)
    TextView mTvTime;
    private              int duration = 3;      //倒计时3秒
    private static final int STEP     = 1000;
    private static final int ISTIME   = 0;


    @Override
    protected int getContentResourseId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void init() {
        mHandler.sendEmptyMessageDelayed(0, STEP);
    }

    /**
     * 必须重写base中的setStatusBar方法.要不然用继承父类的沉浸式状态栏
     */
    @Override
    protected void setStatusBar() {
        //里面什么东西都没有
    }

    /**
     * 界面的跳转
     */
    private void jumpActivity() {

        boolean isFirst = PreferencesUtils.getBoolean(SplashActivity.this, "isFirst", true);
        //默认为第一次

        if (isFirst) {
            PreferencesUtils.putBoolean(SplashActivity.this, "isFirst", false);
            startActivity(new Intent(SplashActivity.this, GuideActivity.class));
        } else {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
        }

        finish();
    }

    /**
     * 如果点击了,停止倒计时,直接跳转
     */
    @OnClick(R.id.tv_time)
    public void onClick(View v) {
        mHandler.removeMessages(0);
        jumpActivity();
    }


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            duration--;
            if (duration > ISTIME) {
                mTvTime.setText(duration + "秒跳过");
                mHandler.sendEmptyMessageDelayed(0, STEP);
            } else {
                mHandler.removeMessages(0);
                jumpActivity();
            }
        }
    };

}
