package com.team.dan.mappers;

import com.team.dan.core.Event;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Author: Liam Lundy
 * Creation Date: 10/19/15.
 * <p>
 * Package: ${PACKAGE}
 * Project: ${PROJECT}
 */
public class EventMapper implements ResultSetMapper<Event> {
    public Event map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        Event event = new Event();
        event.setAuthorId(resultSet.getInt("author_id"));
        event.setDate(resultSet.getDate("date"));
        event.setTime(resultSet.getTime("time"));
        event.setDescription(resultSet.getString("description"));
        event.setEventId(resultSet.getInt("event_id"));
        event.setTitle(resultSet.getString("title"));
        event.setLocation(resultSet.getString("location"));
        event.setImageExt(resultSet.getString("image_ext"));
        return event;
    }
}
