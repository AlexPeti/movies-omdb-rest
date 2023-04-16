package gr.aueb.cf.movies.dao;

import gr.aueb.cf.movies.model.Movie;
import gr.aueb.cf.movies.model.User;

import java.util.List;

public interface IUserDAO {
    /**
     * Inserts a new User object into the database.
     *
     * @param user The User object to be inserted.
     * @return The inserted User object.
     */
    User insert(User user);

    /**
     * Updates an existing User object in the database.
     *
     * @param user The User object to be updated.
     * @return The updated User object.
     */
    User update(User user);

    /**
     * Deletes a User object from the database by its ID.
     *
     * @param id The ID of the User object to be deleted.
     */
    void delete(Long id);

    /**
     * Retrieves a list of User objects from the database by username.
     *
     * @param username The username to search for.
     * @return A list of User objects that match the given username.
     */
    List<User> getUsersByUsername(String username);

    /**
     * Retrieves a User object from the database by username.
     *
     * @param username The username to search for.
     * @return The User object that matches the given username, or null if not found.
     */
    User getByUsername(String username);

    /**
     * Retrieves a User object from the database by ID.
     *
     * @param id The ID of the User object to retrieve.
     * @return The User object that matches the given ID, or null if not found.
     */
    User getById(Long id);

    /**
     * Adds a Movie object to the list of movies associated with a User object.
     *
     * @param user  The User object to associate the Movie with.
     * @param movie The Movie object to be added.
     */
    void addMovie(User user, Movie movie);

    /**
     * Removes a Movie object from the list of movies associated with a User object.
     *
     * @param user  The User object to disassociate the Movie from.
     * @param movie The Movie object to be removed.
     */
    void removeMovie(User user, Movie movie);

    /**
     * Retrieves a list of all Movie objects associated with a User object by username.
     *
     * @param username The username of the User to retrieve the movies for.
     * @return A list of Movie objects associated with the given username.
     */
    List<Movie> getAllMoviesByUsername(String username);

    /**
     * Authenticates a User object by username and password.
     *
     * @param username The username of the User to authenticate.
     * @param password The password of the User to authenticate.
     * @return The authenticated User object, or null if not found or authentication fails.
     */
    User authenticate(String username, String password);
}
