package com.cniao.fragment;

import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cniao.R;
import com.cniao.activity.CreateOrderActivity;
import com.cniao.adapter.ShopCartAdapter;
import com.cniao.bean.MessageEvent;
import com.cniao.bean.ShoppingCart;
import com.cniao.utils.CartShopProvider;
import com.cniao.utils.LogUtil;
import com.cniao.widget.CNiaoToolBar;
import com.cniao.widget.WrapContentLinearLayoutManager;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * <pre>
 *     author : 高磊华
 *     e-mail : 984992087@qq.com
 *     time   : 2017/08/09
 *     desc   : 购物车fragment
 *     version: 1.0
 * </pre>
 */

public class ShopCartFragment extends BaseFragment implements View.OnClickListener {

    public static final  int    ACTION_EDIT     = 1;
    public static final  int    ACTION_CAMPLATE = 2;
    private static final String TAG             = "CartFragment";

    @BindView(R.id.recycler_view)
    RecyclerView   mRecyclerView;
    @BindView(R.id.checkbox_all)
    CheckBox       mCheckBox;
    @BindView(R.id.txt_total)
    TextView       mTextTotal;
    @BindView(R.id.btn_order)
    Button         mBtnOrder;
    @BindView(R.id.btn_del)
    Button         mBtnDel;
    @BindView(R.id.toolbar)
    CNiaoToolBar   mToolbar;
    @BindView(R.id.rv_bottom)
    RelativeLayout mRvBottom;
    @BindView(R.id.ll_empty)
    LinearLayout   mLlEmpty;

    private ShopCartAdapter  mAdapter;
    private CartShopProvider mCartShopProvider;

    @Override
    protected int getContentResourseId() {
        return R.layout.fragment_shopcart;
    }

    @Override
    protected void init() {
        LogUtil.e("生命周期", "ShopCartFragment", true);
        mCartShopProvider = new CartShopProvider(getContext());
        changeToolbar();
        showData();
    }


    /**
     * 改变标题栏
     */
    private void changeToolbar() {
        mToolbar.hideSearchView();
        mToolbar.showTitleView();
        mToolbar.setTitle(R.string.cart);
        mToolbar.getRightButton().setVisibility(View.VISIBLE);
        mToolbar.setRightButtonText("编辑");
        mToolbar.getRightButton().setOnClickListener(this);
        mToolbar.getRightButton().setTag(ACTION_EDIT);
    }


    /**
     * 获取数据
     */
    private void showData() {

        List<ShoppingCart> carts = mCartShopProvider.getAll();

        if (carts == null) {
            initEmptyView();           //如果数据为空,显示空的试图
            return;
        }

        /**
         * 购物车数据不为空
         */
        mAdapter = new ShopCartAdapter(getContext(), carts, mCheckBox, mTextTotal);
        mRecyclerView.setAdapter(mAdapter);
        //recyclerView本身存在一个bug,在删 添加数据同时进行时,会报错:
        // java.lang.IndexOutOfBoundsException: Inconsistency detected. Invalid view holder
        // adapter positionViewHolder{42319ed8 position=1 id=-1, oldPos=0, pLpos:0 scrap
        // tmpDetached no parent}
        //需要 重写LinearLayoutManager
        mRecyclerView.setLayoutManager(new WrapContentLinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL));

    }

    private void initEmptyView() {
        mRvBottom.setVisibility(View.GONE);
        mLlEmpty.setVisibility(View.VISIBLE);
    }


    @OnClick({R.id.btn_del, R.id.btn_order, R.id.tv_goshop})
    public void viewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_del:
                //                mAdapter.delCart();
                break;
            case R.id.btn_order:
                Intent intent = new Intent(getContext(), CreateOrderActivity.class);
                startActivity(intent, true);
                break;
            case R.id.tv_goshop:      //如果没有商品时
                mLlEmpty.setVisibility(View.GONE);
                //跳转到homeFragment中

                //不能这样使用.replace也不行.会出现界面的重叠

                //                getActivity().getSupportFragmentManager()
                //                        .beginTransaction()
                //                        .add(R.id.realtabcontent, new HomeFragment())
                //                        .commit();

                EventBus.getDefault().post(new MessageEvent(0));

                break;
        }
    }


    @Override
    public void onClick(View view) {
        Toast.makeText(getContext(), "就要点你", Toast.LENGTH_SHORT).show();
    }


    /**
     * 刷新数据
     * <p>
     * fragment是隐藏与显示.生命周期很多没走.
     * 需要切换到购物车fragment后,进行刷新
     * <p>
     * 先将数据全部清除,再重新添加(有可能和以前一样,有可能有新数据)
     * 清除的目的,就是为了防止添加了新数据而界面上没展示
     */
    public void refData() {

        List<ShoppingCart> carts = mCartShopProvider.getAll();
        if (carts != null && carts.size() > 0) {
            mLlEmpty.setVisibility(View.GONE);
            mRvBottom.setVisibility(View.VISIBLE);
            showData();
            mAdapter.showTotalPrice();
        } else {
            initEmptyView();
            return;
        }


        /**
         * 不能按照下面的逻辑写.
         * 因为第二次进入购物车界面时,fragment生命周期没有走 init方法.
         * 如果一开始购物车为空,按照showData()的逻辑,adapter是没有初始化的,即mAdapter为空
         * 即使后面再添加商品到购物车中,因为没有执行 init() 方法,而init()方法内的 show() 方法自然也不会执行.此时mAdpter依旧为空
         * 一直都在执行else中的逻辑
         * 所以需要根据carts的值来判断
         */

        //        //有数据
        //        if (mAdapter != null && mAdapter.getDatas() != null) {
        //            mAdapter.getDatas().clear();
        //            List<ShoppingCart> carts = mCartShopProvider.getAll();
        //            mAdapter.addData(carts);
        //            mAdapter.showTotalPrice();
        //        }
        //        //没有数据
        //        else {
        //            initEmptyView();
        //            return;
        //        }


    }

}



