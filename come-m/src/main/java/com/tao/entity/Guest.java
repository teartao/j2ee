package com.tao.entity;

/**
 * @Author TaoLei
 * @Date 16/7/20
 * @Since
 * @Desc
 */
public class Guest {
    private Integer id;
    private String name;
    private double price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
