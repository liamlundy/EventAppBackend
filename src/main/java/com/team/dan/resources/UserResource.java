package com.team.dan.resources;

import com.team.dan.core.User;
import com.team.dan.db.EventDao;
import com.team.dan.db.UserDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Set;

/**
 * Author: Liam Lundy
 * Creation Date: 11/12/15.
 * <p>
 * Package: ${PACKAGE}
 * Project: ${PROJECT}
 */
@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    private final UserDao userDao;

    public UserResource(UserDao userDao) {
        this.userDao = userDao;
    }

    @GET
    @Path("/id/{id}")
    public User getUserById(@PathParam("id") int id) {
        User user = null;
        try {
            user = userDao.getUserById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @POST
    @Path("/create")
    public void createUser(User user) {
        userDao.createNewUser(user.getEmail(), user.getPassword(), user.isAdmin(), user.isValid(), user.getFirstName(),
                user.getLastName());
    }

    @GET
    @Path("/email/{email}")
    public User getUserByEmail(@PathParam("email") String email) {
        User user = null;
        try {
            user = userDao.getUserByEmail(email);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @GET
    @Path("/getall")
    public Set<User> getAllUsers() {
        Set<User> users = null;
        try {
            users = userDao.getAllUsers();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }
}