package com.team.dan.auth;

import com.team.dan.core.User;
import com.team.dan.db.UserDao;
import io.dropwizard.auth.Authorizer;

/**
 * Author: Liam Lundy
 * Creation Date: 11/18/15.
 * <p>
 * Package: ${PACKAGE}
 * Project: ${PROJECT}
 */
public class EventAppAuthorizer implements Authorizer<User> {

    private final UserDao userDao;

    public EventAppAuthorizer(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean authorize(User user, String role) {
        if (role.equals("ADMIN") && user.isAdmin()) {
            return true;
        } else if (role.equals("USER")) {
            return true;
        }
        return false;
    }
}
