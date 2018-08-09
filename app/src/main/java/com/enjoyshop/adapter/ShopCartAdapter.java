package com.enjoyshop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.enjoyshop.EnjoyshopApplication;
import com.enjoyshop.R;
import com.enjoyshop.bean.ShoppingCart;
import com.enjoyshop.utils.CartShopProvider;
import com.enjoyshop.utils.GlideUtils;
import com.enjoyshop.utils.LogUtil;
import com.enjoyshop.widget.NumberAddSubView;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by 高磊华
 * Time  2017/8/9
 * Describe: 购物车的适配器
 */

public class ShopCartAdapter extends CommonAdapter<ShoppingCart> implements MultiItemTypeAdapter
        .OnItemClickListener {

    private Context            mContext;
    private List<ShoppingCart> mCarts;
    private CheckBox           mCheckBox;
    private TextView           mTextView;
    private CartShopProvider   mCartShopProvider;


    public ShopCartAdapter(Context context, List<ShoppingCart> cart, CheckBox checkBox,
                           TextView textView) {
        super(context, R.layout.template_cart, cart);

        this.mContext = context;
        this.mCarts = cart;
        this.mTextView = textView;
        this.mCheckBox = checkBox;
        mCartShopProvider = new CartShopProvider(mContext);
        setCheckBox(checkBox);
        setOnItemClickListener(this);
        showTotalPrice();
    }

    @Override
    protected void convert(ViewHolder holder, final ShoppingCart cart, int position) {

        holder.setText(R.id.text_title, cart.getName());
        holder.setText(R.id.text_price, "￥" + cart.getPrice());
        GlideUtils.load(EnjoyshopApplication.sContext, cart.getImgUrl(), (ImageView) holder
                .getView(R.id.iv_view));

        CheckBox checkBox = (CheckBox) holder.getView(R.id.checkbox);
        checkBox.setChecked(cart.isChecked());
        LogUtil.e("是否选中", position + "" + cart.isChecked(), true);

        NumberAddSubView numberAddSubView = (NumberAddSubView) holder.getView(R.id.num_control);
        numberAddSubView.setValue(cart.getCount());

        numberAddSubView.setOnButtonClickListener(new NumberAddSubView.OnButtonClickListener() {
            @Override
            public void onButtonAddClick(View view, int value) {
                cart.setCount(value);
                mCartShopProvider.updata(cart);
                showTotalPrice();
            }

            @Override
            public void onButtonSubClick(View view, int value) {
                cart.setCount(value);
                mCartShopProvider.updata(cart);
                showTotalPrice();
            }
        });
    }


    /**
     * 全选或者全不选
     */
    private void setCheckBox(final CheckBox checkBox) {

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAll_None(checkBox.isChecked());      //需要判断是全选或全不选
                showTotalPrice();
            }
        });
    }

    public void checkAll_None(boolean isChecked) {

        if (!isNull()) {
            return;
        }

        int i = 0;
        for (ShoppingCart cart : mCarts) {
            cart.setIsChecked(isChecked);
            notifyItemChanged(i);
            i++;
        }
    }


    @Override
    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {

        ShoppingCart item = getItem(position);
        item.setIsChecked(!item.isChecked());            //如果选中,点击后就不选    如果没选,就选中
        notifyItemChanged(position);

        /**
         * 还需要监听其他的是否选中.
         * 主要是用于 全选 和 全不选
         */
        checkListen();
        showTotalPrice();
    }


    /**
     * 思路:
     * 1.先拿到所有数据的长度,然后对所有数据进行遍历
     * 2.判断里面的 isCheck  是不是被选中
     * 3.把所有选中的进行计数  并相加 sum1
     * 4. 如果步骤3中sum1 与总的总数相等,说明全部选中了  反正没有全选中
     */
    private void checkListen() {

        int count = 0;
        int checkNum = 0;
        if (mCarts != null) {
            count = mCarts.size();

            for (ShoppingCart cart : mCarts) {
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

    @Override
    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
        return false;
    }


    public ShoppingCart getItem(int position) {
        if (position >= mCarts.size()) {
            return null;
        }
        return mCarts.get(position);
    }


    public void addData(List<ShoppingCart> datas) {
        addData(0, datas);
    }

    public void addData(int position, List<ShoppingCart> list) {

        if (list != null && list.size() > 0) {
            for (ShoppingCart t : list) {
                mCarts.add(position, t);
                notifyItemInserted(position);
            }
        }
    }


    /**
     * 计算总和
     */
    private float getTotalPrice() {

        float sum = 0;
        if (!isNull())
            return sum;

        for (ShoppingCart cart : mCarts) {
            if (cart.isChecked()) {            //是否勾上去了
                sum += cart.getCount() * cart.getPrice();
            }
        }

        Log.e("总价格", "getTotalPrice: " + sum);
        return sum;
    }


    private boolean isNull() {
        return (mCarts != null && mCarts.size() > 0);
    }

    public void showTotalPrice() {
        float total = getTotalPrice();
        mTextView.setText(Html.fromHtml("合计 ￥<span style='color:#eb4f38'>" + total + "</span>"),
                TextView.BufferType.SPANNABLE);
    }

}
