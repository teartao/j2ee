package com.tao.entity.po;

import java.util.Date;

/**
 * @Author neotao
 * @Date 2018/5/18
 * @Version V0.0.1
 * @Desc
 */
public class Order {
    private Long userId;
    private Long selection_id;
    private Date createTime;

    private User creater;
    private MenuItemPO selection;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSelection_id() {
        return selection_id;
    }

    public void setSelection_id(Long selection_id) {
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

    public MenuItemPO getSelection() {
        return selection;
    }

    public void setSelection(MenuItemPO selection) {
        this.selection = selection;
    }
}
