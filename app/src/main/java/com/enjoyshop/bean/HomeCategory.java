package com.enjoyshop.bean;


/**
 * <pre>
 *     author : 高磊华
 *     e-mail : 984992087@qq.com
 *     time   : 2017/08/06
 *     desc   :首页商品分类
 *     version: 1.0
 * </pre>
 */

public class HomeCategory extends Category {


    private int imgBig;
    private int imgSmallTop;
    private int imgSmallBottom;


    public HomeCategory(String name, int imgBig, int imgSmallTop, int imgSmallBottom) {
        super(name);
        this.imgBig = imgBig;
        this.imgSmallTop = imgSmallTop;
        this.imgSmallBottom = imgSmallBottom;
    }

    public int getImgBig() {
        return imgBig;
    }

    public void setImgBig(int imgBig) {
        this.imgBig = imgBig;
    }

    public int getImgSmallTop() {
        return imgSmallTop;
    }

    public void setImgSmallTop(int imgSmallTop) {
        this.imgSmallTop = imgSmallTop;
    }

    public int getImgSmallBottom() {
        return imgSmallBottom;
    }

    public void setImgSmallBottom(int imgSmallBottom) {
        this.imgSmallBottom = imgSmallBottom;
    }
}
