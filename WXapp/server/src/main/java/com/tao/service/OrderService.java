package com.tao.service;

import com.tao.dto.Order;
import com.tao.dto.User;

/**
 * @Author neotao
 * @Date 2018/5/18
 * @Version V0.0.1
 * @Desc
 */
public interface OrderService {
    Order findMyOrder(User user);
}
