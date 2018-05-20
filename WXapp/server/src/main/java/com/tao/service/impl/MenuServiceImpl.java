package com.tao.service.impl;

import com.tao.dao.MenuDao;
import com.tao.dao.MenuSelectionDao;
import com.tao.dao.UserDao;
import com.tao.dto.Menu;
import com.tao.dto.MenuSelection;
import com.tao.dto.User;
import org.springframework.stereotype.Service;
import com.tao.service.MenuService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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
    public Menu publishMenu(Menu menu) {
        menuDao.add(menu);
        for (MenuSelection selection : menu.getSelectionList()) {
            menuSelectionDao.add(selection);
        }
        return menu;
    }

    @Override
    public Menu latestMenu(Menu menu) {
        return null;
    }
}
