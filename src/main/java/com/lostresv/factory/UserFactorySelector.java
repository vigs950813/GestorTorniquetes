package com.lostresv.factory;

import com.lostresv.model.User;

import java.util.Date;

public class UserFactorySelector {

    public static User createUserByType(int id, String name, Date creationDate, String userType) {
        UserFactory factory;

        switch (userType) {
            case "Employee":
                factory = new EmployeeFactory();
                break;
            case "Administrator":
                factory = new AdministratorFactory();
                break;
            default:
                throw new IllegalArgumentException("Unsupported userType: " + userType);
        }

        return factory.createUser(id, name, creationDate, userType);
    }
}
