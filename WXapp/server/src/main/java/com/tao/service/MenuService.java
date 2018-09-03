package com.tao.service;

import com.tao.entity.MenuList;

/**
 * @Author neotao
 * @Date 2018/5/18
 * @Version V0.0.1
 * @Desc
 */
public interface MenuService {
    /**
     * 发布菜单
     *
     * @param menuList
     * @return
     */
    MenuList publishMenu(MenuList menuList);

    /**
     * 获取最新菜单
     *
     * @param menuList
     * @return
     */
    MenuList latestMenu(MenuList menuList);
}
