package gr.aueb.cf.movies.rest;

import gr.aueb.cf.movies.dao.IUserDAO;
import gr.aueb.cf.movies.dao.UserDAOImpl;
import gr.aueb.cf.movies.model.User;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

@Path("/login")
public class LoginController {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response authenticateUser(User user) {
        try {
            IUserDAO userDAO = new UserDAOImpl();
            User authenticatedUser = userDAO.authenticate(user.getUsername(), user.getPassword());

            if (authenticatedUser != null) {
                // Create URI of the index.html page with welcome message as query parameter
                UriBuilder uriBuilder = UriBuilder.fromPath("index.html").queryParam("message", "Welcome back, " + user.getUsername());
                return Response.temporaryRedirect(uriBuilder.build()).build();
            } else {
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
