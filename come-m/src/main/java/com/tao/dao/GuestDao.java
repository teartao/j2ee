package com.tao.dao;

import java.util.List;
import java.util.Map;

import com.tao.entity.Guest;

/**
 * @Author TaoLei
 * @Date 16/7/13
 * @Since
 * @Desc
 */
public interface GuestDao {

    Guest findGuestById(Integer id);

    List<Guest> findGuestByName(String name);

    List<Guest> findGuestsByKeyword(String keyword);

    int updateGuest(Guest guest);

    int addGuest(Guest guest);
}
