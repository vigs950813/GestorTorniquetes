package com.lostresv.model;

public class Card {

    private int id;
    private String code;
    private boolean active;

    public Card() {
    }

    public Card(int id, String code, boolean active) {
        this.id = id;
        this.code = code;
        this.active = active;
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", active=" + active +
                '}';
    }
}
