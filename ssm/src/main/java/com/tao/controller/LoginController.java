package com.tao.controller;

import com.alibaba.fastjson.JSONObject;
import com.tao.entity.ResultDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("login")
public class LoginController {

    @RequestMapping(method = RequestMethod.GET)
    public String login(ModelMap model) {
        return "login";
    }

    @ResponseBody
    @RequestMapping(value = "doLogin")
    public ResultDTO<JSONObject> doLogin(String username, String password) {
        ResultDTO<JSONObject> result = new ResultDTO<>();
        if (username != null && !"".equals(username) && password != null && !"".equals(password)) {
            if (username.equals("admin") && password.equals("123")) {
                result.setCode(0);
                result.setMsg("成功");
                JSONObject data = new JSONObject();
                data.put("riderId", "92");
                data.put("token", "EkQpvLZZRbG1GB44pSuluw==");
                result.setData(data);
                return result;
            }
        }
        result.setCode(401);
        result.setMsg("用户名或密码错误");
        result.setData(new JSONObject());
        return result;
    }
}
