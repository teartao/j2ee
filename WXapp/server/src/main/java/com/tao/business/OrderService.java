package com.tao.business;

import com.tao.entity.dto.MenuItemDTO;
import com.tao.entity.po.Order;
import com.tao.entity.po.User;

import java.io.IOException;

/**
 * @Author neotao
 * @Date 2018/5/18
 * @Version V0.0.1
 * @Desc
 */
public interface OrderService {
    MenuItemDTO findMyOrder(String userId);

    MenuItemDTO addOrder(String userId, String orderId) throws IOException;
}
