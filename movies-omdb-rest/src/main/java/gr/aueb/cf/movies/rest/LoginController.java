package gr.aueb.cf.movies.rest;

import gr.aueb.cf.movies.dao.IUserDAO;
import gr.aueb.cf.movies.model.User;
import gr.aueb.cf.movies.service.exceptions.EntityNotFoundException;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class LoginController {

    @Inject
    private IUserDAO userDAO;

    @POST
    @Path("/authenticate")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(User user) throws EntityNotFoundException {
        User dbUser = userDAO.getById(user.getId());
        if (dbUser == null || !dbUser.getPassword().equals(user.getPassword())) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        if (dbUser.getUsername().equals(user.getUsername())) {
            return Response.ok(dbUser).build();
        }

        return Response.status(Response.Status.UNAUTHORIZED).build();
    }
}
