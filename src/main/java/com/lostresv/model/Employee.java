package com.lostresv.model;

import java.util.Date;

public class Employee extends User {

    private String username;
    private String password;

    public Employee(int id, String name, Date creationDate, Card card, String username, String password) {
        super(id, name, creationDate, "Employee", card);
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
