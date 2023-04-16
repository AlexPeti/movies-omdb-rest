package gr.aueb.cf.movies.service;

import gr.aueb.cf.movies.dto.UserDTO;
import gr.aueb.cf.movies.model.Movie;
import gr.aueb.cf.movies.model.User;
import gr.aueb.cf.movies.service.exceptions.EntityNotFoundException;

import java.util.List;

public interface IUserService {
    /**
     * Inserts a new user based on the provided user data.
     *
     * @param userDTO The user data to be inserted
     * @return The inserted user object
     */
    User insertUser(UserDTO userDTO);

    /**
     * Updates an existing user based on the provided user data.
     *
     * @param userDTO The user data to be updated
     * @return The updated user object
     * @throws EntityNotFoundException If the user with the given ID is not found
     */
    User updateUser(UserDTO userDTO) throws EntityNotFoundException;

    /**
     * Deletes a user with the given ID.
     *
     * @param id The ID of the user to be deleted
     * @throws EntityNotFoundException If the user with the given ID is not found
     */
    void deleteUser(Long id) throws EntityNotFoundException;

    /**
     * Retrieves a user by ID.
     *
     * @param id The ID of the user to be retrieved
     * @return The retrieved user object
     * @throws EntityNotFoundException If the user with the given ID is not found
     */
    User getUserById(Long id) throws EntityNotFoundException;

    /**
     * Retrieves a list of users by username.
     *
     * @param username The username to search for
     * @return A list of user objects matching the provided username
     * @throws EntityNotFoundException If no users with the given username are found
     */
    List<User> getUsersByUsername(String username) throws EntityNotFoundException;

    /**
     * Retrieves a user by username.
     *
     * @param username The username to search for
     * @return The retrieved user object
     * @throws EntityNotFoundException If the user with the given username is not found
     */
    User getUserByUsername(String username) throws EntityNotFoundException;

    /**
     * Adds a movie to the watchlist of a user.
     *
     * @param username The username of the user
     * @param movie The movie to be added to the watchlist
     * @return The updated user object with the added movie in the watchlist
     */
    User addMovieToWatchlist(String username, Movie movie);

    /**
     * Removes a movie from the watchlist of a user.
     *
     * @param username The username of the user
     * @param movie The movie to be removed from the watchlist
     * @return The updated user object with the removed movie from the watchlist
     */
    User removeMovieFromWatchlist(String username, Movie movie);

    /**
     * Authenticates a user based on the provided username and password.
     *
     * @param username The username of the user to be authenticated
     * @param password The password of the user to be authenticated
     * @return The authenticated user object
     * @throws EntityNotFoundException If the user with the given username is not found
     */
    User authenticateUser(String username, String password) throws EntityNotFoundException;
}
