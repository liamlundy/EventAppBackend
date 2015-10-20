package com.team.dan;

import com.team.dan.core.Event;
import com.team.dan.db.EventDao;
import com.team.dan.resources.TestResource;
import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;

/**
 * Author: Liam Lundy
 * Creation Date: 10/13/15.
 * <p>
 * Package: ${PACKAGE}
 * Project: ${PROJECT}
 */
public class EventAppApplication extends Application<EventAppConfiguration>{

    public static void main(String[] args) throws Exception {
        new EventAppApplication().run(args);
    }

    @Override
    public void run(EventAppConfiguration eventAppConfiguration, Environment environment) throws Exception {
        //Database set up
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, eventAppConfiguration.getDataSourceFactory(), "mysql");

        //DAOs
        final EventDao eventDao = jdbi.onDemand(EventDao.class);

        //Resource registration
        environment.jersey().register(new TestResource(eventDao));
    }
}


