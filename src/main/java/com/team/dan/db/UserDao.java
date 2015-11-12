package com.team.dan.db;

import com.team.dan.core.User;
import com.team.dan.mappers.UserMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.Set;

/**
 * Author: Liam Lundy
 * Creation Date: 11/4/15.
 * <p>
 * Package: ${PACKAGE}
 * Project: ${PROJECT}
 */
@RegisterMapper(UserMapper.class)
public interface UserDao {

    @SqlQuery("SELECT * FROM users " +
            "WHERE user_id = :id")
    public User getUserById(@Bind("id") int id);

    @SqlQuery("SELECT * FROM users " +
            "WHERE email = :email")
    public User getUserByEmail(@Bind("email") String email);

    @SqlUpdate("INSERT INTO users (email, password, admin, valid, first_name, last_name) \n" +
            "VALUE (:email, :password, :admin, :valid, :firstName, :lastName);\n")
    public void createNewUser(@Bind("email") String email, @Bind("password") String password,
                              @Bind("admin") boolean isAdmin, @Bind("valid") boolean isValid,
                              @Bind("firstName") String firstName, @Bind("lastName") String lastName);

    @SqlQuery("SELECT * FROM users")
    public Set<User> getAllUsers();
}
