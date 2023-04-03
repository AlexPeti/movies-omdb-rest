package gr.aueb.cf.movies.rest;

import gr.aueb.cf.movies.dao.IUserDAO;
import gr.aueb.cf.movies.model.User;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.UUID;


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
//            System.out.println(jsonResponse);
            return Response.ok(jsonResponse).build();

        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }




//    @POST
//    @Path("/authenticate")
//    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response authenticateUser(MultivaluedMap<String, String> formParams) {
//
//        String username = formParams.getFirst("username");
//        String password = formParams.getFirst("password");
//
//        User authenticatedUser = userDAO.authenticate(username, password);
//
//        if (authenticatedUser != null) {
//            URI redirectUri = UriBuilder.fromUri("http://127.0.0.1:5500/index.html").build();
//            return Response.seeOther(redirectUri).build();
//        } else {
//            return Response.status(Response.Status.UNAUTHORIZED).build();
//        }
//    }

    // method that returns json number 2, leitourgei, stelnei to json me to username pisw

}
