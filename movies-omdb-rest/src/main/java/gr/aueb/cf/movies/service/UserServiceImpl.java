package gr.aueb.cf.movies.service;

import gr.aueb.cf.movies.dao.IUserDAO;
import gr.aueb.cf.movies.dto.MovieDTO;
import gr.aueb.cf.movies.dto.UserDTO;
import gr.aueb.cf.movies.model.Movie;
import gr.aueb.cf.movies.model.User;
import gr.aueb.cf.movies.service.exceptions.EntityNotFoundException;
import gr.aueb.cf.movies.service.util.JPAHelper;
import jakarta.persistence.EntityManager;

import javax.inject.Inject;
import javax.ws.rs.ext.Provider;
import java.util.ArrayList;
import java.util.List;

@Provider
public class UserServiceImpl implements IUserService {

    @Inject
    IUserDAO userDAO;

    private User map(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());

        List<Movie> movies = user.getMovies();
        List<MovieDTO> moviesDTO = new ArrayList<>();
        for (Movie movie : movies) {
            MovieDTO movieDTO = new MovieDTO();
            movieDTO.setTitle(movie.getTitle());
            moviesDTO.add(movieDTO);
        }
        userDTO.setMovies(moviesDTO);

        return user;
    }
    @Override
    public User insertUser(UserDTO userDTO) {
        User user;
        user = map(userDTO);

        JPAHelper.beginTransaction();
        if (userDTO.getId() == null) {
            user = userDAO.insert(user);
        } else {
            user = userDAO.update(user);
        }
        JPAHelper.commitTransaction();
        return user;
    }

    @Override
    public User updateUser(UserDTO userDTO) throws EntityNotFoundException {
        User userToUpdate;
        try {
            JPAHelper.beginTransaction();
            userToUpdate = map(userDTO);
            if (userDAO.getById(userToUpdate.getId()) == null) {
                throw new EntityNotFoundException(User.class,userToUpdate.getId());
            }
            userDAO.update(userToUpdate);
            JPAHelper.commitTransaction();
            return userToUpdate;
        } catch (EntityNotFoundException e) {
            JPAHelper.rollbackTransaction();
            throw e;
        } finally {
            JPAHelper.closeEntityManager();
        }
    }

    @Override
    public void deleteUser(Long id) throws EntityNotFoundException {
        try {
            JPAHelper.beginTransaction();

            if (userDAO.getById(id) == null) {
                throw new EntityNotFoundException(User.class,id);
            }
            userDAO.delete(id);
            JPAHelper.commitTransaction();

        } catch (EntityNotFoundException e) {
            JPAHelper.rollbackTransaction();
            throw e;
        } finally {
            JPAHelper.closeEntityManager();
        }

    }

    @Override
    public User getUserById(Long id) throws EntityNotFoundException {
        User user;

        try {
            JPAHelper.beginTransaction();
            user = userDAO.getById(id);

            if (user == null) {
                throw new EntityNotFoundException(User.class, id);
            }
            JPAHelper.commitTransaction();
            return user;
        } catch (EntityNotFoundException e) {
            JPAHelper.rollbackTransaction();
            throw e;
        } finally {
            JPAHelper.closeEntityManager();
        }
    }

    @Override
    public List<User> getUsersByUsername(String username) throws EntityNotFoundException {
        List<User> users;
        try {
            JPAHelper.beginTransaction();
            users = userDAO.getUsersByUsername(username);
            if (users.size() == 0) {
                throw new EntityNotFoundException(List.class, 0L);
            }
            JPAHelper.commitTransaction();
            return users;
        } catch (EntityNotFoundException e) {
            JPAHelper.rollbackTransaction();
            throw e;
        } finally {
            JPAHelper.closeEntityManager();
        }
    }

    @Override
    public User getUserByUsername(String username) throws EntityNotFoundException {
        User user;
        try {
            JPAHelper.beginTransaction();
            user = userDAO.getByUsername(username);
            if (user == null) {
                throw new EntityNotFoundException(User.class, 0L);
            }
            JPAHelper.commitTransaction();
            return user;
        } catch (EntityNotFoundException e) {
            JPAHelper.rollbackTransaction();
            throw e;
        } finally {
            JPAHelper.closeEntityManager();
        }
    }

    @Override
    public User addMovie(Long id, Movie movie) {
        EntityManager em = JPAHelper.getEntityManager();
        User user = em.find(User.class, id);
        user.getMovies().add(movie);
        em.getTransaction().begin();
        User updatedUser = em.merge(user);
        em.getTransaction().commit();
        return updatedUser;
    }

    @Override
    public User removeMovie(Long id, Movie movie) {
        EntityManager em = JPAHelper.getEntityManager();
        User user = em.find(User.class, id);
        user.getMovies().remove(movie);
        em.getTransaction().begin();
        User updatedUser = em.merge(user);
        em.getTransaction().commit();
        return updatedUser;
    }

    @Override
    public User authenticateUser(String username, String password) throws EntityNotFoundException {
        User user = userDAO.authenticate(username, password);
        if (user == null) {
            throw new EntityNotFoundException(User.class, 0L);
        }
        return user;
    }
}
