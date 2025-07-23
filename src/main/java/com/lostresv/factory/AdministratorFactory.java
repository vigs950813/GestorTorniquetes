package com.lostresv.factory;

import com.lostresv.model.Administrator;
import com.lostresv.model.User;
import com.lostresv.model.Card;

import java.util.Date;

public class AdministratorFactory implements UserFactory {
    @Override
    public User createUser(int id, String name, Date creationDate, String userType) {
        return new Administrator(id, name, creationDate, null, "", "");
    }
}
