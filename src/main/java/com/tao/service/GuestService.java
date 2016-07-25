package com.tao.service;

import com.tao.dao.GuestDao;
import com.tao.entity.Guest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
