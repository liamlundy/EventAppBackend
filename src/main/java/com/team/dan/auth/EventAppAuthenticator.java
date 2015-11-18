package com.team.dan.auth;

import com.google.common.base.Optional;
import com.team.dan.core.User;
import com.team.dan.db.UserDao;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * Author: Liam Lundy
 * Creation Date: 11/18/15.
 * <p>
 * Package: ${PACKAGE}
 * Project: ${PROJECT}
 */
public class EventAppAuthenticator implements Authenticator<BasicCredentials, User> {

    private final UserDao userDao;

    public EventAppAuthenticator(UserDao userDao) {
        this.userDao = userDao;
    }

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
