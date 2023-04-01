package gr.aueb.cf.movies.rest;

import gr.aueb.cf.movies.model.User;
import gr.aueb.cf.movies.service.IUserService;
import gr.aueb.cf.movies.service.exceptions.EntityNotFoundException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users/current")
public class CurrentUserController {

    @Inject
    IUserService userService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCurrentUser(@Context HttpServletRequest request) throws EntityNotFoundException {
        String username = request.getRemoteUser();
        User currentUser = userService.getUserByUsername(username);
        return Response.ok(currentUser).build();
    }
}

