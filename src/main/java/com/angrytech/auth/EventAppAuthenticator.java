package com.angrytech.auth;

import com.angrytech.core.User;
import com.google.common.base.Optional;
import com.angrytech.db.UserDao;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

/**
 * Author: Liam Lundy
 * Creation Date: 11/18/15.
 * <p>
 * Package: ${PACKAGE}
 * Project: ${PROJECT}
 */
public class EventAppAuthenticator implements Authenticator<BasicCredentials, User> {

    private final UserDao userDao;

    /**
     * Creates a new instance of the authenticator
     * @param userDao the user database interface
     */
    public EventAppAuthenticator(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * Authenticates the user
     * @param basicCredentials A set of credentials including password and email
     * @return Either an User or an empty optional
     * @throws AuthenticationException if there are errors during the method.
     */
    public Optional<User> authenticate(BasicCredentials basicCredentials) throws AuthenticationException {
        User user = userDao.getUserByEmail(basicCredentials.getUsername());
        if (user == null) {
            return Optional.absent();
        }
        if (user.getPassword().equals(basicCredentials.getPassword()))
            return Optional.of(user);
        return Optional.absent();
    }
}
