package com.cniao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;

import com.cniao.CNiaoApplication;
import com.cniao.R;
import com.cniao.adapter.HistorySearchAdapter;
import com.cniao.adapter.HotSearchAdapter;
import com.cniao.entity.SearchHistory;
import com.cniao.greendao.SearchHistoryDao;
import com.cniao.utils.ToastUtils;
import com.cniao.widget.ClearEditText;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 高磊华
 * Time  2017/8/22
 * Describe: 搜索界面
 */

public class SearchActivity extends BaseActivity {

    public final static int SEARCH_HISTORY_MAX = 100;

    @BindView(R.id.edittxt_phone)
    ClearEditText mEditText;
    @BindView(R.id.hot_search_ry)
    RecyclerView  mHotSearchView;
    @BindView(R.id.history_search_ry)
    RecyclerView  mHistorySearchView;

    private List<String>     hotSearchData;
    private List<String>     historySearchData;
    private SearchHistoryDao searchDataDao;

    private HotSearchAdapter     mHotSearchAdapter;
    private HistorySearchAdapter mHistorySearchAdapter;


    @Override
    protected int getContentResourseId() {
        return R.layout.activity_search;
    }


    @Override
    protected void init() {

    }


    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    @OnClick(R.id.gosearch)
    public void search(View view) {

        String content = mEditText.getText().toString().trim();
        if (TextUtils.isEmpty(content)) {
            ToastUtils.setGravity(Gravity.CENTER, 0, 0);
            ToastUtils.showShortSafe("还没输入您想搜索的宝贝呢");
            return;
        }

        //1.发起网络请求,进行搜索   ---这里没有接口,不写
        //2.将搜索的结果保存到历史搜索中
        doData(content);

    }


    /**
     * 初始化数据
     */
    private void initData() {

        searchDataDao = CNiaoApplication.getDaoInstant().getSearchHistoryDao();

        hotSearchData = new ArrayList<>();
        historySearchData = new ArrayList<>();

        getHotData();
        getHistorydata(null);

    }


    /**
     * 热门搜索
     */
    private void getHotData() {

        hotSearchData.add("华为手机");
        hotSearchData.add("玫瑰花");
        hotSearchData.add("移动硬盘");
        hotSearchData.add("android高级进阶");
        hotSearchData.add("蚕丝被");

        mHotSearchView.setLayoutManager(new GridLayoutManager(this, 3));
        mHotSearchAdapter = new HotSearchAdapter(this, hotSearchData);
        mHotSearchView.setAdapter(mHotSearchAdapter);

        mHotSearchAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {

                String content = hotSearchData.get(position);
                doData(content);

            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int
                    position) {
                return false;
            }
        });

    }


    /**
     * 从数据库获取到的 所有历史搜索记录
     */
    private List<SearchHistory> getAllHistory() {
        List<SearchHistory> searchHistories = searchDataDao.queryBuilder().orderAsc
                (SearchHistoryDao.Properties.Order).build().list();
        return searchHistories;
    }

    /**
     * 历史搜索
     * 必须进行判断是不是本来就停留在这个界面进行操作,还是初始化进入
     * 要不然,会重复添加数据
     */
    private void getHistorydata(String content) {

        List<SearchHistory> datas = getAllHistory();

        mHistorySearchView.setLayoutManager(new LinearLayoutManager(this));
        mHistorySearchAdapter = new HistorySearchAdapter(this, historySearchData);
        mHistorySearchView.setAdapter(mHistorySearchAdapter);

        if (datas != null && datas.size() > 0) {
            for (int i = 0; i < datas.size(); i++) {
                historySearchData.add(datas.get(i).getSearchContent());
                mHistorySearchAdapter.notifyDataSetChanged();
            }
        }

        mHistorySearchAdapter.setOnItemClickListener(new MultiItemTypeAdapter
                .OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int
                    position) {

                String content = historySearchData.get(position);
                doData(content);

            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int
                    position) {
                return false;
            }
        });

    }


    /**
     * 操作数据库数据
     */
    private void doData(String content) {

        List<SearchHistory> datas = searchDataDao.queryBuilder().build().list();     //历史搜索记录

        //有历史数据
        if (datas != null && datas.size() > 0) {
            datas = searchDataDao.queryBuilder().where(SearchHistoryDao.Properties.SearchContent
                    .eq(content)).build().list();

            //现在操作的一个(输入、热门、历史)没有与本地数据库中存在重复的
            if (datas == null || datas.size() == 0) {
                //匹配的结果与 限制的最大值进行比较.
                datas = searchDataDao.queryBuilder().where(SearchHistoryDao.Properties.Order.ge
                        (SEARCH_HISTORY_MAX)).build().list();    //ge ≥

                //如果匹配后超过了最大值
                if (datas != null && datas.size() > 0) {
                    searchDataDao.deleteInTx(datas);
                }
                //没有超过最大值
                datas = searchDataDao.queryBuilder().build().list();
                for (SearchHistory data : datas) {
                    //把所有的都进行+1  相当于往后移动一格.移动一格后,实际上就是把第0格空出来了
                    data.setOrder(data.getOrder() + 1);
                    searchDataDao.update(data);
                }

                SearchHistory searchHistory = new SearchHistory();
                searchHistory.setSearchContent(content);
                searchHistory.setOrder(1);
                searchDataDao.insert(searchHistory);
            }

            //有重复的
            else {

                SearchHistory searchHistory = datas.get(0);
                datas = searchDataDao.queryBuilder().where(SearchHistoryDao.Properties.Order.lt
                        (searchHistory.getOrder()))
                        //lt  小于
                        .build().list();
                for (SearchHistory data : datas) {
                    data.setOrder(searchHistory.getOrder() + 1);
                    searchDataDao.update(data);
                }
                searchHistory.setOrder(1);
                searchDataDao.update(searchHistory);
            }
        }

        //没有历史数据
        else {
            SearchHistory searchHistory = new SearchHistory();
            searchHistory.setSearchContent(content);
            searchHistory.setOrder(1);
            searchDataDao.insert(searchHistory);
        }

        mHotSearchAdapter.notifyDataSetChanged();
        mHistorySearchAdapter.notifyDataSetChanged();


        Bundle bundle=new Bundle();
        bundle.putString("search",content);
        Intent intent=new Intent(SearchActivity.this, SearchResultActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);     //跳转到搜索结果界面

    }

}
