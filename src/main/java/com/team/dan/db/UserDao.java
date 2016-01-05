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

    /**
     * return a user based on their user id
     * @param id the super id
     * @return the user
     */
    @SqlQuery("SELECT * FROM users " +
            "WHERE user_id = :id")
    public User getUserById(@Bind("id") int id);

    /**
     * return a user based on their email
     * @param email the user's email
     * @return the user
     */
    @SqlQuery("SELECT * FROM users " +
            "WHERE email = :email")
    public User getUserByEmail(@Bind("email") String email);

    /**
     * Creates a new user in the database
     * @param email the user email
     * @param password the user password
     * @param isAdmin true if the user is an admin
     * @param isValid true if the user is valid
     * @param firstName the user first name
     * @param lastName the user last name
     */
    @SqlUpdate("INSERT INTO users (email, password, admin, valid, first_name, last_name) \n" +
            "VALUE (:email, :password, :admin, :valid, :firstName, :lastName);\n")
    public void createNewUser(@Bind("email") String email, @Bind("password") String password,
                              @Bind("admin") boolean isAdmin, @Bind("valid") boolean isValid,
                              @Bind("firstName") String firstName, @Bind("lastName") String lastName);

    /**
     * Return all the user
     * @return the set of all users
     */
    @SqlQuery("SELECT * FROM users")
    public Set<User> getAllUsers();

    /**
     * returns the associated password for an email
     * @param email the user email
     * @return the associated password
     */
    @SqlQuery("SELECT password\n" +
            "FROM users\n" +
            "WHERE email = :email")
    public String getPassword(@Bind("email") String email);
}
