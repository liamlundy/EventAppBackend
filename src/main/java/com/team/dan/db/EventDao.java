package com.team.dan.db;

import com.team.dan.core.Event;
import com.team.dan.mappers.EventMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.sql.Date;
import java.sql.Time;
import java.util.Set;

/**
 * Author: Liam Lundy
 * Creation Date: 10/19/15.
 * <p>
 * Package: ${PACKAGE}
 * Project: ${PROJECT}
 */
@RegisterMapper(EventMapper.class)
public interface EventDao {

    /**
     * returns an event from the db based on id
     * @param id the event id
     * @return the specified event
     */
    @SqlQuery("SELECT * FROM events " +
            "WHERE event_id = :id")
    public Event getEvent(@Bind("id") int id);

    /**
     * returns the set of events that will occur in the next few weeks.
     * @return a set of events that will occur within the next week
     */
    @SqlQuery("SELECT *\n" +
            "FROM events\n" +
            "WHERE date > NOW() AND\n" +
            "      date < DATE_ADD(NOW(), INTERVAL 1 WEEK)")
    public Set<Event> getNextWeek();

    /**
     * returns all the events
     * @return the set of all events
     */
    @SqlQuery("SELECT *\n" +
            "FROM events")
    public Set<Event> getAllEvents();

    /**
     * gets the path of a photo given that photos id
     * @param id the id of the event
     * @return the path to the photo
     */
    @SqlQuery("SELECT photo_loc\n" +
            "FROM events\n" +
            "WHERE event_id = :id")
    public String getEventPhotoPath(@Bind("id") int id);

    /**
     * deletes an event from the database
     * @param id the id of the event to delete
     */
    @SqlUpdate("DELETE FROM events\n" +
            "WHERE event_id = :id")
    public void deleteEvent(@Bind("id") int id);

    /**
     * Inserts a new event into the db
     * @param authorId the user id of the author
     * @param photoLocation the location of the event photo
     * @param description the description of the event.
     * @param title the title of the event
     * @param location the event's location
     * @param date the date of the event in SQL date format
     * @param time the time of the event in SQL time format
     */
    @SqlUpdate("INSERT INTO events (author_id, photo_loc, description, title, location, date, time)\n" +
            "VALUE(:authorId, :photoLocation, :description, :title, :location, :date, :time)")
    public void insertEvent(@Bind("authorId") int authorId, @Bind("photoLocation") String photoLocation,
                            @Bind("description") String description, @Bind("title") String title,
                            @Bind("location") String location, @Bind("date") Date date, @Bind("time") Time time);
}