package gr.aueb.cf.movies.rest;

import gr.aueb.cf.movies.dto.MovieDTO;
import gr.aueb.cf.movies.model.Movie;
import gr.aueb.cf.movies.model.User;
import gr.aueb.cf.movies.service.IUserService;
import gr.aueb.cf.movies.service.exceptions.EntityNotFoundException;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/users/{username}/movies")
public class UserMovieController {

    @Inject
    IUserService userService;

    @POST
    @Path("/watchlist")
    public Response addMovieToWatchlist(@PathParam("username") String username, MovieDTO movieDTO) throws EntityNotFoundException {
        Movie movie = new Movie();
        movie.setTitle(movieDTO.getTitle());
        User updatedUser = userService.addMovieToWatchlist(username, movie);
        return Response.ok(updatedUser).build();
    }
}
