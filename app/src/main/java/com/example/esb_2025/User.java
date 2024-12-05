package com.example.esb_2025;

public class User {

    private String username;
    private String pwd, email;

    public User(String username, String pwd, String email) {
        this.username = username;
        this.email = email;
        this.pwd = pwd;
    }

    public User() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
