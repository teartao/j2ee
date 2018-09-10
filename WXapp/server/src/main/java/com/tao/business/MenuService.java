package com.tao.business;

import com.tao.entity.dto.MenuItemDTO;
import com.tao.entity.po.MenuItemPO;
import com.tao.entity.po.MenuList;

import java.io.IOException;
import java.util.List;

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
     * @param menuTxt
     * @return
     */
    List<MenuItemPO> publishMenu(String menuTxt) throws IOException;

    /**
     * 获取最新菜单
     *
     * @return
     */
    List<MenuItemDTO> latestMenu() throws IOException;
}
