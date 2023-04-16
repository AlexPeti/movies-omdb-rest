package gr.aueb.cf.movies.dao;

import java.util.List;
import gr.aueb.cf.movies.model.Movie;
import gr.aueb.cf.movies.model.User;
import jakarta.persistence.EntityManager;

public interface IMovieDAO {

    /**
     * Adds a new Movie object into the database.
     *
     * @param movie The Movie object to be added.
     * @return The added Movie object.
     */
    Movie addMovie(Movie movie);

    /**
     * Updates an existing Movie object in the database.
     *
     * @param movie The Movie object to be updated.
     * @return The updated Movie object.
     */
    Movie updateMovie(Movie movie);

    /**
     * Deletes a Movie object from the database by its ID.
     *
     * @param id The ID of the Movie object to be deleted.
     */
    void deleteMovie(Long id);

    /**
     * Retrieves a Movie object from the database by ID.
     *
     * @param id The ID of the Movie object to retrieve.
     * @return The Movie object that matches the given ID, or null if not found.
     */
    Movie getMovieById(Long id);

    /**
     * Retrieves a list of all Movie objects from the database.
     *
     * @return A list of all Movie objects.
     */
    List<Movie> getAllMovies();

    /**
     * Retrieves a list of Movie objects from the database by director.
     *
     * @param director The director to search for.
     * @return A list of Movie objects that match the given director.
     */
    List<Movie> getMoviesByDirector(String director);

    /**
     * Retrieves a list of Movie objects from the database by username.
     *
     * @param username The username to search for.
     * @return A list of Movie objects that are associated with the given username.
     */
    List<Movie> getMoviesByUsername(String username);

    /**
     * Retrieves a list of Movie objects from the database by title.
     *
     * @param title The title to search for.
     * @return A list of Movie objects that match the given title.
     */
    List<Movie> getMoviesByTitle(String title);

    /**
     * Retrieves a Movie object from the database by title.
     *
     * @param title The title to search for.
     * @return The Movie object that matches the given title, or null if not found.
     */
    Movie getMovieByTitle(String title);
}

