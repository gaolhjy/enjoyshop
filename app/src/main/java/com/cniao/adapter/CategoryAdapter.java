package com.cniao.adapter;

import android.content.Context;

import com.cniao.R;
import com.cniao.bean.Category;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * <pre>
 *     author : 高磊华
 *     e-mail : 984992087@qq.com
 *     time   : 2017/08/08
 *     desc   : 分类一级菜单.第一次以这个形式使用鸿洋封装的baseAdapter
 *     version: 1.0
 * </pre>
 */


public class CategoryAdapter extends CommonAdapter<Category> {

    public CategoryAdapter(Context context, List<Category> datas) {
       super(context, R.layout.template_single_text, datas);
    }

    @Override
    protected void convert(ViewHolder holder, Category category, int position) {
        holder.setText(R.id.textView,category.getName());
    }
}
