package com.enjoyshop.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.enjoyshop.EnjoyshopApplication;
import com.enjoyshop.R;
import com.enjoyshop.bean.ShoppingCart;
import com.enjoyshop.utils.GlideUtils;

import java.util.List;

/**
 * <pre>
 *     author : 高磊华
 *     e-mail : 984992087@qq.com
 *     time   : 2017/08/13
 *     desc   : 商品订单适配器
 *     version: 1.0
 * </pre>
 */


public class GoodsOrderAdapter extends BaseQuickAdapter<ShoppingCart, BaseViewHolder> {

    private List<ShoppingCart> mDatas;

    public GoodsOrderAdapter(List<ShoppingCart> datas) {
        super(R.layout.template_order_goods, datas);
        this.mDatas = datas;
    }

    @Override
    protected void convert(BaseViewHolder holder, ShoppingCart item) {
        GlideUtils.load(EnjoyshopApplication.sContext, item.getImgUrl(), (ImageView) holder
                .getView(R.id.iv_view));
    }


    public float getTotalPrice() {

        float sum = 0;
        if (!isNull())
            return sum;

        for (ShoppingCart cart : mDatas) {
            sum += cart.getCount() * cart.getPrice();
        }

        return sum;

    }


    private boolean isNull() {
        return (mDatas != null && mDatas.size() > 0);
    }
}
