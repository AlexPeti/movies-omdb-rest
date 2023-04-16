package gr.aueb.cf.movies.rest;

import gr.aueb.cf.movies.model.Movie;
import gr.aueb.cf.movies.model.User;
import gr.aueb.cf.movies.service.IUserService;
import gr.aueb.cf.movies.service.exceptions.EntityNotFoundException;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 * This class represents a RESTful web service endpoint for adding a movie to a user's watchlist.
 * The endpoint is accessed via HTTP POST request to the "/api/users/movies/watchlist/{username}/{title}/{director}" path.
 * It takes in form parameters for username, title, and director, and returns a JSON response
 * with status OK if the movie is added successfully, or a NOT_FOUND response if the username does not exist.
 * <p>
 * HTTP Method: POST
 * Path: /api/users/movies/watchlist/{username}/{title}/{director}
 *
 * @version 1.0
 */
@Path("/users/movies")
public class AddMovieToWatchlistController {

    @Inject
    private IUserService userService;

    /**
     *Adds a movie to the user's watchlist.
     *
     *@param username The username of the user.
     *@param title The title of the movie to add to the watchlist.
     *@param director The director of the movie to add to the watchlist.
     *@return A JSON response with status OK if the movie is added successfully.
     *@throws EntityNotFoundException if the username does not exist in the system.
     */
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

        return Response.status(Response.Status.OK).build();
    }
}