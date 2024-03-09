package com.example.clientapp.Model;

public class tblUser {
    private String UserName;
    private String Password;

    public tblUser () {
    }

    public tblUser(String userName, String password) {
        UserName = userName;
        Password = password;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}