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

@Path("/user")
public class GetMoviesByUsernameController {

    @Inject
    private IMovieService movieService;

    /**
     * Fetches the titles of the movies from a user's list of movies
     *
     * @param username the user's username
     * @return the titles of the movies from a user's movie list
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