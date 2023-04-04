package gr.aueb.cf.movies.rest;

import gr.aueb.cf.movies.model.Movie;
import gr.aueb.cf.movies.model.User;
import gr.aueb.cf.movies.service.IUserService;
import gr.aueb.cf.movies.service.exceptions.EntityNotFoundException;
import gr.aueb.cf.movies.service.util.JPAHelper;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users/movies")
public class UserMovieController {

    @Inject
    IUserService userService;

    @POST
    @Path("/watchlist/{username}/{title}/{director}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addMovieToWatchlist(
            @PathParam("username") String username,
            @PathParam("title") String title,
            @PathParam("director") String director) throws EntityNotFoundException {

        // Retrieve the user from the database
        User user = userService.getUserByUsername(username);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        // Create a new Movie object with the provided title and director
        Movie movie = new Movie(title, director);
        

        // Add the movie to the user's watchlist
        User updatedUser = userService.getUserById(user.getId());
        updatedUser = userService.addMovieToWatchlist(username,movie);
//        User updatedUser = userService.addMovieToWatchlist(username, movie);

        // Return a JSON response with the updated user
        return Response.ok(updatedUser).build();
    }

}
