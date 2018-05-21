package com.tao.controller;

import com.alibaba.fastjson.JSONObject;
import com.tao.dto.UserDTO;
import com.tao.entity.Menu;
import com.tao.entity.User;
import com.tao.entity.WxUser;
import com.tao.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.tao.service.MenuService;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
public class HelloController {
    @Resource
    private MenuService menuService;
    @Resource
    private UserService userService;

    @RequestMapping(value = "json", method = RequestMethod.GET)
    public JSONObject getJsonObj() throws IOException {
        JSONObject result = new JSONObject();
        result.put("aaa", "呵呵额");
        result.put("bbb", "222");
        result.put("ccc", "333");
        return result;
    }

    @RequestMapping(value = "menu", method = RequestMethod.POST)
    public JSONObject addMenu(@RequestBody Menu menu) {
        menuService.publishMenu(menu);

        return new JSONObject();
    }
    @RequestMapping(value = "user", method = RequestMethod.POST)
    public JSONObject addMenu(@RequestBody UserDTO userDTO) {
        User user = new User();
//        user.setId(userDTO.getId());
        user.setName(userDTO.getNickName());

        WxUser wxUser = new WxUser();
        wxUser.setCity(userDTO.getCity());
        wxUser.setCountry(userDTO.getCountry());
        wxUser.setGender(userDTO.getGender());
        wxUser.setLanguage(userDTO.getLanguage());
        wxUser.setNickName(userDTO.getNickName());
        wxUser.setLanguage(userDTO.getLanguage());
        wxUser.setAvatarUrl(userDTO.getAvatarUrl());

        user.setWxUser(wxUser);
        userService.createUser(user);

        JSONObject json = new JSONObject();
        json.put("xxx","asd");
        return json;
    }
}