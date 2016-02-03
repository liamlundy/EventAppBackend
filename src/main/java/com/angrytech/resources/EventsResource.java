package com.angrytech.resources;

import com.angrytech.core.Event;
import com.angrytech.core.Group;
import com.angrytech.db.EventDao;
import com.angrytech.db.GroupDao;
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
import java.sql.Date;
import java.sql.Time;
import java.util.Set;


/**
 * Author: Liam Lundy
 * Creation Date: 11/1/15.
 * <p>
 * Package: ${PACKAGE}
 * Project: ${PROJECT}
 */
@Path("events")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EventsResource {

    private final EventDao eventDao;
    private final String photoLocationFull;
    private final String photoLocationThumb;

    /**
     *
     * @param eventDao
     * @param photoLocation
     */
    public EventsResource(EventDao eventDao, String photoLocation) {
        this.eventDao = eventDao;
        File baseDir = new File(photoLocation);
        if (!baseDir.exists()) {
            baseDir.mkdir();
        }
        this.photoLocationFull = String.format("%sfull/", photoLocation);
        File fullDir = new File(photoLocationFull);
        if (!fullDir.exists()) {
            fullDir.mkdir();
        }
        this.photoLocationThumb = String.format("%sthumb/", photoLocation);
        File thumbDir = new File(photoLocationThumb);
        if (!thumbDir.exists()) {
            thumbDir.mkdir();
        }
    }

    /**
     *
     * @return
     */
    @GET
    @Path("weeklyevents")
    public Response getWeeklyEvents() {
        Set<Event> events = eventDao.getNextWeek();
        return Response.ok(events).build();
    }

    /**
     *
     * @return
     */
    @GET
    @Path("allevents")
    public Response getAllEvents() {
        Set<Event> events = eventDao.getAllEvents();
        return Response.ok(events).build();
    }

    /**
     *
     * @param id
     * @return
     */
    @GET
    @Path("byGroup/{group_id}")
    public Response getEventsByGroup(@PathParam("group_id") int id) {
        Set<Event> events = eventDao.getEventsByGroup(id);
        return Response.ok(events).build();
    }

    /**
     *
     * @param id
     * @return
     */
    @DELETE
    @Path("delete/{id}")
    public Response deleteEvent(@PathParam("id") int id) {
        try {
            eventDao.deleteEvent(id);
            return Response.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    /**
     *
     * @param id
     * @return
     */
    @GET
    @Path("id/{id}")
    public Response getEvent(@PathParam("id") int id) {
        Event event = eventDao.getEvent(id);
        return Response.ok(event).build();
    }

    /**
     *
     * @param id
     * @return
     */
    @GET
    @Path("getImage/{event_id}")
    @Produces({"images/gif", "images/png", "images/jpg"})
    public Response getEventImage(@PathParam("event_id") int id) {

        String imageExt = eventDao.getImageExt(id);

        BufferedImage image = getImage(String.format("%s%d.%s", photoLocationFull, id, imageExt));

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, imageExt, baos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] imageData = baos.toByteArray();

        if (imageData != null)
            return Response.ok(imageData).build();

        return Response.noContent().build();
    }

    /**
     *
     * @param id
     * @return
     */
    @GET
    @Path("getImageThumb/{event_id}")
    @Produces({"images/gif", "images/png", "images/jpg" })
    public Response getEventImageThumb(@PathParam("event_id") int id) {

        String imageExt = eventDao.getImageExt(id);

        BufferedImage image = getImage(String.format("%s%d.%s", photoLocationThumb, id, imageExt));

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, imageExt, baos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] imageData = baos.toByteArray();

        if (imageData != null)
            return Response.ok(imageData).build();

        return Response.noContent().build();
    }

    /**
     *
     * @param inputStream
     * @param contentDispositionHeader
     * @param authorId
     * @param description
     * @param title
     * @param location
     * @param date
     * @param time
     * @return
     */
    @PermitAll
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @POST
    @Path("create")
    public Response createEvent(@FormDataParam("image") final InputStream inputStream,
                                @FormDataParam("image") final FormDataContentDisposition contentDispositionHeader,
                                @FormDataParam("author_id") final int authorId,
                                @FormDataParam("description") final String description,
                                @FormDataParam("title") final String title,
                                @FormDataParam("location") final String location,
                                @FormDataParam("date") final Date date,
                                @FormDataParam("time") final Time time) {
        String imageExt = FilenameUtils.getExtension(contentDispositionHeader.getFileName());
        eventDao.insertEvent(authorId, description, title, location, date, time, imageExt);
        int eventId = eventDao.getLastInsertedPieceId();

        try {
            saveImage(inputStream, String.valueOf(eventId), imageExt);
            saveImageThumb(String.valueOf(eventId), imageExt);
        } catch (IOException e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
        return Response.ok(eventId).build();
    }

    /**
     *
     * @param inputStream
     * @param fileName
     * @param imageExt
     * @throws IOException
     */
    private void saveImage(InputStream inputStream, String fileName, String imageExt) throws IOException {
        OutputStream os = new FileOutputStream(String.format("%s%s.%s", photoLocationFull, fileName, imageExt));
        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            os.write(buffer, 0, bytesRead);
        }
        inputStream.close();
        os.close();
    }

    /**
     *
     * @param fileName
     * @param imageExt
     */
    private void saveImageThumb(String fileName, String imageExt) {
        try {
            BufferedImage bufferedImage = ImageIO.read(new File(String.format("%s%s.%s", photoLocationFull, fileName, imageExt)));
            double scaledHeight = 200;
            double scaledWidth = 200;

            // creates output image
            BufferedImage outputImage = new BufferedImage((int) scaledWidth,
                    (int) scaledHeight, bufferedImage.getType());

            // scales the input image to the output image
            Graphics2D g2d = outputImage.createGraphics();
            g2d.drawImage(bufferedImage, 0, 0, (int) scaledWidth, (int) scaledHeight, null);
            g2d.dispose();

            // writes to output file
            ImageIO.write(outputImage, imageExt, new File(String.format("%s%s.%s", photoLocationThumb, fileName, imageExt)));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param filename
     * @return
     */
    private BufferedImage getImage(String filename) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }
}