package com.angrytech.mappers;

import com.angrytech.core.Group;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Author: Liam Lundy
 * Creation Date: 2/1/16.
 * <p>
 * Package: ${PACKAGE}
 * Project: ${PROJECT}
 */
public class GroupMapper implements ResultSetMapper<Group> {
    public Group map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        Group group = new Group();
        group.setGroupId(resultSet.getInt("group_id"));
        group.setTitle(resultSet.getString("title"));
        group.setDescription(resultSet.getString("description"));
        return group;
    }
}
