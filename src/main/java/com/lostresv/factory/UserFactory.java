package com.lostresv.factory;

import com.lostresv.model.User;
import java.util.Date;

public interface UserFactory {
    User createUser(int id, String name, Date creationDate, String userType);
}
