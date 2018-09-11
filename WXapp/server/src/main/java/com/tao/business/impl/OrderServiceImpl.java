package com.tao.business.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tao.business.MenuService;
import com.tao.business.OrderService;
import com.tao.entity.dto.MenuItemDTO;
import com.tao.manager.OrderManager;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @Author neotao
 * @Date 2018/9/10
 * @Version V0.0.1
 * @Desc
 */
@Service("orderBizService")
public class OrderServiceImpl implements OrderService {
    private static Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Resource
    private MenuService menuBizService;

    @Resource
    private OrderManager orderFactory;

    @Override
    public MenuItemDTO findMyOrder(String userId) throws IOException {
        JSONObject orders = findOrders();
        String myOrder = orders.getString(userId);
        return JSONObject.parseObject(myOrder, MenuItemDTO.class);
    }

    @Override
    public JSONObject findOrders() throws IOException {
        File todayOrderFile = orderFactory.getTodayOrderFile();
        //读取文件内容，校验是否为空
        String ordersContent = FileUtils.readFileToString(todayOrderFile, "utf-8");
        JSONObject todayOrderJSON = null;
        if (!StringUtils.isEmpty(ordersContent)) {
            todayOrderJSON = JSON.parseObject(ordersContent);
        } else {
            todayOrderJSON = new JSONObject();
        }
        return todayOrderJSON;

    }

    @Override
    public JSONObject getOrderList() throws IOException {
//        JSONObject orders = findOrders();
//        List<JSONObject> orderedMenuList = new ArrayList<>();
//        List<String> orderedMenuKey = new ArrayList<>();
//        for (String key : orders.keySet()) {
//            if (!orderedMenuKey.contains(key)) {
//                orderedMenuKey.add(key);
//                JSONObject orderMenuItem = orders.getJSONObject(key);
//                orderMenuItem.put("");
//                orderedMenuList.add(orderMenuItem);
//            } else {
//
//            }
//        }
        return null;
    }

    @Override
    public MenuItemDTO addOrder(String userId, String menuId) throws IOException {
        File todayOrderFile = orderFactory.getTodayOrderFile();
        JSONObject todayOrderJSON = findOrders();
        List<MenuItemDTO> menuList = menuBizService.latestMenu();
        //判断用户点餐选项，将用户点餐选项存入
        int menuIndex = Integer.valueOf(menuId);
        if (0 <= menuIndex && menuIndex < menuList.size()) {
            //{userId:menuItem}
            todayOrderJSON.put(userId, menuList.get(menuIndex));
        } else {
            logger.error("点餐失败，菜单号{}不存在", menuId);
            throw new IllegalArgumentException("菜单编号不存在");
        }
        orderFactory.saveOrder(todayOrderJSON.toJSONString());
        logger.info("write/update order to [{}],content :\n{} ", todayOrderFile.getAbsolutePath(), todayOrderJSON.toJSONString());
        return menuList.get(menuIndex);
    }
}
