package com.haycm.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    @ResponseBody
    @RequestMapping("/index")
    public String welcome() {
        JSONObject result = new JSONObject();
        result.put("aaa","爱爱爱");
        result.put("bbb","bbb1");
        result.put("ccc","ccc1");
        System.out.println(result);
        return result.toJSONString();
    }
}
