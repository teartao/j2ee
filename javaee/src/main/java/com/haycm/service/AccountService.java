package com.haycm.service;

import com.haycm.entity.LoginResult;
import org.springframework.stereotype.Service;

/**
 * author TaoLei
 * date 15-3-19.
 * description
 */
@Service
public class AccountService {
    public LoginResult login(String username, String password) {
        return new LoginResult(username,password);
    }
}
