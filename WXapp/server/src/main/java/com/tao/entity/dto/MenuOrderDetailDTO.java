package com.tao.entity.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author neotao
 * @Date 2018/9/11
 * @Version V0.0.1
 * @Desc
 */
public class MenuOrderDetailDTO implements Serializable {
    private MenuItemDTO menu;
    private List<String> orderedUsers;

    public MenuItemDTO getMenu() {
        return menu;
    }

    public void setMenu(MenuItemDTO menu) {
        this.menu = menu;
    }

    public List<String> getOrderedUsers() {
        if (orderedUsers == null) {
            orderedUsers = new ArrayList<>();
        }
        return orderedUsers;
    }

    public void setOrderedUsers(List<String> orderedUsers) {
        this.orderedUsers = orderedUsers;
    }
}
