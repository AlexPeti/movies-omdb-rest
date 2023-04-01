package gr.aueb.cf.movies.rest;

import gr.aueb.cf.movies.dao.IUserDAO;
import gr.aueb.cf.movies.model.User;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

@Path("/login")
public class LoginController {

    @Inject
    IUserDAO userDAO;

    @POST
    @Path("/authenticate")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
    @Produces(MediaType.APPLICATION_JSON)
    public Response authenticateUser(MultivaluedMap<String, String> formParams) {

        String username = formParams.getFirst("username");
        String password = formParams.getFirst("password");

        User authenticatedUser = userDAO.authenticate(username, password);

        if (authenticatedUser != null) {
            URI redirectUri = UriBuilder.fromUri("http://127.0.0.1:5500/index.html").build();
            return Response.seeOther(redirectUri).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
}
