package com.cniao.activity;

import android.view.View;

import com.cniao.R;
import com.cniao.widget.CNiaoToolBar;

import butterknife.BindView;

/**
 * Created by 高磊华
 * Time  2017/8/21
 * Describe: 我的订单
 */

public class MyOrdersActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    CNiaoToolBar mToolBar;

    @Override
    protected void init() {
        initToolBar();
    }


    @Override
    protected int getContentResourseId() {
        return R.layout.activity_myorder;
    }

    private void initToolBar() {
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyOrdersActivity.this.finish();
            }
        });
    }
}
