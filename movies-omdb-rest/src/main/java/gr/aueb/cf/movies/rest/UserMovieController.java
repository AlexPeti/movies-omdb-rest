package gr.aueb.cf.movies.rest;

import gr.aueb.cf.movies.dao.IUserDAO;
import gr.aueb.cf.movies.dao.UserDAOImpl;
import gr.aueb.cf.movies.model.Movie;
import gr.aueb.cf.movies.model.User;
import gr.aueb.cf.movies.service.util.JPAHelper;
import jakarta.persistence.EntityManager;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users/{id}/movies")
public class UserMovieController {

    @POST
    @Path("/{userId}/movies")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addMovieToWatchlist(@PathParam("userId") Long userId, Movie movie) {
        try {
            IUserDAO userDAO = new UserDAOImpl();
            userDAO.addMovie(userId, movie);
            return Response.status(Response.Status.CREATED).entity(movie).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/{userId}/movies")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeMovieFromUserList(@PathParam("userId") Long userId, Movie movie) {
        IUserDAO userDAO = new UserDAOImpl();
        userDAO.removeMovie(userId, movie);
        return Response.ok().entity(userDAO.getById(userId)).build();
    }
}
