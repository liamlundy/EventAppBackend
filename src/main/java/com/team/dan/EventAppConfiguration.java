package com.team.dan;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

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

    @JsonProperty("database")
    public DataSourceFactory getDataSourceFactory() {
        return database;
    }
}