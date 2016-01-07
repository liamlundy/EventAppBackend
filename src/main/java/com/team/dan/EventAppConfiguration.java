package com.team.dan;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Author: Liam Lundy
 * Creation Date: 10/13/15.
 */
public class EventAppConfiguration extends Configuration  {
    @Valid
    @NotNull
    @JsonProperty
    private DataSourceFactory database =  new DataSourceFactory();

    @NotEmpty
    private String photoLocation;

    /**
     * return the database
     * @return
     */
    @JsonProperty("database")
    public DataSourceFactory getDataSourceFactory() {
        return database;
    }

    /**
     * Return the path of the photo folder
     * @return The photo directory path
     */
    @JsonProperty
    public String getPhotoLocation() {
        return photoLocation;
    }
}