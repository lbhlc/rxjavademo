package com.example.administrator.myapplication.model;

import com.google.gson.annotations.Expose;


/**
 * 账户部分返回的Model
 *
 * @author lbh
 * @version 1.0.0
 */
public class AccountRspModel {

    // 当前登录的账号名称
    @Expose
    private String username;
    @Expose
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AccountRspModel(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
