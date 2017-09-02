package com.cniao.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.cniao.CNiaoApplication;
import com.cniao.R;
import com.cniao.bean.ShoppingCart;
import com.cniao.utils.GlideUtils;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

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


public class GoodsOrderAdapter extends CommonAdapter<ShoppingCart> {

    private List<ShoppingCart> mDatas;

    public GoodsOrderAdapter(Context context, List<ShoppingCart> datas) {
        super(context, R.layout.template_order_goods, datas);
        this.mDatas = datas;
    }

    @Override
    protected void convert(ViewHolder holder, ShoppingCart shoppingCart, int position) {

        GlideUtils.load(CNiaoApplication.sContext, shoppingCart.getImgUrl(), (ImageView) holder
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
