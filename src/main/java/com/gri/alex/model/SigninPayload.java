package com.gri.alex.model;

/**
 * User: Alex
 * Date: 11/22/17
 */
public class SigninPayload {

    private final String token;
    private final User user;

    public SigninPayload(String token, User user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public User getUser() {
        return user;
    }
}
