package com.team.dan;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

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
        //Resource registration
        environment.jersey().register(new TestResource());
    }
}


