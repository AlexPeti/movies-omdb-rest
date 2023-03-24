package gr.aueb.cf.movies.dao;

import gr.aueb.cf.movies.model.User;
import gr.aueb.cf.movies.service.util.JPAHelper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import javax.ws.rs.ext.Provider;
import java.util.List;

@Provider
public class UserDAOImpl implements IUserDAO {
    @Override
    public User insert(User user) {
        EntityManager em = getEntityManager();
        em.persist(user);
        return user;
    }

    @Override
    public User update(User user) {
        getEntityManager().merge(user);
        return user;
    }

    @Override
    public void delete(Long id) {
        EntityManager em = getEntityManager();
        User userToDelete = em.find(User.class,id);
        em.remove(userToDelete);
    }

    @Override
    public List<User> getByUsername(String username) {
        String jpql = "SELECT u FROM User u WHERE u.username LIKE :username";
        TypedQuery<User> query = getEntityManager().createQuery(jpql, User.class);
        query.setParameter("username", username + "%");
        return query.getResultList();
    }

    @Override
    public User getById(Long id) {
        EntityManager em = getEntityManager();
        return em.find(User.class,id);
    }

    private EntityManager getEntityManager() {
        return JPAHelper.getEntityManager();
    }
}
