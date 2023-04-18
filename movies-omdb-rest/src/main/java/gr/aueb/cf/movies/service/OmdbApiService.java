package gr.aueb.cf.movies.service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 * Service class for interacting with the OMDB API to retrieve movie details.
 */
public class OmdbApiService {
    private static final String OMDB_URL = "http://www.omdbapi.com/";
    private static final String OMDB_API_KEY = "YOUR_API_KEY";

    /**
     * Retrieves movie details from the OMDB API based on the given movie title
     *
     * @param movieTitle
     * The title of the movie for which details are to be retrieved
     *
     * @return
     * A string containing the JSON response from the OMDB API
     */
    public String getMovieDetails(String movieTitle) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(OMDB_URL)
                .queryParam("apikey", OMDB_API_KEY)
                .queryParam("t", movieTitle)
                .queryParam("plot", "full");
        String response = target.request(MediaType.APPLICATION_JSON)
                .get(String.class);
        return response;
    }
}
