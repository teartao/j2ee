package com.tao.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.IOException;

@Controller
@RequestMapping("/")
public class HelloController {
    @Resource
    private JdbcTemplate jdbcTemplate;

    @RequestMapping(method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        model.addAttribute("message", "Hello world!");
        return "hello";
    }

    @ResponseBody
    @RequestMapping(value = "json", method = RequestMethod.GET)
    public JSONObject getJsonObj() throws IOException {
        JSONObject result = new JSONObject();
        result.put("aaa", "呵呵额");
        result.put("bbb", "222");
        result.put("ccc", "333");
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "getException", method = RequestMethod.GET)
    public JSONObject getException() throws IOException {
        throw new RuntimeException("has an exception");
    }
}