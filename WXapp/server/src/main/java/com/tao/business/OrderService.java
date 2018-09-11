package com.tao.business;

import com.alibaba.fastjson.JSONObject;
import com.tao.entity.dto.MenuItemDTO;

import java.io.IOException;

/**
 * @Author neotao
 * @Date 2018/5/18
 * @Version V0.0.1
 * @Desc
 */
public interface OrderService {
    MenuItemDTO findMyOrder(String userId) throws IOException;

    JSONObject findOrders() throws IOException;

    JSONObject getOrderList() throws IOException;

    MenuItemDTO addOrder(String userId, String menuId) throws IOException;
}
