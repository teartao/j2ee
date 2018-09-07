package com.tao.service.impl;

import com.tao.entity.dto.MenuItemDTO;
import com.tao.entity.po.MenuItemPO;
import com.tao.service.MenuFactory;
import com.tao.utils.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author neotao
 * @Date 2018/9/6
 * @Version V0.0.1
 * @Desc
 */
@Component
public class HTDMenuService implements MenuFactory {
    @Override
    public List<MenuItemDTO> parseToMenu(String menuTxt) {
        return parseToMenu(menuTxt, 1);
    }

    @Override
    public List<MenuItemDTO> parseToMenu(String menuTxt, int startRow) {
        String[] menuRows = menuTxt.split("\n");
        List<MenuItemDTO> menus = new ArrayList<>();
        for (int i = startRow; i < menuRows.length; i++) {
            String menuRow = menuRows[i];
            MenuItemDTO menu = new MenuItemDTO();
            menu.setName(StringUtils.getChineseChars(menuRow));
            menu.setPrice(Integer.valueOf(StringUtils.getChineseChars(menuRow)));
            menus.add(menu);
        }
        return menus;
    }
}
