package com.tao.business.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.tao.business.MenuService;
import com.tao.dao.MenuDao;
import com.tao.dao.MenuSelectionDao;
import com.tao.dao.UserDao;
import com.tao.entity.dto.MenuItemDTO;
import com.tao.entity.po.MenuItemPO;
import com.tao.entity.po.MenuList;
import com.tao.service.MenuFactory;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Author neotao
 * @Date 2018/5/18
 * @Version V0.0.1
 * @Desc
 */
@Service("menuBizService")
public class MenuServiceImpl implements MenuService {

    @Resource
    private UserDao userDao;
    @Resource
    private MenuDao menuDao;
    @Resource
    private MenuSelectionDao menuSelectionDao;

    @Resource
    private MenuFactory menuFactory;

    @Value("${fileUploadPath}")
    private String filePath;


    @Override
    public List<MenuItemPO> publishMenu(String menuTxt) throws IOException {
        List<MenuItemDTO> menuItemsList = menuFactory.parseToMenu(menuTxt);
        List<MenuItemPO> menuPOList = new ArrayList<>();
        for (int i = 0; i < menuItemsList.size(); i++) {
            MenuItemDTO menuItem = menuItemsList.get(i);
            MenuItemPO menuPOItem = new MenuItemPO();
            menuPOItem.setId(i + 1L);
            menuPOItem.setName(menuItem.getName());
            menuPOItem.setPrice(menuItem.getPrice());
            menuPOList.add(menuPOItem);
        }
        FileUtils.writeStringToFile(new File(filePath + "menu.json"), JSON.toJSONString(menuPOList), "utf-8");

        return menuPOList;
    }

    @Override
    public List<MenuItemDTO> latestMenu() throws IOException {
        String menuJSON = FileUtils.readFileToString(new File(filePath + "menu.json"), "utf-8");
        return JSONObject.parseObject(menuJSON, new TypeReference<List<MenuItemDTO>>() {
        });
    }
}
