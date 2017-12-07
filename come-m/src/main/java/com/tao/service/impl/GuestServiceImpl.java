package com.tao.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.tao.dao.GuestDao;
import com.tao.entity.Guest;
import com.tao.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
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
    public Guest findGuestById(Integer id) {
        return guestDao.findGuestById(id);
    }

    @Override
    public Guest findGuestByName(String name) {
        List<Guest> guests = guestDao.findGuestByName(name);
        if (guests.size() != 1) {
            return null;
        }
        return guests.get(0);
    }

    @Override
    public List<Guest> findGuests(String keyword) {
        return guestDao.findGuestsByKeyword(keyword);
    }

    @Override
    public int addMoney(String name, double price) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println(df.format(new Date()) + " -- " + name + price);

        Guest guest = findGuestByName(name);
        if (guest == null) {
            throw new RuntimeException("没这个人吧 ?");
        }
        if (guest.getPrice() != 0) {
            throw new RuntimeException("已经出过钱辣 !!");
        }
        guest.setPrice(price);
        return guestDao.updateGuest(guest);
    }

    @Override
    public int addGuest(String name, Double price) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println(df.format(new Date()) + " -- " + name + price);

        Guest guest = findGuestByName(name);
        if (guest == null) {
            guest = new Guest();
            guest.setName(name);
            guest.setPrice(price);
        } else if (guest.getName() != null && !"".equals(guest.getName().trim())) {
            throw new RuntimeException("已经有这个客人辣!! 添加价格就好");
        }

        return guestDao.addGuest(guest);
    }
}
