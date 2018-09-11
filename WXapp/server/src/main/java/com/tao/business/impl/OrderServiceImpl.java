package com.tao.business.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tao.business.MenuService;
import com.tao.business.OrderService;
import com.tao.entity.dto.MenuItemDTO;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @Value("${fileUploadPath}")
    private String filePath;

    @Resource
    private MenuService menuBizService;

    @Override
    public MenuItemDTO findMyOrder(String userId) {
        return null;
    }

    @Override
    public MenuItemDTO addOrder(String userId, String orderId) throws IOException {
        //根据日期创建对应日期的点餐记录文件 order.YYYY-MM-dd.json
        SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd");
        String dateString = formatter.format(new Date());

        String orderFileFullName = filePath + "order." + dateString + ".json";
        File todayOrderFile = new File(orderFileFullName);
        //判断文件是否存在，存在则读取，否则创建
        if (!todayOrderFile.exists()) {
            boolean isSuccess = todayOrderFile.createNewFile();
            logger.info("create file [{}] success:{}", orderFileFullName, isSuccess);
        }

        //读取文件内容，校验是否为空
        String ordersContent = FileUtils.readFileToString(todayOrderFile, "utf-8");
        JSONObject todayOrderJSON = null;
        if (!StringUtils.isEmpty(ordersContent)) {
            todayOrderJSON = JSON.parseObject(ordersContent);
        } else {
            todayOrderJSON = new JSONObject();
        }
        List<MenuItemDTO> menuList = menuBizService.latestMenu();
        //判断用户点餐选项，将用户点餐选项存入
        int orderIndex = Integer.valueOf(orderId);
        if (0 <= orderIndex && orderIndex < menuList.size()) {
            //{userId:menuItem}
            todayOrderJSON.put(userId, menuList.get(orderIndex));
        }else{
            logger.error("点餐失败，菜单号{}不存在", orderId);
            throw  new IllegalArgumentException("菜单编号不存在");
        }
        FileUtils.writeStringToFile(todayOrderFile, todayOrderJSON.toJSONString(), "utf-8");
        logger.info("write/update order to [{}],content :\n{} ", orderFileFullName, todayOrderJSON.toJSONString());
        return menuList.get(orderIndex);
    }
}
