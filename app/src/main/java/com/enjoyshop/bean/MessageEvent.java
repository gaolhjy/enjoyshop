package com.enjoyshop.bean;

/**
 * Created by 高磊华
 * Time  2017/8/22
 * Describe: eventbus 的数据模型
 */

public class MessageEvent {

    public MessageEvent(int type) {
        this.type = type;
    }

    private int type = 0;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
