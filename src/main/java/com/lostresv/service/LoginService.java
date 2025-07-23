package com.lostresv.service;

import com.lostresv.dao.EmployeeDAO;
import com.lostresv.dao.AdministratorDAO;
import com.lostresv.dao.CardDAO;
import com.lostresv.model.Employee;
import com.lostresv.model.Administrator;
import com.lostresv.model.Card;
import com.lostresv.model.User;

import java.sql.Connection;

public class LoginService {

    private final EmployeeDAO employeeDAO;
    private final AdministratorDAO administratorDAO;
    private final CardDAO cardDAO;

    public LoginService(Connection connection) {
        this.employeeDAO = new EmployeeDAO(connection);
        this.administratorDAO = new AdministratorDAO(connection);
        this.cardDAO = new CardDAO(connection);
    }

    public User authenticate(String username, String password) throws Exception {
        // Try Employee
        Employee employee = employeeDAO.findByUsername(username);
        if (employee != null && employee.getPassword().equals(password)) {
            Card card = cardDAO.findByUserId(employee.getId());
            employee.setCard(card);
            return employee;
        }

        // Try Administrator
        Administrator admin = administratorDAO.findByUsername(username);
        if (admin != null && admin.getPassword().equals(password)) {
            Card card = cardDAO.findByUserId(admin.getId());
            admin.setCard(card);
            return admin;
        }

        // No match
        return null;
    }
}
