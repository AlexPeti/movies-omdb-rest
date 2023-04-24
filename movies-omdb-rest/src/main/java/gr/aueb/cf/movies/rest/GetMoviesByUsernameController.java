package gr.aueb.cf.movies.rest;

import gr.aueb.cf.movies.model.Movie;
import gr.aueb.cf.movies.service.IMovieService;

import javax.inject.Inject;
import javax.json.*;
import javax.json.stream.JsonGenerator;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class represents a RESTful web service endpoint for retrieving movies by username.
 * The endpoint is accessed via HTTP GET request to the "/api/user/watchlist" path.
 * The response is in JSON format and contains the list of movies associated with a user.
 *<p>
 * HTTP Method: GET
 * Path: /api/user/watchlist
 *
 * @version 1.0
 */
@Path("/user")
public class GetMoviesByUsernameController {

    @Inject
    private IMovieService movieService;

    /**
     * Retrieves movies associated with the given username.
     *
     * @param username the username for which to retrieve movies
     * @return a Response object containing the retrieved movies in JSON format
     * @throws IllegalArgumentException if the username is empty or null
     */
    @GET
    @Path("/watchlist")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMoviesByUsername(@QueryParam("username") String username) {
        if (username == null || username.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Username cannot be empty")
                    .build();
        }

        List<Movie> movies = movieService.getMoviesByUsername(username);

        if (movies == null || movies.isEmpty()) {
            return Response.noContent().build();
        } else {
            // Convert the list of movies to a JSON array
            JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
            for (Movie movie : movies) {
                JsonObject jsonObject = Json.createObjectBuilder()
//                        .add("id", movie.getId())
                        .add("title", movie.getTitle())
//                        .add("genre", movie.getDirector())
                        .build();
                jsonArrayBuilder.add(jsonObject);
            }
            JsonArray jsonArray = jsonArrayBuilder.build();

            // Create a JSON object containing the JSON array
            JsonObject jsonObject = Json.createObjectBuilder()
                    .add("movies", jsonArray)
                    .build();

            // Configure the JSON serialization settings
            Map<String, Boolean> config = new HashMap<>();
            config.put(JsonGenerator.PRETTY_PRINTING, false);

            // Create a StringWriter to hold the serialized JSON
            StringWriter writer = new StringWriter();

            // Create a JsonWriterFactory with the configured settings
            JsonWriterFactory writerFactory = Json.createWriterFactory(config);

            // Use the JsonWriterFactory to create a JsonWriter
            JsonWriter jsonWriter = writerFactory.createWriter(writer);

            // Write the JsonObject to the StringWriter using the JsonWriter
            jsonWriter.writeObject(jsonObject);

            // Close the JsonWriter
            jsonWriter.close();

            // Get the serialized JSON from the StringWriter
            String json = writer.toString();

            // Return the JSON as the response entity
            return Response.status(Response.Status.OK).entity(json).build();
        }
    }
}