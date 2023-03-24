package gr.aueb.cf.movies.service;

import gr.aueb.cf.movies.dto.UserDTO;
import gr.aueb.cf.movies.model.Movie;
import gr.aueb.cf.movies.model.User;
import gr.aueb.cf.movies.service.exceptions.EntityNotFoundException;

import java.util.List;

public interface IUserService {
    User insertUser(UserDTO userDTO);
    User updateUser(UserDTO userDTO) throws EntityNotFoundException;
    void deleteUser(Long id) throws EntityNotFoundException;
    User getUserById(Long id) throws EntityNotFoundException;
    List<User> getUsersByUsername(String username) throws EntityNotFoundException;
    User addMovie(Long id, Movie movie);
    User removeMovie(Long id, Movie movie);
}
