package com.tao.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@RequestMapping("/")
public class HelloController {
    @ResponseBody
    @RequestMapping(value = "getJsonObj", method = RequestMethod.GET)
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