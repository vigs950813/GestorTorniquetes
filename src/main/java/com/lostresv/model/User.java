package com.lostresv.model;

import java.util.Date;

public abstract class User {

    protected int id;
    protected String name;
    protected Date creationDate;
    protected String userType;
    protected Card card;

    public User(int id, String name, Date creationDate, String userType, Card card) {
        this.id = id;
        this.name = name;
        this.creationDate = creationDate;
        this.userType = userType;
        this.card = card;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public String getUserType() {
        return userType;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    @Override
    public String toString() {
        return String.format("[%s] ID: %d, Name: %s, Created: %s", userType, id, name, creationDate.toString());
    }
}
