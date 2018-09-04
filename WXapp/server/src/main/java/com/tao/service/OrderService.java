package com.tao.service;

import com.tao.entity.po.Order;
import com.tao.entity.po.User;

/**
 * @Author neotao
 * @Date 2018/5/18
 * @Version V0.0.1
 * @Desc
 */
public interface OrderService {
    Order findMyOrder(User user);
}
