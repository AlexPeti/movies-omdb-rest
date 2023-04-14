package gr.aueb.cf.movies.service;

import gr.aueb.cf.movies.model.Movie;

import java.util.List;

public interface IMovieService {
    List<Movie> getMoviesByTitle(String title);
    Movie getMovieByTitle(String title);
    List<Movie> getMoviesByUsername(String username);
    void addToWatchlist(String username, String title);
    void removeFromWatchlist(String username, String title);

}
