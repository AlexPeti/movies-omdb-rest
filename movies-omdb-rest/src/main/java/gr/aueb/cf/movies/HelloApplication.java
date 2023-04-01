package gr.aueb.cf.movies;

import gr.aueb.cf.movies.rest.CurrentUserController;
import gr.aueb.cf.movies.rest.LoginController;
import gr.aueb.cf.movies.rest.MovieController;
import gr.aueb.cf.movies.rest.UserMovieController;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.jboss.resteasy.plugins.interceptors.CorsFilter;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.FeatureContext;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api")
public class HelloApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(CurrentUserController.class);
        classes.add(LoginController.class);
        classes.add(MovieController.class);
        classes.add(UserMovieController.class);
        return classes;
    }

    @Override
    public Set<Object> getSingletons() {
        Set<Object> singletons = new HashSet<>();
        singletons.add(new JacksonFeature());
        singletons.add(new CorsFeature());
        return singletons;
    }

    public static class CorsFeature implements DynamicFeature {
        @Override
        public void configure(ResourceInfo resourceInfo, FeatureContext context) {
            CorsFilter corsFilter = new CorsFilter();
            corsFilter.getAllowedOrigins().add("*");
            corsFilter.setAllowedHeaders("origin, content-type, accept, authorization");
            corsFilter.setAllowedMethods("GET, POST, PUT, DELETE, OPTIONS, HEAD");
            context.register(corsFilter);
        }
    }
}
