package com.lostresv.factory;

import com.lostresv.model.Employee;
import com.lostresv.model.User;
import com.lostresv.model.Card;

import java.util.Date;

public class EmployeeFactory implements UserFactory {
    @Override
    public User createUser(int id, String name, Date creationDate, String userType) {
        // Password and username will be loaded separately (e.g., via EmployeeDAO)
        return new Employee(id, name, creationDate, null, "", "");
    }
}
