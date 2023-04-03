package gr.aueb.cf.movies.dao;

import java.util.List;
import gr.aueb.cf.movies.model.Movie;
import gr.aueb.cf.movies.model.User;

public interface IMovieDAO {

    Movie addMovie(Movie movie);

    Movie updateMovie(Movie movie);

    void deleteMovie(Long id);

    Movie getMovieById(Long id);

    List<Movie> getAllMovies();

    List<Movie> getMoviesByGenre(String genre);

    List<Movie> getMoviesByUser(User user);

    List<Movie> getMoviesByTitle(String title);

}

