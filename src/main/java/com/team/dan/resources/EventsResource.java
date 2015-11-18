package com.team.dan.resources;

import com.sun.imageio.plugins.common.ImageUtil;
import com.team.dan.core.Event;
import com.team.dan.db.EventDao;

import javax.annotation.security.PermitAll;
import javax.imageio.ImageIO;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

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

    @PermitAll
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

    @GET
    @Path("/getImage/{eventId}")
    @Produces({"images/gif", "images/png", "images/jpg" })
    public Response getEventImage(@PathParam("eventId") int id) {

        String path = eventDao.getEventPhotoPath(id);

        BufferedImage image = getImage(path);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "png", baos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] imageData = baos.toByteArray();

        if (imageData != null)
            return Response.ok(imageData).build();

        return Response.noContent().build();
    }

    private BufferedImage getImage(String filename) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(filename));
        } catch (IOException e) {
        }
        return img;
    }
}