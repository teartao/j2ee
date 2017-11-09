package com.haycm.entity;

/**
 * author TaoLei
 * date 15-3-19.
 * description
 */
public class LoginResult {
    private String userName;
    private String password;

    public LoginResult() {
    }
    public LoginResult(String username, String password) {
        this.userName = username;
        this.password = password;
    }
}
