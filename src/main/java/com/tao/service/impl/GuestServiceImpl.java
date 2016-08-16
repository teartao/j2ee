package com.tao.service.impl;

import com.tao.dao.GuestDao;
import com.tao.entity.Guest;
import com.tao.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author TaoLei
 * @Date 16/7/20
 * @Since
 * @Desc
 */
@Service("guestServiceImpl")
public class GuestServiceImpl implements GuestService {
    @Autowired
    private GuestDao guestDao;

    @Override
    public Guest findGuest(Integer id) {
        return guestDao.findGuest(id);
    }

    @Override
    public List<Guest> findGuests(String keyword) {
        return guestDao.findGuests(keyword);
    }

    @Override
    public List<Guest> findGuests(Map param) {
        return guestDao.findGuests(param);
    }
}
