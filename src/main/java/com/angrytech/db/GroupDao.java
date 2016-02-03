package com.angrytech.db;

import com.angrytech.core.Group;
import com.angrytech.mappers.GroupMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.Set;

/**
 * Author: Liam Lundy
 * Creation Date: 2/1/16.
 * <p>
 * Package: ${PACKAGE}
 * Project: ${PROJECT}
 */
@RegisterMapper(GroupMapper.class)
public interface GroupDao {

    @SqlQuery("SELECT *\n" +
            "FROM groups")
    public Set<Group> getAll();

    @SqlQuery("SELECT *\n" +
            "FROM groups\n" +
            "WHERE group_id = :id")
    public Group getById(@Bind("id") int id);

    @SqlUpdate("INSERT INTO groups (title, description) VALUES (:title, :description);")
    public void insert(@Bind("title") String title, @Bind("description") String description);

    @SqlUpdate("INSERT INTO group_event(group_id, event_id) VALUES (:groupId, :eventId);")
    public void associate(@Bind("groupId") int groupId, @Bind("eventId") int eventId);
}
