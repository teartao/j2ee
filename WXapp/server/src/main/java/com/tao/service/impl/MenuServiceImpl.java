package com.tao.service.impl;

import com.tao.dao.MenuDao;
import com.tao.dao.MenuSelectionDao;
import com.tao.dao.UserDao;
import com.tao.entity.po.MenuList;
import com.tao.entity.po.MenuItem;
import org.springframework.stereotype.Service;
import com.tao.service.MenuService;

import javax.annotation.Resource;

/**
 * @Author neotao
 * @Date 2018/5/18
 * @Version V0.0.1
 * @Desc
 */
@Service("menuService")
public class MenuServiceImpl implements MenuService {

    @Resource
    private UserDao userDao;
    @Resource
    private MenuDao menuDao;
    @Resource
    private MenuSelectionDao menuSelectionDao;

    @Override
    public MenuList publishMenu(MenuList menuList) {
        menuDao.add(menuList);
        for (MenuItem selection : menuList.getSelectionList()) {
            menuSelectionDao.add(selection);
        }
        return menuList;
    }

    @Override
    public MenuList latestMenu(MenuList menuList) {
        return null;
    }
}
