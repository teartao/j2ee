package com.tao.service;

import com.tao.entity.User;

/**
 * @Author neotao
 * @Date 2018/5/18
 * @Version V0.0.1
 * @Desc
 */
public interface UserService {
    User findUser(User user);

    User createUser(User user);
}
