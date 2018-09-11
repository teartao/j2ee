package com.tao.service;

import com.tao.entity.dto.MenuItemDTO;
import com.tao.entity.po.MenuItemPO;

import java.io.IOException;
import java.util.List;

/**
 * @Author neotao
 * @Date 2018/9/4
 * @Version V0.0.1
 * @Desc
 */
public interface MenuFactory {
    List<MenuItemDTO> parseToMenu(String menuTxt);
    List<MenuItemDTO> parseToMenu(String menuTxt,int startRow);
    List<MenuItemDTO> getMenu() throws IOException;

    void saveMenu(List<MenuItemPO> menuPOList) throws IOException;
}
