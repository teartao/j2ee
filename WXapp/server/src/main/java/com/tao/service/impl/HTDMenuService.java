package com.tao.service.impl;

import com.alibaba.fastjson.JSON;
import com.tao.entity.dto.MenuItemDTO;
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

            String name=StringUtils.getChineseChars(menuRow);
            if(!org.springframework.util.StringUtils.isEmpty(name)){
                menu.setName(name);
            }

            String price = StringUtils.getNumbers(menuRow);
            if(!org.springframework.util.StringUtils.isEmpty(price)){
                menu.setPrice(Integer.valueOf(price));
            }else{
                menu.setPrice(10);
            }
            menus.add(menu);
        }
        return menus;
    }

    public static void main(String[] args) {
        HTDMenuService xx= new HTDMenuService();
        List<MenuItemDTO> menu=xx.parseToMenu("9月7日\n" +
                "千张卷肉\n" +
                "剁椒蒸鱼块\n" +
                "盐水鸭（12）\n" +
                "瓠子烧鸡（12）\n" +
                "咖喱猪排（12）\n" +
                "酸菜粉条烧肉（12）\n" +
                "冒菜冒肉片（12）");
        System.out.println(JSON.toJSONString(menu));
    }
}
