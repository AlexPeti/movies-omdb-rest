package gr.aueb.cf.movies;

import gr.aueb.cf.movies.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("usersPU");
        EntityManager em = emf.createEntityManager();

        // Create a new User entity
        User user = new User();
        user.setUsername("user");
        user.setPassword("password");

        // Insert the User entity into the database
        em.persist(user);

        // Commit the transaction
        em.getTransaction().commit();

        // Close the EntityManager and EntityManagerFactory
        em.close();
        emf.close();
    }
}
