package gr.aueb.cf.movies;

import gr.aueb.cf.movies.rest.*;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.jboss.resteasy.plugins.interceptors.CorsFilter;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.FeatureContext;
import java.util.HashSet;
import java.util.Set;

/**
 * The root application class for the Movies API.
 */
@ApplicationPath("/api")
public class HelloApplication extends Application {

    /**
     * Retrieves the set of resource classes that are part of this application.
     *
     * @return The set of resource classes
     */
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(LoginController.class);
        classes.add(MovieController.class);
        classes.add(AddMovieToWatchlistController.class);
        classes.add(GetMoviesByUsernameController.class);
        classes.add(RemoveMovieFromWatchlistController.class);
        return classes;
    }

    /**
     * Retrieves the set of singleton objects that are part of this application.
     *
     * @return The set of singleton objects
     */
    @Override
    public Set<Object> getSingletons() {
        Set<Object> singletons = new HashSet<>();
        singletons.add(new JacksonFeature());
        singletons.add(new CorsFeature());
        return singletons;
    }

    /**
     * Implementation of a dynamic feature for configuring Cross-Origin Resource Sharing (CORS) filter.
     */
    public static class CorsFeature implements DynamicFeature {

        /**
         * Configures the CORS filter for a resource.
         *
         * @param resourceInfo The resource information
         * @param context The feature context
         */
        @Override
        public void configure(ResourceInfo resourceInfo, FeatureContext context) {
            CorsFilter corsFilter = new CorsFilter();
            corsFilter.getAllowedOrigins().add("*");
            corsFilter.setAllowedHeaders("origin, content-type, accept, authorization");
            corsFilter.setAllowedMethods("GET, POST, PUT, DELETE, OPTIONS, HEAD");

            // set the Access-Control-Allow-Origin header
            corsFilter.getAllowedOrigins().add("http://127.0.0.1:5500");
            corsFilter.setAllowCredentials(true);

            context.register(corsFilter);
        }
    }
}
