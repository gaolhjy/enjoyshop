package com.cniao.activity;

import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.cniao.R;
import com.cniao.bean.MessageEvent;
import com.cniao.bean.Tab;
import com.cniao.fragment.CategoryFragment;
import com.cniao.fragment.HomeFragment;
import com.cniao.fragment.HotFragment;
import com.cniao.fragment.MineFragment;
import com.cniao.fragment.ShopCartFragment;
import com.cniao.utils.LogUtil;
import com.cniao.utils.ToastUtils;
import com.cniao.widget.FragmentTabHost;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 高磊华
 * Time  2017/8/2
 * Describe:整个app的主入门
 */

public class MainActivity extends BaseActivity {

    private FragmentTabHost mTabhost;
    private LayoutInflater  mInflater;
    private List<Tab> mTabs = new ArrayList<>();
    private ShopCartFragment shopCartFragment;

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void init() {
        initTab();
    }

    @Override
    protected int getContentResourseId() {
        return R.layout.activity_main;
    }

    private void initTab() {

        Tab tab_home = new Tab(HomeFragment.class, R.string.home, R.drawable.selector_icon_home);
        Tab tab_hot = new Tab(HotFragment.class, R.string.hot, R.drawable.selector_icon_hot);
        Tab tab_category = new Tab(CategoryFragment.class, R.string.catagory, R.drawable
                .selector_icon_category);
        Tab tab_shop = new Tab(ShopCartFragment.class, R.string.cart, R.drawable
                .selector_icon_cart);
        Tab tab_mine = new Tab(MineFragment.class, R.string.mine, R.drawable.selector_icon_mine);

        mTabs.add(tab_home);
        mTabs.add(tab_hot);
        mTabs.add(tab_category);
        mTabs.add(tab_shop);
        mTabs.add(tab_mine);

        mInflater = LayoutInflater.from(this);
        mTabhost = (FragmentTabHost) this.findViewById(android.R.id.tabhost);
        mTabhost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        for (Tab tab : mTabs) {
            TabHost.TabSpec tabSpec = mTabhost.newTabSpec(getString(tab.getTitle()));
            tabSpec.setIndicator(buildIndicator(tab));
            mTabhost.addTab(tabSpec, tab.getFragment(), null);
        }


        /**
         * 因为涉及到fragment的生命周期.所以每次切换到购物车时,需要重新刷新数据
         */
        mTabhost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                LogUtil.e("生命周期", "1111111111", true);
                if (getString(R.string.cart).equals(tabId)) {
                    refData();
                }
            }
        });

        mTabhost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
        mTabhost.setCurrentTab(0);           //默认选中第0个

    }

    private void refData() {

        if (shopCartFragment == null) {
            LogUtil.e("生命周期", "22222222", true);
            Fragment fragment = getSupportFragmentManager().findFragmentByTag(getString(R.string
                    .cart));

            if (fragment != null) {
                LogUtil.e("生命周期", "4444444444", true);
                shopCartFragment = (ShopCartFragment) fragment;
                shopCartFragment.refData();
            }
        } else {
            LogUtil.e("生命周期", "3333333333", true);
            shopCartFragment.refData();
        }

    }

    private View buildIndicator(Tab tab) {

        View view = mInflater.inflate(R.layout.tab_indicator, null);
        ImageView img = (ImageView) view.findViewById(R.id.icon_tab);
        TextView text = (TextView) view.findViewById(R.id.txt_indicator);

        img.setImageResource(tab.getIcon());
        text.setText(tab.getTitle());

        return view;
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        if (event.getType() == 0) {
            mTabhost.setCurrentTab(1);
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    //控制物理返回键
    // 用来计算返回键的点击间隔时间
    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - exitTime > 2000) {
                ToastUtils.showSafeToast(MainActivity.this, "再点一次退出轻松购");
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
