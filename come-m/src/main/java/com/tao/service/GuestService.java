package com.tao.service;

import com.alibaba.fastjson.JSONObject;
import com.tao.entity.Guest;

import java.util.List;
import java.util.Map;

/**
 * @Author TaoLei
 * @Date 16/7/13
 * @Since
 * @Desc
 */
public interface GuestService {

    Guest findGuestById(Integer id);

    Guest findGuestByName(String name);

    List<Guest> findGuests(String keyword);

    int addMoney(String name, double price);

    int addGuest(String name, Double price);

}
