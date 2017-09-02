package com.cniao.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.cniao.CNiaoApplication;
import com.cniao.R;
import com.cniao.bean.HotGoods;
import com.cniao.utils.GlideUtils;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by 高磊华
 * Time  2017/8/9
 * Dscribe: 分类 二级菜单 适配器
 */

public class SecondGoodsAdapter extends CommonAdapter<HotGoods.ListBean> {

    public SecondGoodsAdapter(Context context, List<HotGoods.ListBean> datas) {
        super(context, R.layout.template_category_wares, datas);

    }

    @Override
    protected void convert(ViewHolder holder, HotGoods.ListBean bean, int position) {

        holder.setText(R.id.text_title, bean.getName());
        holder.setText(R.id.text_price, "￥" + bean.getPrice());
        GlideUtils.load(CNiaoApplication.sContext, bean.getImgUrl(), (ImageView) holder.getView(R
                .id.iv_view));
    }
}
