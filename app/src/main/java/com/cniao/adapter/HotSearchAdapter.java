package com.cniao.adapter;

import android.content.Context;

import com.cniao.R;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * <pre>
 *   author : 高磊华
 *   e-mail : 984992087@qq.com
 *   time   : 2017/08/28
 *   desc   : 热门搜索的适配器
 * </pre>
 */

public class HotSearchAdapter extends CommonAdapter<String> {

    public HotSearchAdapter(Context context, List<String> datas) {
        super(context, R.layout.item_search, datas);
    }

    @Override
    protected void convert(ViewHolder holder, String s, int position) {
        holder.setText(R.id.tv_content, s);
    }
}

