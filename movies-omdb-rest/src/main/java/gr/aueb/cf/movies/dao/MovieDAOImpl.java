package gr.aueb.cf.movies.dao;

import gr.aueb.cf.movies.model.Movie;
import gr.aueb.cf.movies.model.User;
import gr.aueb.cf.movies.service.util.JPAHelper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import javax.ws.rs.ext.Provider;
import java.util.List;

@Provider
public class MovieDAOImpl implements IMovieDAO {

    @Override
    public Movie addMovie(Movie movie) {
        EntityManager em = getEntityManager();
        em.persist(movie);
        return movie;
    }

    @Override
    public Movie updateMovie(Movie movie) {
        EntityManager em = getEntityManager();
        em.merge(movie);
        return movie;
    }

    @Override
    public void deleteMovie(Long id) {
        EntityManager em = getEntityManager();
        Movie movie = em.find(Movie.class, id);
        em.remove(movie);
    }

    @Override
    public Movie getMovieById(Long id) {
        EntityManager em = getEntityManager();
        return em.find(Movie.class, id);
    }

    @Override
    public List<Movie> getAllMovies() {
        String jpql = "SELECT m FROM Movie m";
        TypedQuery<Movie> query = getEntityManager().createQuery(jpql, Movie.class);
        return query.getResultList();
    }

    @Override
    public List<Movie> getMoviesByGenre(String genre) {
        String jpql = "SELECT m FROM Movie m WHERE m.genre = :genre";
        TypedQuery<Movie> query = getEntityManager().createQuery(jpql, Movie.class);
        query.setParameter("genre", genre);
        return query.getResultList();
    }

    @Override
    public List<Movie> getMoviesByUser(User user) {
        String jpql = "SELECT m FROM Movie m JOIN m.users u WHERE u = :user";
        TypedQuery<Movie> query = getEntityManager().createQuery(jpql, Movie.class);
        query.setParameter("user", user);
        return query.getResultList();
    }

    @Override
    public List<Movie> getMoviesByTitle(String title) {
        String jpql = "SELECT m FROM Movie m WHERE m.title LIKE :title";
        TypedQuery<Movie> query = getEntityManager().createQuery(jpql, Movie.class);
        query.setParameter("title", title + "%");
        return query.getResultList();
    }


    private EntityManager getEntityManager() {
        return JPAHelper.getEntityManager();
    }
}

