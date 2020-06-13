package ua.testing.model.dao;

import ua.testing.model.entity.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public interface UserDAO extends GenericDAO<User> {
    void save(User user) throws SQLException;
    void updatePassword(String newPassword, String username);
    void updateUsername(String newUsername, String username);
    int addCentsToBalance(long cents, String username);
    int deductBalance(long cents, String username);
    Optional<User> findUserByUsername(String username);
    Connection getConnection();
}
