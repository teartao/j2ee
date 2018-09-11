package com.tao.business.impl;

import com.tao.business.MenuService;
import com.tao.dao.MenuDao;
import com.tao.dao.MenuSelectionDao;
import com.tao.dao.UserDao;
import com.tao.entity.dto.MenuItemDTO;
import com.tao.entity.po.MenuItemPO;
import com.tao.manager.MenuManager;
import com.tao.manager.OrderManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author neotao
 * @Date 2018/5/18
 * @Version V0.0.1
 * @Desc
 */
@Service("menuBizService")
public class MenuServiceImpl implements MenuService {
    private static Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);

    @Resource
    private UserDao userDao;
    @Resource
    private MenuDao menuDao;
    @Resource
    private MenuSelectionDao menuSelectionDao;

    @Resource
    private MenuManager menuManager;
    @Resource
    private OrderManager orderManager;


    @Override
    public List<MenuItemPO> publishMenu(String menuTxt) throws IOException {
        List<MenuItemDTO> menuItemsList = menuManager.parseToMenu(menuTxt);
        List<MenuItemPO> menuPOList = new ArrayList<>();
        for (int i = 0; i < menuItemsList.size(); i++) {
            MenuItemDTO menuItem = menuItemsList.get(i);
            MenuItemPO menuPOItem = new MenuItemPO();
            menuPOItem.setId((long) i);
            menuPOItem.setName(menuItem.getName());
            menuPOItem.setPrice(menuItem.getPrice());
            menuPOList.add(menuPOItem);
        }

        menuManager.saveMenu(menuPOList);
        orderManager.saveOrder("");
        return menuPOList;
    }

    @Override
    public List<MenuItemDTO> latestMenu() throws IOException {
        return menuManager.getMenu();
    }
}
