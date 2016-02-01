package com.angrytech;

import com.angrytech.auth.EventAppAuthenticator;
import com.angrytech.core.User;
import com.angrytech.resources.EventsResource;
import com.angrytech.resources.TestResource;
import com.angrytech.auth.EventAppAuthorizer;
import com.angrytech.db.EventDao;
import com.angrytech.db.UserDao;
import com.angrytech.resources.UserResource;
import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Environment;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.skife.jdbi.v2.DBI;

/**
 * Author: Liam Lundy
 * Creation Date: 10/13/15.
 * <p>
 * Package: ${PACKAGE}
 * Project: ${PROJECT}
 */
public class EventAppApplication extends Application<EventAppConfiguration>{

    /**
     * The main method for the application
     * @param args the passed in arguments
     * @throws Exception a generic exception
     */
    public static void main(String[] args) throws Exception {
        new EventAppApplication().run(args);
    }

    /**
     * The main run method of the app
     * @param eventAppConfiguration the event app configuration class
     * @param environment the environment
     * @throws Exception a generic exception
     */
    @Override
    public void run(EventAppConfiguration eventAppConfiguration, Environment environment) throws Exception {
        //Database set up
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, eventAppConfiguration.getDataSourceFactory(), "mysql");

        //Database Access Objects
        final EventDao eventDao = jdbi.onDemand(EventDao.class);
        final UserDao userDao = jdbi.onDemand(UserDao.class);

        //Resource registration
        environment.jersey().register(new TestResource(eventDao));
        environment.jersey().register(new EventsResource(eventDao, eventAppConfiguration.getPhotoLocation()));
        environment.jersey().register(new UserResource(userDao));

        //Register Multi Part file upload class
        environment.jersey().register(MultiPartFeature.class);

        //authentication registration
        environment.jersey().register(new AuthDynamicFeature(
                new BasicCredentialAuthFilter.Builder<User>()
                        .setAuthenticator(new EventAppAuthenticator(userDao))
                        .setAuthorizer(new EventAppAuthorizer(userDao))
                        .setRealm("SUPER SECRET STUFF")
                        .buildAuthFilter()));
        environment.jersey().register(RolesAllowedDynamicFeature.class);
        environment.jersey().register(new AuthValueFactoryProvider.Binder(User.class));

    }
}


