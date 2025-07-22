package com.lostresv.model;

import java.util.Date;
import java.util.List;

public class Visitor extends User {

    private List<Integer> allowedTurnstiles;

    public Visitor(int id, String name, Date creationDate, Card card, List<Integer> allowedTurnstiles) {
        super(id, name, creationDate, "Visitor", card);
        this.allowedTurnstiles = allowedTurnstiles;
    }

    public List<Integer> getAllowedTurnstiles() {
        return allowedTurnstiles;
    }

    public void setAllowedTurnstiles(List<Integer> allowedTurnstiles) {
        this.allowedTurnstiles = allowedTurnstiles;
    }
}
