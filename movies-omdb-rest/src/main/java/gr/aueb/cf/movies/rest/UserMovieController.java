package gr.aueb.cf.movies.rest;

import gr.aueb.cf.movies.dao.IUserDAO;
import gr.aueb.cf.movies.dao.UserDAOImpl;
import gr.aueb.cf.movies.model.Movie;
import gr.aueb.cf.movies.model.User;
import gr.aueb.cf.movies.service.util.JPAHelper;
import jakarta.persistence.EntityManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users/{id}/movies")
public class UserMovieController {

    @POST
    @Path("/{id}/movies")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addMovieToWatchlist(@PathParam("id") Long userId, Movie movie, @Context HttpServletRequest request) {
        try {
            IUserDAO userDAO = new UserDAOImpl();
            User user = userDAO.getById(userId);
            HttpSession session = request.getSession(false);

            if (session == null || session.getAttribute("userId") == null || !session.getAttribute("userId").equals(userId)) {
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }

            userDAO.addMovie(userId, movie);
            return Response.status(Response.Status.CREATED).entity(movie).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/{id}/movies")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeMovieFromWatchlist(@PathParam("id") Long userId, Movie movie, @Context HttpServletRequest request) {
        try {
            IUserDAO userDAO = new UserDAOImpl();
            User user = userDAO.getById(userId);
            HttpSession session = request.getSession(false);

            if (session == null || session.getAttribute("userId") == null || !session.getAttribute("userId").equals(userId)) {
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }

            userDAO.removeMovie(userId, movie);
            return Response.ok().entity(userDAO.getById(userId)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

}
