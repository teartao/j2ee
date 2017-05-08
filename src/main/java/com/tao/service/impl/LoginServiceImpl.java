package com.tao.service.impl;

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
    public ExecuteResult checkLoginUser(String userName, String password) {
        ExecuteResult<HashMap<Object, Object>> rs = new ExecuteResult<>();
        if ("admin".equals(userName) && "123".equals(userName)) {
            rs.setSuccess(true);
            rs.setMessage("login success");
            rs.setResult(new HashMap<>());

        }else{
            rs.setSuccess(false);
            rs.setMessage("login failed");
            rs.setResult(new HashMap<>());
        }
        return rs;
    }
}
