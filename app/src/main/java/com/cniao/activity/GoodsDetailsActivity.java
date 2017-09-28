package com.cniao.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.webkit.JavascriptInterface;

import com.cniao.CNiaoApplication;
import com.cniao.R;
import com.cniao.bean.HotGoods;
import com.cniao.bean.User;
import com.cniao.contants.UrlContants;
import com.cniao.helper.UIHelper;
import com.cniao.utils.CartShopProvider;
import com.cniao.utils.LogUtil;
import com.cniao.utils.ToastUtils;
import com.cniao.widget.CNiaoToolBar;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.tencent.qq.QQ;
import okhttp3.Call;

/**
 * Created by 高磊华
 * Time  2017/8/9
 * Describe: 商品详情
 */

public class GoodsDetailsActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.toolbar)
    CNiaoToolBar mToolBar;
    @BindView(R.id.webView)
    WebView      mWebView;

    private HotGoods.ListBean goodsBean;
    private WebAppInterface   mAppInterfce;
    private CartShopProvider  cartProvider;

    @Override
    protected int getContentResourseId() {
        return R.layout.activity_goods_detail;
    }

    @Override
    protected void init() {

        //接收数据
        Bundle bundle = getIntent().getExtras();
        goodsBean = (HotGoods.ListBean) bundle.getSerializable("itemClickGoods");
        if (goodsBean == null) {
            finish();
        }

        cartProvider = new CartShopProvider(this);

        LogUtil.e("跳转后数据", goodsBean.getName() + goodsBean.getPrice(), true);

        initToolBar();

        initData();

    }


    private void initData() {

        final WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBlockNetworkImage(false);
        webSettings.setAppCacheEnabled(true);
        mWebView.loadUrl(UrlContants.WARES_DETAIL);

        mAppInterfce = new WebAppInterface(this);
        mWebView.addJavascriptInterface(mAppInterfce, "appInterface");

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String s) {
                webView.loadUrl(s);
                return true;
            }

            @Override
            public void onPageFinished(WebView webView, String s) {
                super.onPageFinished(webView, s);
                //整个页面加载完后,才能调用这个方法
                mAppInterfce.showDetail();
            }
        });

    }

    /**
     * 初始化标题栏
     */
    private void initToolBar() {

        mToolBar.setNavigationOnClickListener(this);
        mToolBar.setRightButtonText("分享");

        mToolBar.setRightButtonOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View inflate = UIHelper.showShareDialogOnBottom(GoodsDetailsActivity.this);
//                inflate.findViewById(R.id.weixin).setOnClickListener(this);
//                inflate.findViewById(R.id.wxcircle).setOnClickListener(this);
//                inflate.findViewById(R.id.sina).setOnClickListener(this);
                inflate.findViewById(R.id.qq).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showShare(QQ.NAME);
                    }
                });
//                inflate.findViewById(R.id.qzone).setOnClickListener(this);


//                UIHelper.showShareDialogOnBottom(GoodsDetailsActivity.this);
//                inflate.findViewById(R.id.weixin).setOnClickListener(this);
//                inflate.findViewById(R.id.wxcircle).setOnClickListener(this);
//                inflate.findViewById(R.id.sina).setOnClickListener(this);
//                inflate.findViewById(R.id.qq).setOnClickListener(this);
//                inflate.findViewById(R.id.qzone).setOnClickListener(this);

            }
        });
    }


    /**
     * 分享
     */
    private void showShare(String platform) {

        OnekeyShare oks = new OnekeyShare();
        //指定分享的平台，如果为空，还是会调用九宫格的平台列表界面
        if (platform != null) {
            oks.setPlatform(platform);
        }

        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // 分享时Notification的图标和文字  2.5.9以后的版本不     调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle("来自轻松购的分享");
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://www.cniao5.com");
        // text是分享文本，所有平台都需要这个字段
        oks.setText(goodsBean.getName());
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //        oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片       //很少有本地图片的
        oks.setImageUrl(goodsBean.getImgUrl());

        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment(goodsBean.getName());
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("来自轻松购的分享");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://www.cniao5.com");

        // 启动分享GUI
        oks.show(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.toolbar:
                this.finish();
                break;
//            case R.id.weixin:
//                showShare(Wechat.NAME);
//                break;
//            case R.id.wxcircle:
//                showShare(WechatMoments.NAME);
//                break;
//            case R.id.sina:
//                showShare(SinaWeibo.NAME);
//                break;
//            case R.id.qq:
//                showShare(QQ.NAME);
//                break;
//            case R.id.qzone:
//                showShare(QZone.NAME);
//                break;
        }
    }


    class WebAppInterface {

        private Context context;

        public WebAppInterface(Context context) {
            this.context = context;
        }

        @JavascriptInterface
        public void showDetail() {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mWebView.loadUrl("javascript:showDetail(" + goodsBean.getId() + ")");
                }
            });
        }

        @JavascriptInterface
        public void buy(long id) {
            cartProvider.put(goodsBean);
            ToastUtils.setGravity(Gravity.CENTER, 0, 0);
            ToastUtils.showShortSafe("已添加到购物车");
        }


        /**
         * 这里和视频 源代码有出入.
         * 已经修改了.之前是 收藏  加入购物车.
         * 现在变成了 加入购物车  立即购物
         */
        @JavascriptInterface
        public void addToCart(long id) {
            addToFavorite();
        }
    }


    private void addToFavorite() {

        User user = CNiaoApplication.getInstance().getUser();

        if (user == null) {
            startActivity(new Intent(this, LoginActivity.class), true);
        }

        Long userId = CNiaoApplication.getInstance().getUser().getId();

        String url = UrlContants.FAVORITE_CREATE + "?user_id=" + userId + "&ware_id=" + goodsBean
                .getId();


        OkHttpUtils.post().url(UrlContants.FAVORITE_CREATE).build().execute(new StringCallback() {

            @Override
            public void onError(Call call, Exception e, int id) {
                LogUtil.e("收藏", "收藏失败" + e, true);
            }

            @Override
            public void onResponse(String response, int id) {
                ToastUtils.setGravity(Gravity.CENTER, 0, 0);
                ToastUtils.showShortSafe("已添加到收藏夹");
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWebView != null) {
            mWebView.destroy();
        }
    }
}
