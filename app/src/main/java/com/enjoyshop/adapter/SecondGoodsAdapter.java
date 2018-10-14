package com.enjoyshop.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.enjoyshop.EnjoyshopApplication;
import com.enjoyshop.R;
import com.enjoyshop.bean.HotGoods;
import com.enjoyshop.utils.GlideUtils;

import java.util.List;

/**
 * Created by 高磊华
 * Time  2017/8/9
 * Dscribe: 分类 二级菜单 适配器
 */

public class SecondGoodsAdapter extends BaseQuickAdapter<HotGoods.ListBean, BaseViewHolder> {

    public SecondGoodsAdapter(List<HotGoods.ListBean> datas) {
        super(R.layout.template_category_wares, datas);
    }

    @Override
    protected void convert(BaseViewHolder holder, HotGoods.ListBean bean) {
        holder.setText(R.id.text_title, bean.getName())
                .setText(R.id.text_price, "￥" + bean.getPrice());
        GlideUtils.load(EnjoyshopApplication.sContext, bean.getImgUrl(), (ImageView) holder
                .getView(R.id.iv_view));
    }
}
