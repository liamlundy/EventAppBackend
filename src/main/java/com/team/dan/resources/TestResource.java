package com.team.dan.resources;

import com.team.dan.core.Event;
import com.team.dan.db.EventDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Author: Liam Lundy
 * Creation Date: 10/15/15.
 * <p>
 * Package: ${PACKAGE}
 * Project: ${PROJECT}
 */
@Path("/test")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TestResource {

    private final EventDao eventDao;

    public TestResource(EventDao eventDao) {
        this.eventDao = eventDao;
    }

    @GET
    @Path("name")
    public String firstResource() {
        return String.format("Hey there, Person. You know the secret!");
    }

    @GET
    @Path("/event/{id}")
    public Event getEvent(@PathParam("id") int id) {
        Event event = eventDao.getEvent(id);
        return event;
    }
}
