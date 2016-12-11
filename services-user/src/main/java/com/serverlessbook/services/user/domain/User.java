package com.serverlessbook.services.user.domain;


public class User {

    private String id;

    private String username;

    private String email;

    public String getId() {
        return id;
    }

    public User withId(String id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public User withUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User withEmail(String email) {
        this.email = email;
        return this;
    }
}
