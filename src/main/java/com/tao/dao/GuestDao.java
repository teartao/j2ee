package com.tao.dao;

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
public interface GuestDao {
    Guest findGuest(Integer guestId);

    List<Guest> findGuests(String keyword);

    List<Guest> findGuests(Map param);
}
