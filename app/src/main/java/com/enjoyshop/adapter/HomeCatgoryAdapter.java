package com.enjoyshop.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.enjoyshop.EnjoyshopApplication;
import com.enjoyshop.R;
import com.enjoyshop.bean.HomeCampaignBean;
import com.enjoyshop.utils.GlideUtils;

import java.util.List;

/**
 * <pre>
 *     author : 高磊华
 *     e-mail : 984992087@qq.com
 *     time   : 2017/08/06
 *     desc   : 首页商品分类的适配器
 *              涉及到多样式的条目的话,注意继承的是BaseMultiItemQuickAdapter
 *     version: 1.0
 * </pr>
 */

public class HomeCatgoryAdapter extends BaseMultiItemQuickAdapter<HomeCampaignBean,
        BaseViewHolder> {

    public HomeCatgoryAdapter(List<HomeCampaignBean> datas) {
        super(datas);
        addItemType(HomeCampaignBean.ITEM_TYPE_LEFT, R.layout.template_home_cardview);
        addItemType(HomeCampaignBean.ITEM_TYPE_RIGHT, R.layout.template_home_cardview2);
    }

    @Override
    protected void convert(BaseViewHolder holder, HomeCampaignBean bean) {
        switch (bean.getItemType()) {
            //左边的
            case HomeCampaignBean.ITEM_TYPE_LEFT:
                holder.setText(R.id.text_title, bean.getTitle());
                GlideUtils.load(EnjoyshopApplication.sContext, bean.getCpOne().getImgUrl(),
                        (ImageView) holder.getView(R.id.imgview_big));
                GlideUtils.load(EnjoyshopApplication.sContext, bean.getCpTwo().getImgUrl(),
                        (ImageView) holder.getView(R.id.imgview_small_top));
                GlideUtils.load(EnjoyshopApplication.sContext, bean.getCpThree().getImgUrl(),
                        (ImageView) holder.getView(R.id.imgview_small_bottom));

                holder.addOnClickListener(R.id.imgview_big)
                        .addOnClickListener(R.id.imgview_small_top)
                        .addOnClickListener(R.id.imgview_small_bottom);


                break;

            //右边的
            case HomeCampaignBean.ITEM_TYPE_RIGHT:

                holder.setText(R.id.text_title, bean.getTitle());
                GlideUtils.load(EnjoyshopApplication.sContext, bean.getCpOne().getImgUrl(),
                        (ImageView) holder.getView(R.id.imgview_big));
                GlideUtils.load(EnjoyshopApplication.sContext, bean.getCpTwo().getImgUrl(),
                        (ImageView) holder.getView(R.id.imgview_small_top));
                GlideUtils.load(EnjoyshopApplication.sContext, bean.getCpThree().getImgUrl(),
                        (ImageView) holder.getView(R.id.imgview_small_bottom));


                holder.addOnClickListener(R.id.imgview_big)
                        .addOnClickListener(R.id.imgview_small_top)
                        .addOnClickListener(R.id.imgview_small_bottom);
                break;
            default:
                break;
        }
    }
}

