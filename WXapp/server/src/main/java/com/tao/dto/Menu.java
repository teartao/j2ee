package com.tao.dto;

import java.util.Date;
import java.util.List;

/**
 * @Author neotao
 * @Date 2018/5/18
 * @Version V0.0.1
 * @Desc 菜单
 */
public class Menu {
    private long id;
    private String title;
    private long createrId;
    private Date createTime;

    private User creater;
    private List<MenuSelection> selectionList;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public long getCreaterId() {
        return createrId;
    }

    public void setCreaterId(long createrId) {
        this.createrId = createrId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getCreater() {
        return creater;
    }

    public void setCreater(User creater) {
        this.creater = creater;
    }

    public List<MenuSelection> getSelectionList() {
        return selectionList;
    }

    public void setSelectionList(List<MenuSelection> selectionList) {
        this.selectionList = selectionList;
    }
}
