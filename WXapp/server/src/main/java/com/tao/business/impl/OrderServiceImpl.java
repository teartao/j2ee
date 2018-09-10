package com.tao.business.impl;

import com.tao.business.OrderService;
import com.tao.entity.dto.MenuItemDTO;
import org.springframework.stereotype.Service;

/**
 * @Author neotao
 * @Date 2018/9/10
 * @Version V0.0.1
 * @Desc
 */
@Service("orderBizService")
public class OrderServiceImpl implements OrderService {
    @Override
    public MenuItemDTO findMyOrder(String userId) {
        return null;
    }

    @Override
    public MenuItemDTO addOrder(String userId, String orderId) {
        return null;
    }
}
