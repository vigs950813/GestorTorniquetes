// File: com/lostresv/model/Card.java
package com.lostresv.model;

import java.util.Date;

public class Card {
    private int id;
    private String uid;
    private boolean active;
    private int userId;
    private Date issueDate;

    public Card(int id, String uid, boolean active, int userId, Date issueDate) {
        this.id = id;
        this.uid = uid;
        this.active = active;
        this.userId = userId;
        this.issueDate = issueDate;
    }

    public int getId() {
        return id;
    }

    public String getUid() {
        return uid;
    }

    public boolean isActive() {
        return active;
    }

    public int getUserId() {
        return userId;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    @Override
    public String toString() {
        return String.format("Card[ID: %d, UID: %s, Active: %b, UserID: %d, Issued: %s]",
                id, uid, active, userId, issueDate.toString());
    }

    public void setId(int id) {
        this.id = id;
    }
}
