package com.tao.service;

import com.tao.dto.ExecuteResult;

/**
 * Created by taolei on 2017/5/8.
 */
public interface LoginService {
    ExecuteResult checkLoginUser(String userName, String password);
}
