package com.enjoyshop.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.enjoyshop.R;
import com.enjoyshop.bean.Category;

import java.util.List;

/**
 * <pre>
 *     author : 高磊华
 *     e-mail : 984992087@qq.com
 *     time   : 2017/08/08
 *     desc   : 分类一级菜单.
 *     version: 1.0
 * </pre>
 */


public class CategoryAdapter extends BaseQuickAdapter<Category, BaseViewHolder> {

    public CategoryAdapter(List<Category> datas) {
        super(R.layout.template_single_text, datas);
    }

    @Override
    protected void convert(BaseViewHolder holder, Category item) {
        holder.setText(R.id.textView, item.getName());
    }
}
