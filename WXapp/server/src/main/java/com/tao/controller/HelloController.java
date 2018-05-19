package com.tao.controller;

import com.alibaba.fastjson.JSONObject;
import com.tao.dto.Menu;
import org.springframework.jdbc.core.JdbcTemplate;
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
    private JdbcTemplate jdbcTemplate;

    @RequestMapping(value = "json", method = RequestMethod.GET)
    public JSONObject getJsonObj() throws IOException {
        JSONObject result = new JSONObject();
        result.put("aaa", "呵呵额");
        result.put("bbb", "222");
        result.put("ccc", "333");
        if (result.getString("xxx") == null) {
            throw new RuntimeException("xxxx");
        }
        return result;
    }

    @RequestMapping(value = "menu", method = RequestMethod.POST)
    public JSONObject addMenu(@RequestBody Menu menu) {
//        menuService.publishMenu(menu);

        jdbcTemplate.batchUpdate();
        return new JSONObject();
    }
}