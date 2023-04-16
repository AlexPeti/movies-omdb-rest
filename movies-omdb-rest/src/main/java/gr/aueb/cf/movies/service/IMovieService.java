package gr.aueb.cf.movies.service;

import gr.aueb.cf.movies.model.Movie;

import java.util.List;

public interface IMovieService {
    /**
     * Retrieves a list of movies by title.
     *
     * @param title The title to search for
     * @return A list of movie objects matching the provided title
     */
    List<Movie> getMoviesByTitle(String title);

    /**
     * Retrieves a movie by title.
     *
     * @param title The title to search for
     * @return The retrieved movie object
     */
    Movie getMovieByTitle(String title);

    /**
     * Retrieves a list of movies by username.
     *
     * @param username The username to search for
     * @return A list of movie objects associated with the provided username
     */
    List<Movie> getMoviesByUsername(String username);
}
