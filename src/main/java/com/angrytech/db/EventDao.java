package com.angrytech.db;

import com.angrytech.core.Event;
import com.angrytech.mappers.EventMapper;
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
     *
     * @param id the event id
     * @return the specified event
     */
    @SqlQuery("SELECT * FROM events " +
            "WHERE event_id = :id")
    public Event getEvent(@Bind("id") int id);

    /**
     * returns the set of events that will occur in the next few weeks.
     *
     * @return a set of events that will occur within the next week
     */
    @SqlQuery("SELECT *\n" +
            "FROM events\n" +
            "WHERE date > NOW() AND\n" +
            "      date < DATE_ADD(NOW(), INTERVAL 1 WEEK)")
    public Set<Event> getNextWeek();

    @SqlQuery("SELECT *\n" +
            "FROM events\n" +
            "  INNER JOIN group_event\n" +
            "    ON events.event_id = group_event.event_id\n" +
            "WHERE group_id = :group_id AND\n" +
            "      date > NOW();")
    public Set<Event> getEventsByGroup(@Bind("group_id") int groupId);

    /**
     * returns all the events
     *
     * @return the set of all events
     */
    @SqlQuery("SELECT *\n" +
            "FROM events")
    public Set<Event> getAllEvents();

    /**
     * Get the image extension/type for the event
     *
     * @param id The event id
     * @return The image extension/type
     */
    @SqlQuery("SELECT image_ext\n" +
            "FROM events\n" +
            "WHERE event_id = :id")
    public String getImageExt(@Bind("id") int id);

    /**
     * deletes an event from the database
     *
     * @param id the id of the event to delete
     */
    @SqlUpdate("DELETE FROM events\n" +
            "WHERE event_id = :id")
    public void deleteEvent(@Bind("id") int id);

    /**
     * Inserts a new event into the db
     *
     * @param authorId    the user id of the author
     * @param description the description of the event.
     * @param title       the title of the event
     * @param location    the event's location
     * @param date        the date of the event in SQL date format
     * @param time        the time of the event in SQL time format
     */
    @SqlUpdate("INSERT INTO events (author_id, description, title, location, date, time, image_ext)\n" +
            "VALUE(:authorId, :description, :title, :location, :date, :time, :imageExt)")
    public void insertEvent(@Bind("authorId") int authorId, @Bind("description") String description,
                            @Bind("title") String title, @Bind("location") String location, @Bind("date") Date date,
                            @Bind("time") Time time, @Bind("imageExt") String imageExt);

    /**
     * Gets the id of the event that was just inserted
     *
     * @return the most recent event id
     */
    @SqlQuery("SELECT LAST_INSERT_ID();")
    public int getLastInsertedPieceId();
}