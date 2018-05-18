package com.tao.service;

import com.tao.dto.Menu;

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
     * @param menu
     * @return
     */
    Menu publishMenu(Menu menu);

    /**
     * 获取最新菜单
     *
     * @param menu
     * @return
     */
    Menu latestMenu(Menu menu);
}
