package com.gri.alex.model;

/**
 * User: Alex
 * Date: 11/21/17
 */
public class User {

    private final String id;
    private final String name;
    private final String email;
    private final String password;

    public User(String name, String email, String password) {
        this(null, name, email, password);
    }

    public User(String id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}