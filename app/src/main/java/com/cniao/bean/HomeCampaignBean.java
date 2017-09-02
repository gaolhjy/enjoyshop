package com.cniao.bean;

/**
 * Created by 高磊华
 * Time  2017/8/7
 * Describe: 首页 商品数据
 */

public class HomeCampaignBean {


    private Campaign cpOne;
    private Campaign   cpTwo;
    private Campaign cpThree;
    private int         id;
    private String      title;


    public Campaign getCpOne() {
        return cpOne;
    }

    public void setCpOne(Campaign cpOne) {
        this.cpOne = cpOne;
    }

    public Campaign getCpTwo() {
        return cpTwo;
    }

    public void setCpTwo(Campaign cpTwo) {
        this.cpTwo = cpTwo;
    }

    public Campaign getCpThree() {
        return cpThree;
    }

    public void setCpThree(Campaign cpThree) {
        this.cpThree = cpThree;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
