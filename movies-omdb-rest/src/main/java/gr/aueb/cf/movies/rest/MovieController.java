package gr.aueb.cf.movies.rest;

import gr.aueb.cf.movies.service.OmdbApiService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * MovieController is a RESTful web service endpoint for retrieving movie details from the OMDB API by using the movie title.
 * The endpoint is accessed via HTTP GET request to the "/api/movies/title" path.
 *<p>
 * Http Method: GET
 * Path: /api/movies/title
 *
 * @version 1.0
 */
@Path("/movies")
public class MovieController {
    @Inject
    private OmdbApiService omdbApiService;

    /**
     *Retrieves movie details from the OMDB API by using the movie title.
     *
     * @param title The title of the movie to retrieve
     * @return A JSON response containing the details of the movie
     */
    @GET
    @Path("/title")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMovieByTitle(@QueryParam("title") String title) {
        String response = omdbApiService.getMovieDetails(title);

        if (response.contains("Error")) {
            return Response.status(Response.Status.NOT_FOUND).entity("Movie not found").build();
        }

        return Response.ok(response, MediaType.APPLICATION_JSON).build();
    }
}
