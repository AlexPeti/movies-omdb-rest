package gr.aueb.cf.movies.rest;

import gr.aueb.cf.movies.service.OmdbApiService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/movies")
public class MovieController {
    @Inject
    private OmdbApiService omdbApiService;

    /**
     *Retrieves a movie from the OMDB API by using the movie title.
     * @param title
     * the title to search for
     *
     * @return
     * the movie details
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
