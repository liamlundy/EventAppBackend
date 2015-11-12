package com.team.dan.mappers;

import com.team.dan.core.Event;
import com.team.dan.core.User;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Author: Liam Lundy
 * Creation Date: 10/19/15.
 * <p>
 * Package: ${PACKAGE}
 * Project: ${PROJECT}
 */
public class UserMapper implements ResultSetMapper<User> {
    public User map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        User user = new User();
        user.setUserId(resultSet.getInt("user_id"));
        user.setEmail(resultSet.getString("email"));
        user.setIsAdmin(resultSet.getBoolean("admin"));
        user.setIsValid(resultSet.getBoolean("valid"));
        user.setFirstName(resultSet.getString("first_name"));
        user.setLastName(resultSet.getString("last_name"));
        user.setPassword(resultSet.getString("password"));
        return user;
    }
}
