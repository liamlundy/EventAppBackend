package com.angrytech.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import java.sql.Date;
import java.sql.Time;

/**
 * Author: Liam Lundy
 * Creation Date: 10/19/15.
 * <p>
 * Package: ${PACKAGE}
 * Project: ${PROJECT}
 */
public class Event {

    @JsonProperty
    @NotEmpty
    private int eventId;

    @JsonProperty
    @NotEmpty
    private int authorId;

    @JsonProperty
    @NotEmpty
    private String description;

    @JsonProperty
    @NotEmpty
    private String title;

    @JsonProperty
    @NotEmpty
    private String location;

    @JsonProperty
    @NotEmpty
    private Date date;

    @JsonProperty
    @NotEmpty
    private Time time;

    @JsonProperty
    @NotEmpty
    private String imageExt;

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getImageExt() {
        return imageExt;
    }

    public void setImageExt(String imageExt) {
        this.imageExt = imageExt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        if (eventId != event.eventId) return false;
        if (authorId != event.authorId) return false;
        if (!description.equals(event.description)) return false;
        if (!title.equals(event.title)) return false;
        if (!location.equals(event.location)) return false;
        if (!date.equals(event.date)) return false;
        if (!time.equals(event.time)) return false;
        return imageExt.equals(event.imageExt);

    }

    @Override
    public int hashCode() {
        int result = eventId;
        result = 31 * result + authorId;
        result = 31 * result + description.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + location.hashCode();
        result = 31 * result + date.hashCode();
        result = 31 * result + time.hashCode();
        result = 31 * result + imageExt.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventId=" + eventId +
                ", authorId=" + authorId +
                ", description='" + description + '\'' +
                ", title='" + title + '\'' +
                ", location='" + location + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", imageExt='" + imageExt + '\'' +
                '}';
    }
}
