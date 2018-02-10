package com.cniao.fragment;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.cniao.CNiaoApplication;
import com.cniao.R;
import com.cniao.activity.GoodsListActivity;
import com.cniao.activity.SearchActivity;
import com.cniao.adapter.DividerItemDecortion;
import com.cniao.adapter.HomeCatgoryAdapter;
import com.cniao.bean.BannerBean;
import com.cniao.bean.Campaign;
import com.cniao.bean.HomeCampaignBean;
import com.cniao.contants.Contants;
import com.cniao.contants.HttpContants;
import com.cniao.widget.CNiaoToolBar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import okhttp3.Call;


/**
 * <pre>
 *     author : 高磊华
 *     e-mail : 984992087@qq.com
 *     time   : 2017/08/02
 *     desc   : 首页fragment
 *     version: 1.1
 * </pre>
 */
public class HomeFragment extends BaseFragment2 implements View.OnClickListener {

    private Banner       mBanner;
    private RecyclerView mRecyclerView;
    private CNiaoToolBar mToolBar;

    private HomeCatgoryAdapter mAdatper;
    private List<String>           images = new ArrayList<>();
    private List<String>           titles = new ArrayList<>();
    private List<HomeCampaignBean> datas  = new ArrayList<>();
    private Gson                   gson   = new Gson();


    @Override
    protected void init(View view) {
        initView(view);
        requestBannerData();     //请求轮播图数据
        requestCampaignData();     //请求商品详情数据
    }

    @Override
    protected int getContentResourseId() {
        return R.layout.fragment_home;
    }


    @Override
    public void onStart() {
        super.onStart();
        mBanner.startAutoPlay();
    }


    private void initView(View view) {

        mToolBar = (CNiaoToolBar) view.findViewById(R.id.toolbar);
        mToolBar.setOnClickListener(this);
        mBanner = (Banner) view.findViewById(R.id.banner);

        //设置banner样式
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
        mBanner.setImageLoader(new GlideImageLoader());

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
    }


    /**
     * 轮播图数据
     */
    private void setBannerData() {
        //设置图片集合
        mBanner.setImages(images);
        //设置标题集合（当banner样式有显示title时）
        mBanner.setBannerTitles(titles);

        //设置指示器位置（当banner模式中有指示器时）
        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        mBanner.start();
    }


    /**
     * 首页商品数据
     */
    private void setRecyclerViewData() {

        mAdatper = new HomeCatgoryAdapter(getContext(), datas);

        mAdatper.setOnCampaignClickListener(new HomeCatgoryAdapter.OnCampaignClickListener() {
            @Override
            public void onClick(View view, Campaign campaign) {

                Intent intent = new Intent(getContext(), GoodsListActivity.class);
                intent.putExtra(Contants.COMPAINGAIN_ID, campaign.getId());
                startActivity(intent);
            }
        });


        mRecyclerView.setAdapter(mAdatper);
        mRecyclerView.addItemDecoration(new DividerItemDecortion());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

    }


    /**
     * 请求轮播图的数据
     */
    private void requestBannerData() {

        OkHttpUtils.get().url(HttpContants.HOME_BANNER_URL)
                .addParams("type", "1")
                .build().execute(new StringCallback() {

                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {

                        Type collectionType = new TypeToken<Collection<BannerBean>>() {
                        }.getType();
                        Collection<BannerBean> enums = gson.fromJson(response, collectionType);
                        Iterator<BannerBean> iterator = enums.iterator();
                        while (iterator.hasNext()) {
                            BannerBean bean = iterator.next();
                            titles.add(bean.getName());
                            images.add(bean.getImgUrl());
                        }

                        setBannerData();
                    }
                });
    }


    /**
     * 商品分类数据
     */
    private void requestCampaignData() {

        OkHttpUtils.get().url(HttpContants.HOME_CAMPAIGN_URL)
                .addParams("type", "1")
                .build().execute(new StringCallback() {

                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {

                        Type collectionType = new TypeToken<Collection<HomeCampaignBean>>() {
                        }.getType();
                        Collection<HomeCampaignBean> enums = gson.fromJson(response,
                                collectionType);
                        Iterator<HomeCampaignBean> iterator = enums.iterator();
                        while (iterator.hasNext()) {
                            HomeCampaignBean bean = iterator.next();
                            datas.add(bean);
                        }

                        setRecyclerViewData();
                    }
                });
    }


    @Override
    public void onStop() {
        super.onStop();
        mBanner.stopAutoPlay();
    }

    //跳转到搜索界面
    @Override
    public void onClick(View v) {
        startActivity(new Intent(getContext(), SearchActivity.class));
    }

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(CNiaoApplication.sContext).load(path).into(imageView);
        }
    }
}
