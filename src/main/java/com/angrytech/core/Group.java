package com.angrytech.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Author: Liam Lundy
 * Creation Date: 2/1/16.
 * <p>
 * Package: ${PACKAGE}
 * Project: ${PROJECT}
 */
public class Group {

    @JsonProperty
    @NotEmpty
    private int groupId;

    @JsonProperty
    @NotEmpty
    private String title;

    @JsonProperty
    @NotEmpty
    private String description;

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Group group = (Group) o;

        return new EqualsBuilder()
                .append(groupId, group.groupId)
                .append(title, group.title)
                .append(description, group.description)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(groupId)
                .append(title)
                .append(description)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "Group{" +
                "groupId=" + groupId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
