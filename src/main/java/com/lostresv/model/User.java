package com.lostresv.model;

import java.time.LocalDate;

public class User {

    private int id;
    private String name;
    private UserType userType;
    private LocalDate registrationDate;
    private Card card;

    public User() {
    }

    public User(int id, String name, UserType userType, LocalDate registrationDate, Card card) {
        this.id = id;
        this.name = name;
        this.userType = userType;
        this.registrationDate = registrationDate;
        this.card = card;
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userType=" + userType +
                ", registrationDate=" + registrationDate +
                ", card=" + card +
                '}';
    }
}
