package com.enjoyshop.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.enjoyshop.R;
import com.enjoyshop.utils.PreferencesUtils;

import java.util.Timer;
import java.util.TimerTask;

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
    private int duration = 3;      //倒计时3秒
    Timer timer = new Timer();

    @Override
    protected int getContentResourseId() {

        //必须写在这里,不能写在 init 中.先全屏,再加载试图
//        requestWindowFeature(Window.FEATURE_NO_TITLE);       // 无标题栏
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager
//                .LayoutParams.FLAG_FULLSCREEN);    //全屏

        return R.layout.activity_splash;
    }

    @Override
    protected void init() {

        /**
         * 第二个参数的意思是，当你调用该方法后，该方法必然会调用 TimerTask 类 TimerTask 类 中的 run()
         * 方法，这个参数就是这两者之间的差值，转换成汉语的意思就是说，用户调用 schedule() 方法后，
         * 要等待这么长的时间才可以第一次执行 run() 方法。即推迟多久执行
         *
           第三个参数的意思就是，第一次调用之后，从第二次开始每隔多长的时间调用一次 run() 方法。即执行多久多长时间
         */
        timer.schedule(task, 1000, 1000);
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
    @OnClick(R.id.ll_time)
    public void onClick(View v) {
        timer.cancel();
        jumpActivity();
    }


    private TimerTask task = new TimerTask() {
        @Override
        public void run() {

            runOnUiThread(new Runnable() {      // UI thread
                @Override
                public void run() {
                    duration--;
                    mTvTime.setText(duration + "");
                    if (duration < 2) {
                        timer.cancel();
                        jumpActivity();
                    }
                }
            });

        }
    };

}
