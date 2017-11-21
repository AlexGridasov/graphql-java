package com.gri.alex.model;

/**
 * User: Alex
 * Date: 11/21/17
 */
public class AuthData {

    private String email;
    private String password;

    public AuthData() {
    }

    public AuthData(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}