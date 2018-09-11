package com.tao.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.tao.entity.dto.MenuItemDTO;
import com.tao.entity.po.MenuItemPO;
import com.tao.service.MenuFactory;
import com.tao.utils.StringUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
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

    @Value("${fileUploadPath}")
    private String filePath;
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

    @Override
    public List<MenuItemDTO> getMenu() throws IOException {
        String menuJSON = FileUtils.readFileToString(new File(filePath + "menu.json"), "utf-8");
        return JSONObject.parseObject(menuJSON, new TypeReference<List<MenuItemDTO>>() {
        });
    }

    @Override
    public void saveMenu(List<MenuItemPO> menuPOList) throws IOException {
        FileUtils.writeStringToFile(new File(filePath + "menu.json"), JSON.toJSONString(menuPOList), "utf-8");
    }

    public static void main(String[] args) {
        HTDMenuService xx= new HTDMenuService();
        List<MenuItemDTO> menu=xx.parseToMenu("周一\n" +
                "苏式熏鱼\n" +
                "酸汤萝卜鸭\n" +
                "海鲜菇鸡丁\n" +
                "椒盐土豆猪肉条（12）\n" +
                "红烧大排（12）\n" +
                "老干妈土豆烧鸡（12）\n" +
                "木须肉盖浇饭加芝心鸡排（15");
        System.out.println(JSON.toJSONString(menu));
    }
}
