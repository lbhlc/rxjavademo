package com.example.administrator.myapplication.model;

import com.google.gson.annotations.Expose;

public class LoginModel {
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

    public LoginModel(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
