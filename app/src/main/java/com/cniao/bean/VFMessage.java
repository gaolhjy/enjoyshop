package com.cniao.bean;

/**
 * Created by 高磊华
 * Time  2017/8/18
 * Describe:  分类fragment中，用于上下滚动播出的信息栏
 */

public class VFMessage {
    /**
     * title : 交城县一镁合金仓库突发火灾,官方证实没有人员伤亡
     * newsId : 1
     */

    private String title;
    private String newsId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public VFMessage(String newsId, String title) {
        this.title = title;
        this.newsId = newsId;
    }

    public VFMessage() {
    }
}
