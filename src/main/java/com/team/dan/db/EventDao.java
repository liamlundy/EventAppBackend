package com.team.dan.db;

import com.team.dan.core.Event;
import com.team.dan.mappers.EventMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

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

    @SqlQuery("SELECT * FROM events " +
            "WHERE date_time > ")
    public Event getNextWeek();
}