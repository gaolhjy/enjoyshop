package com.enjoyshop.fragment;

import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.enjoyshop.R;
import com.enjoyshop.activity.CreateOrderActivity;
import com.enjoyshop.adapter.ShopCartAdapter;
import com.enjoyshop.bean.MessageEvent;
import com.enjoyshop.bean.ShoppingCart;
import com.enjoyshop.utils.CartShopProvider;
import com.enjoyshop.widget.EnjoyshopToolBar;
import com.enjoyshop.widget.WrapContentLinearLayoutManager;

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

public class ShopCartFragment extends BaseFragment {

    public static final  int    ACTION_EDIT     = 1;
    public static final  int    ACTION_CAMPLATE = 2;
    private static final String TAG             = "CartFragment";

    @BindView(R.id.recycler_view)
    RecyclerView     mRecyclerView;
    @BindView(R.id.checkbox_all)
    CheckBox         mCheckBox;
    @BindView(R.id.txt_total)
    TextView         mTextTotal;
    @BindView(R.id.btn_order)
    Button           mBtnOrder;
    @BindView(R.id.btn_del)
    Button           mBtnDel;
    @BindView(R.id.toolbar)
    EnjoyshopToolBar mToolbar;
    @BindView(R.id.rv_bottom)
    RelativeLayout   mRvBottom;
    @BindView(R.id.ll_empty)
    LinearLayout     mLlEmpty;

    private ShopCartAdapter  mAdapter;
    private CartShopProvider mCartShopProvider;
    private List<ShoppingCart> carts;

    @Override
    protected int getContentResourseId() {
        return R.layout.fragment_shopcart;
    }

    @Override
    protected void init() {
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
    }


    /**
     * 获取数据
     */


    private void showData() {

        carts = mCartShopProvider.getAll();

        if (carts == null) {
            initEmptyView();           //如果数据为空,显示空的试图
            return;
        }

        /**
         * 购物车数据不为空
         */
        mAdapter = new ShopCartAdapter(this,carts);
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
        mAdapter.setNewData(carts);

//        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
//            @Override
//            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                ShoppingCart cart = (ShoppingCart) adapter.getData().get(position);
//                CheckBox checkBox = view.findViewById(R.id.checkbox);
//
//                switch (view.getId()) {
//                    case R.id.checkbox:
//                        checkBox.setChecked(cart.isChecked());
//                        checkListen();
//                        showTotalPrice();
//                        mAdapter.notifyItemChanged(position);
//                        break;
//                }
//            }
//        });

        checkListen();

    }


    /**
     * 思路:
     * 1.先拿到所有数据的长度,然后对所有数据进行遍历
     * 2.判断里面的 isCheck  是不是被选中
     * 3.把所有选中的进行计数  并相加 sum1
     * 4. 如果步骤3中sum1 与总的总数相等,说明全部选中了  反正没有全选中
     */
    public void checkListen() {

        int count = 0;
        int checkNum = 0;
        if (carts != null) {
            count = carts.size();

            for (ShoppingCart cart : carts) {
                if (!cart.isChecked()) {
                    mCheckBox.setChecked(false);
                    break;
                } else {
                    checkNum = checkNum + 1;
                }
            }

            if (count == checkNum) {
                mCheckBox.setChecked(true);
            }
        }
    }


    private void initEmptyView() {
        mRvBottom.setVisibility(View.GONE);
        mLlEmpty.setVisibility(View.VISIBLE);
    }


    @OnClick({R.id.btn_del, R.id.btn_order, R.id.tv_goshop, R.id.checkbox_all})
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
            case R.id.checkbox_all:
                mAdapter.setCheckBox(mCheckBox.isChecked());
                showTotalPrice();
                break;
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden) {
            refData();
        }
    }

    /**
     * 刷新数据
     * <p>
     * fragment是隐藏与显示.生命周期很多没走.
     * 先将数据全部清除,再重新添加(有可能和以前一样,有可能有新数据)
     * 清除的目的,就是为了防止添加了新数据而界面上没展示
     */
    private void refData() {

        List<ShoppingCart> carts = mCartShopProvider.getAll();
        if (carts != null && carts.size() > 0) {
            mLlEmpty.setVisibility(View.GONE);
            mRvBottom.setVisibility(View.VISIBLE);
            showData();
            showTotalPrice();
        } else {
            initEmptyView();
        }

    }

    public void showTotalPrice() {
        float total = getTotalPrice();
        mTextTotal.setText(Html.fromHtml("合计 ￥<span style='color:#eb4f38'>" + total + "</span>"),
                TextView.BufferType.SPANNABLE);
    }

    /**
     * 计算总和
     */

    public boolean isNull() {
        return (carts != null && carts.size() > 0);
    }

    private float getTotalPrice() {

        float sum = 0;
        if (!isNull())
            return sum;

        for (ShoppingCart cart : carts) {
            if (cart.isChecked()) {   //是否勾上去了
                sum += cart.getCount() * cart.getPrice();
            }
        }

        return sum;
    }

}



