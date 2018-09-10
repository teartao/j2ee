package com.tao.business.impl;

import com.tao.business.MenuService;
import com.tao.dao.MenuDao;
import com.tao.dao.MenuSelectionDao;
import com.tao.dao.UserDao;
import com.tao.entity.dto.MenuItemDTO;
import com.tao.entity.po.MenuItemPO;
import com.tao.entity.po.MenuList;
import com.tao.service.MenuFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    @Resource
    private UserDao userDao;
    @Resource
    private MenuDao menuDao;
    @Resource
    private MenuSelectionDao menuSelectionDao;

    @Resource
    private MenuFactory menuFactory;


    @Override
    public List<MenuItemPO> publishMenu(String menuTxt) {
        List<MenuItemDTO> menuItemsList = menuFactory.parseToMenu(menuTxt);
        List<MenuItemPO> menuPOList = new ArrayList<>();
        for (MenuItemDTO menuItem : menuItemsList) {
            MenuItemPO menuPOItem = new MenuItemPO();
            menuPOItem.setName(menuItem.getName());
            menuPOItem.setPrice(menuItem.getPrice());
            menuPOList.add(menuPOItem);
        }

        return menuPOList;
    }

    @Override
    public MenuList latestMenu(MenuList menuList) {
        return null;
    }
}
