package com.tao.entity.dto;

import java.io.Serializable;

/**
 * @Author neotao
 * @Date 2018/9/6
 * @Version V0.0.1
 * @Desc
 */
public class MenuItemDTO implements Serializable {

    private String name;
    private int price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
