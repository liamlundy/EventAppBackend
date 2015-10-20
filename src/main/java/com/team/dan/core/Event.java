package com.team.dan.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import java.sql.Date;

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
    private String photoLocation;

    @JsonProperty
    @NotEmpty
    private String desciption;

    @JsonProperty
    @NotEmpty
    private String title;

    @JsonProperty
    @NotEmpty
    private String locaton;

    @JsonProperty
    @NotEmpty
    private Date dateTime;

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

    public String getPhotoLocation() {
        return photoLocation;
    }

    public void setPhotoLocation(String photoLocation) {
        this.photoLocation = photoLocation;
    }

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocaton() {
        return locaton;
    }

    public void setLocaton(String locaton) {
        this.locaton = locaton;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventId=" + eventId +
                ", authorId=" + authorId +
                ", photoLocation='" + photoLocation + '\'' +
                ", desciption='" + desciption + '\'' +
                ", title='" + title + '\'' +
                ", locaton='" + locaton + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        if (eventId != event.eventId) return false;
        if (authorId != event.authorId) return false;
        if (photoLocation != null ? !photoLocation.equals(event.photoLocation) : event.photoLocation != null)
            return false;
        if (desciption != null ? !desciption.equals(event.desciption) : event.desciption != null) return false;
        if (title != null ? !title.equals(event.title) : event.title != null) return false;
        if (locaton != null ? !locaton.equals(event.locaton) : event.locaton != null) return false;
        return !(dateTime != null ? !dateTime.equals(event.dateTime) : event.dateTime != null);

    }

    @Override
    public int hashCode() {
        int result = eventId;
        result = 31 * result + authorId;
        result = 31 * result + (photoLocation != null ? photoLocation.hashCode() : 0);
        result = 31 * result + (desciption != null ? desciption.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (locaton != null ? locaton.hashCode() : 0);
        result = 31 * result + (dateTime != null ? dateTime.hashCode() : 0);
        return result;
    }
}
