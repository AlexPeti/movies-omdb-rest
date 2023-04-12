package gr.aueb.cf.movies.rest;

import gr.aueb.cf.movies.dao.IUserDAO;
import gr.aueb.cf.movies.model.User;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;


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
            JsonObject jsonResponse = Json.createObjectBuilder()
                    .add("username", authenticatedUser.getUsername())
                    .build();
            return Response.ok(jsonResponse).build();

        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

}
