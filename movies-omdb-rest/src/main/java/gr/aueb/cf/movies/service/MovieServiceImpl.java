package gr.aueb.cf.movies.service;

import gr.aueb.cf.movies.dao.IMovieDAO;
import gr.aueb.cf.movies.dao.IUserDAO;
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

    @Inject
    IUserDAO userDAO;

    private Movie map(MovieDTO movieDTO) {
        Movie movie = new Movie();
        movie.setId(movieDTO.getId());
        movie.setTitle(movieDTO.getTitle());
        movie.setDirector(movieDTO.getDirector());

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
        EntityManager entityManager = JPAHelper.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            TypedQuery<Movie> query = entityManager.createQuery("SELECT m FROM Movie m WHERE m.title LIKE :title", Movie.class);
            query.setParameter("title", "%" + title + "%");
            List<Movie> movies = query.getResultList();
            transaction.commit();
            return movies;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        } finally {
            JPAHelper.closeEntityManager();
        }
    }

    @Override
    public Movie getMovieByTitle(String title) {
        EntityManager entityManager = JPAHelper.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Movie movie = movieDAO.getMovieByTitle(title); // Use the DAO method to retrieve the movie
            transaction.commit();
            return movie;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        } finally {
            JPAHelper.closeEntityManager();
        }
    }

    @Override
    public List<Movie> getMoviesByUsername(String username) {
        EntityManager em = JPAHelper.getEntityManager();
        try {
            em.getTransaction().begin();

            // Call the DAO method to retrieve movies based on username
            List<Movie> movies = movieDAO.getMoviesByUsername(username);

            em.getTransaction().commit();

            return movies;
        } catch (Exception e) {
            em.getTransaction().rollback();
            // Handle exception or re-throw
            throw new RuntimeException("Failed to get movies for username: " + username, e);
        } finally {
            em.close();
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

    public void removeFromWatchlist(String username, String title) {
        // Get user by username
        User user = userDAO.getByUsername(username);

        if (user != null) {
            // Get movies in user's watchlist
            List<Movie> movies = movieDAO.getMoviesByUsername(username);

            // Find the movie with the given title
            Movie movieToRemove = null;
            for (Movie movie : movies) {
                if (movie.getTitle().equalsIgnoreCase(title)) {
                    movieToRemove = movie;
                    break;
                }
            }

            // If movie found, remove it from the user's watchlist
            if (movieToRemove != null) {
                movies.remove(movieToRemove);
                userDAO.update(user); // Update the user to persist the changes
            }
        }
    }
}
