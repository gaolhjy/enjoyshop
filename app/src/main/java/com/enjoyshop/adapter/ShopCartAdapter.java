package com.enjoyshop.adapter;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.enjoyshop.EnjoyshopApplication;
import com.enjoyshop.R;
import com.enjoyshop.bean.ShoppingCart;
import com.enjoyshop.fragment.ShopCartFragment;
import com.enjoyshop.utils.CartShopProvider;
import com.enjoyshop.utils.GlideUtils;
import com.enjoyshop.widget.NumberAddSubView;

import java.util.List;

/**
 * Created by 高磊华
 * Time  2017/8/9
 * Describe: 购物车的适配器
 */

public class ShopCartAdapter extends BaseQuickAdapter<ShoppingCart, BaseViewHolder> {

    private List<ShoppingCart> mCarts;
    private CartShopProvider   mCartShopProvider;
    private ShopCartFragment   mShopCartFragment;

    public ShopCartAdapter(ShopCartFragment fragment, List<ShoppingCart> cart) {

        super(R.layout.template_cart);
        this.mShopCartFragment = fragment;
        this.mCarts = cart;

        mCartShopProvider = new CartShopProvider(mShopCartFragment.getContext());

        mShopCartFragment.showTotalPrice();
    }


    /**
     * 全选或者全不选
     *
     * @param checked
     */
    public void setCheckBox(boolean checked) {
        checkAll_None(checked);
    }

    private void checkAll_None(boolean isChecked) {

        if (!mShopCartFragment.isNull()) {
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
    protected void convert(BaseViewHolder holder, final ShoppingCart cart) {

        holder.setText(R.id.text_title, cart.getName())
                .setText(R.id.text_price, "￥" + cart.getPrice())
                .setChecked(R.id.checkbox, cart.isChecked());

        GlideUtils.load(EnjoyshopApplication.sContext, cart.getImgUrl(), (ImageView) holder
                .getView(R.id.iv_view));

        NumberAddSubView numberAddSubView = (NumberAddSubView) holder.getView(R.id.num_control);
        numberAddSubView.setValue(cart.getCount());

        numberAddSubView.setOnButtonClickListener(new NumberAddSubView.OnButtonClickListener() {
            @Override
            public void onButtonAddClick(View view, int value) {
                cart.setCount(value);
                mCartShopProvider.updata(cart);
                mShopCartFragment.showTotalPrice();
            }

            @Override
            public void onButtonSubClick(View view, int value) {
                cart.setCount(value);
                mCartShopProvider.updata(cart);
                mShopCartFragment.showTotalPrice();
            }
        });


        final CheckBox checkBox = holder.getView(R.id.checkbox);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isChecked = cart.isChecked();
                if (isChecked) {
                    checkBox.setEnabled(false);
                } else {
                    checkBox.setEnabled(true);
                }

                mShopCartFragment.checkListen();
                mShopCartFragment.showTotalPrice();
                notifyDataSetChanged();
            }
        });

    }

}
