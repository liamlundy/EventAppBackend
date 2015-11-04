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
    @SqlQuery("SELECT * FROM events " +
            "WHERE event_id = :id")
    public Event getEvent(@Bind("id") int id);

    @SqlQuery("SELECT *\n" +
            "FROM events\n" +
            "WHERE date > NOW() AND\n" +
            "      date < DATE_ADD(NOW(), INTERVAL 1 WEEK)")
    public Set<Event> getNextWeek();

    @SqlUpdate("DELETE FROM events\n" +
            "WHERE event_id = :id")
    public void deleteEvent(@Bind("id") int id);

    @SqlUpdate("INSERT INTO events (author_id, photo_loc, description, title, location, date, time)\n" +
            "VALUE(:authorId, :photoLocation, :description, :title, :location, :date, :time)")
    public void insertEvent(@Bind("authorId") int authorId, @Bind("photoLocation") String photoLocation,
                            @Bind("description") String description, @Bind("title") String title,
                            @Bind("location") String location, @Bind("date") Date date, @Bind("time") Time time);
}