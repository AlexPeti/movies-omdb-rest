package gr.aueb.cf.movies.rest;

import gr.aueb.cf.movies.model.Movie;
import gr.aueb.cf.movies.model.User;
import gr.aueb.cf.movies.service.IUserService;
import gr.aueb.cf.movies.service.exceptions.EntityNotFoundException;

import javax.inject.Inject;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * RemoveMovieFromWatchlistController class is a RESTful web service that provides an endpoint for removing a movie from a user's watchlist.
 * The endpoint is accessed via HTTP PUT request to the "/user/watchlist/{username}/{title}" path.
 * It takes in path parameters for username and title, and returns a JSON response
 * with status OK if the movie is removed successfully, or BAD REQUEST if the movie is not found in the user's watchlist.
 * <p>
 * HTTP Method: PUT
 * Path: /user/watchlist/{username}/{title}
 *
 * @version 1.0
 */

@Path("/user")
public class RemoveMovieFromWatchlistController {

    @Inject
    private IUserService userService;

    /**
     * Removes a movie from the user's watchlist.
     *
     * @param username The username of the user.
     * @param title The title of the movie to remove from the watchlist.
     * @return A JSON response with status OK if the movie is removed successfully, or BAD REQUEST if the movie is not found in the user's watchlist.
     * @throws EntityNotFoundException if the username does not exist in the system.
     */
    @PUT
    @Path("/watchlist/{username}/{title}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeMovieFromWatchlist(
            @PathParam("username") String username,
            @PathParam("title") String title) throws EntityNotFoundException {

        // Retrieve the user from the database
        User user = userService.getUserByUsername(username);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        // Check if the user has the movie in their watchlist
        Movie movieToRemove = null;
        for (Movie movie : user.getMovies()) {
            if (movie.getTitle().equals(title)) {
                movieToRemove = movie;
                break;
            }
        }

        // If movie is found, remove it from the user's watchlist
        if (movieToRemove != null) {
            User updatedUser = userService.removeMovieFromWatchlist(username, movieToRemove);

            // Return a JSON response with the updated user
            return Response.status(Response.Status.OK).build();
        } else {
            // If movie is not found in user's watchlist, return a not found response
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}

