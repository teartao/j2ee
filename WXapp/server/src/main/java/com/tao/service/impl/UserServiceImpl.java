package com.tao.service.impl;

import com.tao.dao.UserDao;
import com.tao.dao.UserWxDao;
import com.tao.entity.User;
import com.tao.entity.WxUser;
import com.tao.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author neotao
 * @Date 2018/5/21
 * @Version V0.0.1
 * @Desc
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;
    @Resource
    private UserWxDao userWxDao;

    @Override
    public User findUser(User user) {
        user = userDao.find(user);
        if (user != null) {
            user.setWxUser(userWxDao.find(user.getWxUser()));
        }
        return user;
    }

    @Override
    public User createUser(User user) {
        WxUser wxUser = user.getWxUser();

        User rsUser = userDao.find(user);
        if (rsUser == null) {
            userDao.add(user);
            rsUser = userDao.find(user);
        }

        wxUser.setUserId(rsUser.getId());
        WxUser rsWxUser = userWxDao.find(wxUser);
        if (rsWxUser == null) {
            userWxDao.add(wxUser);
            rsWxUser = userWxDao.find(wxUser);
        }

        rsUser.setWxUser(rsWxUser);
        return rsUser;
    }
}
