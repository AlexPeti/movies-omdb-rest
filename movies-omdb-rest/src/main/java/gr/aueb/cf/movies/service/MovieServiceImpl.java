package gr.aueb.cf.movies.service;

import gr.aueb.cf.movies.dao.IMovieDAO;
import gr.aueb.cf.movies.dto.MovieDTO;
import gr.aueb.cf.movies.dto.UserDTO;
import gr.aueb.cf.movies.model.Movie;
import gr.aueb.cf.movies.model.User;
import gr.aueb.cf.movies.service.util.JPAHelper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import javax.inject.Inject;
import javax.ws.rs.ext.Provider;
import java.util.List;

@Provider
public class MovieServiceImpl implements IMovieService {

    @Inject
    IMovieDAO movieDAO;

    private Movie map(MovieDTO movieDTO) {
        Movie movie = new Movie();
        movie.setId(movieDTO.getId());
        movie.setTitle(movieDTO.getTitle());
        movie.setGenre(movieDTO.getGenre());

        List<User> users = movie.getUsers();
        List<UserDTO> usersDTO = movieDTO.getUsers();
        for (UserDTO userDTO : usersDTO) {
            User user = new User();
            user.setId(userDTO.getId());
            user.setUsername(userDTO.getUsername());
            user.setPassword(userDTO.getPassword());
            users.add(user);
        }

        return movie;
    }


    @Override
    public List<Movie> getMoviesByTitle(String title) {
        try {
            EntityManager entityManager = JPAHelper.getEntityManager();
            TypedQuery<Movie> query = entityManager.createQuery("SELECT m FROM Movie m WHERE m.title LIKE :title", Movie.class);
            query.setParameter("title", "%" + title + "%");
            List<Movie> movies = query.getResultList();
            return movies;
        } finally {
            JPAHelper.closeEntityManager();
        }
    }

    @Override
    public List<Movie> getWatchlist(String username) {
        try {
            EntityManager entityManager = JPAHelper.getEntityManager();
            TypedQuery<Movie> query = entityManager.createQuery("SELECT m FROM Movie m JOIN m.users u WHERE u.username = :username", Movie.class);
            query.setParameter("username", username);
            List<Movie> movies = query.getResultList();
            return movies;
        } finally {
            JPAHelper.closeEntityManager();
        }
    }

    @Override
    public void addToWatchlist(String username, String title) {
        EntityManager entityManager = JPAHelper.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            TypedQuery<User> userQuery = entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
            userQuery.setParameter("username", username);
            User user = userQuery.getSingleResult();
            TypedQuery<Movie> movieQuery = entityManager.createQuery("SELECT m FROM Movie m WHERE m.title = :title", Movie.class);
            movieQuery.setParameter("title", title);
            Movie movie = movieQuery.getSingleResult();
            List<User> users = movie.getUsers();
            if (!users.contains(user)) {
                users.add(user);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        } finally {
            JPAHelper.closeEntityManager();
        }
    }

    @Override
    public void removeFromWatchlist(String username, String title) {
        EntityManager entityManager = JPAHelper.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            TypedQuery<User> userQuery = entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
            userQuery.setParameter("username", username);
            User user = userQuery.getSingleResult();
            TypedQuery<Movie> movieQuery = entityManager.createQuery("SELECT m FROM Movie m WHERE m.title = :title", Movie.class);
            movieQuery.setParameter("title", title);
            Movie movie = movieQuery.getSingleResult();
            List<User> users = movie.getUsers();
            users.remove(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        } finally {
            entityManager.close();
        }
    }

}
