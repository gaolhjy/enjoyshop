package com.enjoyshop.bean;

/**
 * <pre>
 *     author : 高磊华
 *     e-mail : 984992087@qq.com
 *     time   : 2017/08/08
 *     desc   :分类 一级菜单
 *     version: 1.0
 * </pre>
 */
public class Category extends BaseBean {


    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
}
