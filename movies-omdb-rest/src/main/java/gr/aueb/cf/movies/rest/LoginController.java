package gr.aueb.cf.movies.rest;

import gr.aueb.cf.movies.model.User;
import gr.aueb.cf.movies.service.IUserService;
import gr.aueb.cf.movies.service.exceptions.EntityNotFoundException;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

/**
 * This class represents a RESTful web service endpoint for user authentication.
 * The endpoint is accessed via HTTP POST request to the "/api/login/authenticate" path.
 * It takes in form parameters for username and password, and returns a JSON response
 * containing the username of the authenticated user if successful, or a 401 UNAUTHORIZED
 * response if authentication fails.
 *<p>
 * Http Method: POST
 * Path: /api/login/authenticate
 *
 * @version 1.0
 */
@Path("/login")
public class LoginController {

    @Inject
    IUserService userService;

    /**
     * Authenticates a user based on the provided username and password.
     *
     * @param formParams A MultivaluedMap containing the form parameters for username and password.
     * @return A Response object with a JSON response containing the username of the authenticated user
     *         if successful, or a 401 UNAUTHORIZED response if authentication fails.
     */
    @POST
    @Path("/authenticate")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
    @Produces(MediaType.APPLICATION_JSON)
    public Response authenticateUser(MultivaluedMap<String, String> formParams) throws EntityNotFoundException {

        String username = formParams.getFirst("username");
        String password = formParams.getFirst("password");

        User authenticatedUser = userService.authenticateUser(username, password);

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
