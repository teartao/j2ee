package com.tao.service;

import java.util.List;
import java.util.Map;

import com.tao.entity.Guest;

/**
 * @Author TaoLei
 * @Date 16/7/13
 * @Since
 * @Desc
 */
public interface GuestService {
    Guest findGuest(Integer id);

    List<Guest> findGuests(String keyword);

    List<Guest> findGuests(Map param);
}
