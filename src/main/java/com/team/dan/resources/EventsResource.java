package com.team.dan.resources;

import com.team.dan.core.Event;
import com.team.dan.db.EventDao;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.*;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Author: Liam Lundy
 * Creation Date: 11/1/15.
 * <p>
 * Package: ${PACKAGE}
 * Project: ${PROJECT}
 */
@Path("/events")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EventsResource {

    private final EventDao eventDao;

    public EventsResource(EventDao eventDao) {
        this.eventDao = eventDao;
    }

    @GET
    @Path("/weeklyevents")
    public Set<Event> getWeeklyEvents() {
        Set<Event> events = eventDao.getNextWeek();
        return events;
    }

    @GET
    @Path("/allevents")
    public Set<Event> getAllEvents() {
        Set<Event> events = eventDao.getAllEvents();
        return events;
    }

    @DELETE
    @Path("/delete/{id}")
    public void deleteEvent(@PathParam("id") int id) {
        eventDao.deleteEvent(id);
    }

    @POST
    @Path("/create")
    public void createEvent(Event event) {
        eventDao.insertEvent(event.getAuthorId(), event.getPhotoLocation(), event.getDescription(), event.getTitle(),
                event.getLocation(), event.getDate(), event.getTime());
    }

    @GET
    @Path("/id/{id}")
    public Event getEvent(@PathParam("id") int id) {
        Event event = eventDao.getEvent(id);
        return event;
    }

    @POST
    @Path("/uploadPhoto")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public void fileUploaded(@FormDataParam("file") final InputStream inputStream,
                             @FormDataParam("file") final FormDataContentDisposition contentDispositionHeader) {
        InputStream stream = (inputStream);
    }
}