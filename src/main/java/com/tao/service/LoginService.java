package com.tao.service;

import com.alibaba.fastjson.JSONObject;
import com.tao.dto.ExecuteResult;

/**
 * Created by taolei on 2017/5/8.
 */
public interface LoginService {
    ExecuteResult<JSONObject> checkLoginUser(String userName, String password);
}
