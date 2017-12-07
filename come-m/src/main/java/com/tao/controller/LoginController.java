package com.tao.controller;

import com.alibaba.fastjson.JSONObject;
import com.tao.dto.ExecuteResult;
import com.tao.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by taolei on 2017/5/5.
 */
@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ExecuteResult login(@RequestBody JSONObject param) {
        return loginService.checkLoginUser(param.getString("userName"), param.getString("password"));
    }
}
