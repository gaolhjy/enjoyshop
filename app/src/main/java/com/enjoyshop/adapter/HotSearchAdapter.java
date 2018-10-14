package com.enjoyshop.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.enjoyshop.R;

import java.util.List;

/**
 * <pre>
 *   author : 高磊华
 *   e-mail : 984992087@qq.com
 *   time   : 2017/08/28
 *   desc   : 热门搜索的适配器
 * </pre>
 */

public class HotSearchAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public HotSearchAdapter(List<String> datas) {
        super(R.layout.item_search, datas);
    }

    @Override
    protected void convert(BaseViewHolder holder, String item) {
        holder.setText(R.id.tv_content, item);
    }
}


