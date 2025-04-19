package com.example.ql_internet.model;

public class Login {
    private static String name;
    private String password;
private String role ;
    public Login(String name, String password, String role) {
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public Login(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static String getUsername() {
        return name;
    }
    public void setUsername(String username) {
        this.name = username;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
}
