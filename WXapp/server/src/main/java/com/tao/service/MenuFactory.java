package com.tao.service;

import com.tao.entity.dto.MenuItemDTO;

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
}
