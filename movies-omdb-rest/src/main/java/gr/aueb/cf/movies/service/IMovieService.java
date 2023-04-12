package gr.aueb.cf.movies.service;

import gr.aueb.cf.movies.model.Movie;

import java.util.List;

public interface IMovieService {
    public List<Movie> getMoviesByTitle(String title);
    public List<Movie> getMoviesByUsername(String username);
    public void addToWatchlist(String username, String title);
    public void removeFromWatchlist(String username, String title);
}
