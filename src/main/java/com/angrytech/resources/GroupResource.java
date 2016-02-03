package com.angrytech.resources;

import com.angrytech.core.Group;
import com.angrytech.db.GroupDao;

import javax.annotation.security.PermitAll;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Set;

/**
 * Author: Liam Lundy
 * Creation Date: 2/3/16.
 * <p>
 * Package: ${PACKAGE}
 * Project: ${PROJECT}
 */
@Path("group")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GroupResource {

    private final GroupDao groupDao;


    public GroupResource(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    @PermitAll
    @POST
    @Path("create")
    public Response createGroup(Group group) {
        try {
            groupDao.insert(group.getTitle(), group.getDescription());
            return Response.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @GET
    @Path("all")
    public Response getAllGroups() {
        try {
            Set<Group> groups = groupDao.getAll();
            return Response.ok(groups).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @POST
    @Path("associate")
    public Response associateGroupEvent(@QueryParam("groupId") int groupId, @QueryParam("eventId") int eventId) {
        try {
            groupDao.associate(groupId, eventId);
            return Response.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }
}
