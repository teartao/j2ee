package com.tao.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.tao.dto.ExecuteResult;
import com.tao.service.LoginService;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * Created by taolei on 2017/5/8.
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Override
    public ExecuteResult<JSONObject> checkLoginUser(String userName, String password) {
        ExecuteResult<JSONObject> rs = new ExecuteResult<>();
        if ("admin".equals(userName) && "123".equals(userName)) {
            rs.setSuccess(true);
            rs.setMessage("login success");
            rs.setResult(new JSONObject());

        } else {
            rs.setSuccess(false);
            rs.setMessage("login failed");
            rs.setResult(new JSONObject());
        }
        return rs;
    }
}
