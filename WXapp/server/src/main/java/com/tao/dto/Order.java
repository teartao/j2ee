package com.tao.dto;

import java.util.Date;

/**
 * @Author neotao
 * @Date 2018/5/18
 * @Version V0.0.1
 * @Desc
 */
public class Order {
    private long userId;
    private long selection_id;
    private Date createTime;

    private User creater;
    private MenuSelection selection;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getSelection_id() {
        return selection_id;
    }

    public void setSelection_id(long selection_id) {
        this.selection_id = selection_id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public User getCreater() {
        return creater;
    }

    public void setCreater(User creater) {
        this.creater = creater;
    }

    public MenuSelection getSelection() {
        return selection;
    }

    public void setSelection(MenuSelection selection) {
        this.selection = selection;
    }
}
