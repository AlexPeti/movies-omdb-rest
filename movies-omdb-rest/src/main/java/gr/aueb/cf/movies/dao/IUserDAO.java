package gr.aueb.cf.movies.dao;

import gr.aueb.cf.movies.model.User;

import java.util.List;

public interface IUserDAO {
    User insert(User user);
    User update(User user);
    void delete(Long id);
    List<User> getByUsername(String username);
    User getById(Long id);
}
