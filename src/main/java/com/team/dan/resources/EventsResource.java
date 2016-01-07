package com.team.dan.resources;

import com.team.dan.core.Event;
import com.team.dan.db.EventDao;
import org.apache.commons.io.FilenameUtils;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.annotation.security.PermitAll;
import javax.imageio.ImageIO;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
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
    public Response getWeeklyEvents() {
        Set<Event> events = eventDao.getNextWeek();
        return Response.ok(events).build();
    }

    @GET
    @Path("/allevents")
    public Response getAllEvents() {
        Set<Event> events = eventDao.getAllEvents();
        return Response.ok(events).build();
    }

    @DELETE
    @Path("/delete/{id}")
    public void deleteEvent(@PathParam("id") int id) {
        eventDao.deleteEvent(id);
    }

    @GET
    @Path("/id/{id}")
    public Response getEvent(@PathParam("id") int id) {
        Event event = eventDao.getEvent(id);
        return Response.ok(event).build();
    }

    @PermitAll
    @POST
    @Path("/create")
    public void createEvent(Event event) {
        eventDao.insertEvent(event.getAuthorId(), event.getPhotoLocation(), event.getDescription(), event.getTitle(),
                event.getLocation(), event.getDate(), event.getTime());
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

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Path("/insert")
    public Response fileUpload(@FormDataParam("image") final InputStream inputStream,
                               @FormDataParam("image") final FormDataContentDisposition contentDispositionHeader) {
        String imageExt = FilenameUtils.getExtension(contentDispositionHeader.getFileName());
        try {
            saveImage(inputStream, String.valueOf(1), imageExt);
            saveImageThumb(String.valueOf(1), imageExt);
        } catch (IOException e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
        return Response.ok().build();
    }

    private void saveImage(InputStream inputStream, String fileName, String imageExt) throws IOException {
        OutputStream os = new FileOutputStream("/home/liam/Documents/Web_Development/Projects/EventAppBackend/photos" + fileName + "." + imageExt);
        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            os.write(buffer, 0, bytesRead);
        }
        inputStream.close();
        os.close();
    }

    private void saveImageThumb(String fileName, String imageExt) {
        try {
            BufferedImage bufferedImage = ImageIO.read(new File("/home/liam/Documents/Web_Development/Projects/EventAppBackend/photos" + fileName + "." + imageExt));
            double ratio = (double) bufferedImage.getWidth() / (double) bufferedImage.getHeight();
            double scaledHeight = 150;
            double scaledWidth = ratio * scaledHeight;

            // creates output image
            BufferedImage outputImage = new BufferedImage((int) scaledWidth,
                    (int) scaledHeight, bufferedImage.getType());

            // scales the input image to the output image
            Graphics2D g2d = outputImage.createGraphics();
            g2d.drawImage(bufferedImage, 0, 0, (int) scaledWidth, (int) scaledHeight, null);
            g2d.dispose();

            // writes to output file
            ImageIO.write(outputImage, imageExt, new File("/home/liam/Documents/Web_Development/Projects/EventAppBackend/photos" + fileName + "." + imageExt));

        } catch (IOException e) {
            e.printStackTrace();
        }
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