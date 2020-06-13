package ua.testing.model.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public interface GenericDAO<T> extends AutoCloseable {
    ResourceBundle BUNDLE = ResourceBundle.getBundle("sql");

    List<T> findAll();
    void save(T entity) throws SQLException;
    void update(T entity);
    void delete(long id);
}
