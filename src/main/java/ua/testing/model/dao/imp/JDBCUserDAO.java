package ua.testing.model.dao.imp;

import org.apache.log4j.Logger;
import ua.testing.model.annotation.DAO;
import ua.testing.model.annotation.InjectByType;
import ua.testing.model.dao.UserDAO;
import ua.testing.model.entity.RoleType;
import ua.testing.model.entity.User;
import ua.testing.model.util.BundleKeys;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DAO
public class JDBCUserDAO implements UserDAO {
    private static final Logger LOGGER = Logger.getLogger(JDBCUserDAO.class);

    @InjectByType
    private Connection connection;

    @Override
    public Optional<User> findUserByUsername(String username) {
        User user = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(
                BUNDLE.getString(BundleKeys.SQL_FIND_BY_USERNAME_USER))) {

            preparedStatement.setString(1, username);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    user = extractResultSet(resultSet);
                }
            }

        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return Optional.ofNullable(user);
    }

    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(
                BUNDLE.getString(BundleKeys.SQL_FIND_ALL_USER));
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                list.add(extractResultSet(resultSet));
            }

        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return list;
    }

    @Override
    public void save(User user) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                BUNDLE.getString(BundleKeys.SQL_SAVE_USER))) {

            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setLong(3, user.getBalance());
            preparedStatement.setString(4, user.getRole().name());
            preparedStatement.setString(5, user.getUsername());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void update(User user) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                BUNDLE.getString(BundleKeys.SQL_UPDATE_USER))) {

            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setLong(3, user.getBalance());
            preparedStatement.setLong(4, user.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void updatePassword(String newPassword, String username) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                BUNDLE.getString(BundleKeys.SQL_UPDATE_PASSWORD_USER))) {

            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, username);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void updateUsername(String newUsername, String username) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                BUNDLE.getString(BundleKeys.SQL_UPDATE_USERNAME_USER))) {

            preparedStatement.setString(1, newUsername);
            preparedStatement.setString(2, username);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public int addCentsToBalance(long cents, String username) {
        int affectedRows = 0;

        try (PreparedStatement preparedStatement = connection.prepareStatement(
                BUNDLE.getString(BundleKeys.SQL_UPDATE_REPLENISH_BALANCE_USER))) {

            preparedStatement.setLong(1, cents);
            preparedStatement.setString(2, username);
            affectedRows = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException rollBack) {
                LOGGER.error(rollBack);
            }
            LOGGER.error(e);
        }

        return affectedRows;
    }

    @Override
    public int deductBalance(long cents, String username) {
        int affectedRows = 0;

        try (PreparedStatement preparedStatement = connection.prepareStatement(
                BUNDLE.getString(BundleKeys.SQL_UPDATE_DEDUCT_FROM_BALANCE_USER))) {

            preparedStatement.setLong(1, cents);
            preparedStatement.setLong(2, cents);
            preparedStatement.setString(3, username);
            affectedRows = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return affectedRows;
    }

    @Override
    public void delete(long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                BUNDLE.getString(BundleKeys.SQL_DELETE_USER))) {

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void close() throws Exception {
        if (!connection.isClosed()) {
            connection.close();
        }
    }

    private User extractResultSet(ResultSet resultSet) {
        User user = new User();

        try {
            user.setId(resultSet.getLong("id"));
            user.setRole(resultSet.getString("role_type").equals(RoleType.USER.name()) ? RoleType.USER : RoleType.ADMIN);
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            user.setBalance(resultSet.getLong("balance"));
        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return user;
    }

    public Connection getConnection() {
        return connection;
    }
}
