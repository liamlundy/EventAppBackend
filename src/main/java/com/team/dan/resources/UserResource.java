package com.team.dan.resources;

import com.team.dan.core.User;
import com.team.dan.db.EventDao;
import com.team.dan.db.UserDao;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ReaderInterceptor;
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

    /**
     * Return the User based on the user id
     * @param id the user id
     * @return the User
     */
    @GET
    @Path("/id/{id}")
    public Response getUserById(@PathParam("id") int id) {
        User user;
        try {
            user = userDao.getUserById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
        if (user != null)
            return Response.ok(user).build();
        else
            return Response.noContent().build();
    }

    /**
     * Creates a new user
     * @param user the User to create
     */
    @POST
    @Path("/create")
    public Response createUser(User user) {
        try {
            userDao.createNewUser(user.getEmail(), user.getPassword(), user.isAdmin(), user.isValid(), user.getFirstName(),
                    user.getLastName());
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
        return Response.ok().build();
    }

    /**
     * Return the User based on the user email
     * @param email the user email
     * @return the User
     */
    @GET
    @Path("/email/{email}")
    public Response getUserByEmail(@PathParam("email") String email) {
        User user;
        try {
            user = userDao.getUserByEmail(email);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
        if(user != null)
            return Response.ok(user).build();
        else
            return Response.noContent().build();
    }

    /**
     * Returns the list of all users
     * @return a Set of all the Users
     */
    @RolesAllowed("ADMIN")
    @GET
    @Path("/getall")
    public Response getAllUsers() {
        Set<User> users = null;
        try {
            users = userDao.getAllUsers();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
        return Response.ok(users).build();
    }

    /**
     * Checks the User's credentials and returns the user if they are valid
     * @param userCredentials the User containing only password and email
     * @return the User or null if there credentials are invalid.
     */
    @POST
    @Path("/login")
    public Response login(User userCredentials) {
        User user = null;
        String password = userDao.getPassword(userCredentials.getEmail());
        if (password.equals(userCredentials.getPassword())) {
            user = userDao.getUserByEmail(userCredentials.getEmail());
        }
        return Response.ok(user).build();
    }
}
