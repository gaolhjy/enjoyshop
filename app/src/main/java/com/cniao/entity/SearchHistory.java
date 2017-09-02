package com.cniao.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * <pre>
 *   author : 高磊华
 *   e-mail : 984992087@qq.com
 *   time   : 2017/08/29
 *   desc   : 搜索历史的greendao实体类
 * </pre>
 */

@Entity
public class SearchHistory {

    @Id(autoincrement = true)
    private Long   id;
    @Property(nameInDb = "SEARCHCONTENT")
    private String searchContent;
    @Property(nameInDb = "ORDER")
    private int    order;

    @Generated(hash = 2011946050)
    public SearchHistory(Long id, String searchContent, int order) {
        this.id = id;
        this.searchContent = searchContent;
        this.order = order;
    }

    @Generated(hash = 1905904755)
    public SearchHistory() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSearchContent() {
        return this.searchContent;
    }

    public void setSearchContent(String searchContent) {
        this.searchContent = searchContent;
    }

    public int getOrder() {
        return this.order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
