package ua.testing.model.dao.imp;

import org.apache.log4j.Logger;
import ua.testing.model.annotation.DAO;
import ua.testing.model.annotation.InjectByType;
import ua.testing.model.dao.CartDAO;
import ua.testing.model.entity.Cart;
import ua.testing.model.util.BundleKeys;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@DAO
@SuppressWarnings("Duplicates")
public class JDBCCartDAO implements CartDAO {
    private static final Logger LOGGER = Logger.getLogger(JDBCCartDAO.class);

    @InjectByType
    private Connection connection;

    @Override
    public List<Cart> findAll() {
        List<Cart> carts = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(
                BUNDLE.getString(BundleKeys.SQL_FIND_ALL_CART));
            ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                carts.add(extractResultSet(resultSet));
            }

        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return carts;
    }

    @Override
    public List<Cart> findAllItemsByUsername(String username) {
        List<Cart> products = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(
                BUNDLE.getString(BundleKeys.SQL_FIND_ITEMS_CART))) {

            preparedStatement.setString(1, username);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    products.add(extractResultSet(resultSet));
                }
            }

        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return products;
    }

    @Override
    public void save(Cart cart) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                BUNDLE.getString(BundleKeys.SQL_SAVE_CART))) {

            preparedStatement.setString(1, cart.getEnProductName());
            preparedStatement.setString(2, cart.getRuProductName());
            preparedStatement.setLong(3, cart.getPrice());
            preparedStatement.setInt(4, cart.getAmount());
            preparedStatement.setString(5, cart.getUsername());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void update(Cart cart) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                BUNDLE.getString(BundleKeys.SQL_UPDATE_CART))) {

            preparedStatement.setString(1, cart.getEnProductName());
            preparedStatement.setString(2, cart.getRuProductName());
            preparedStatement.setLong(3, cart.getPrice());
            preparedStatement.setInt(4, cart.getAmount());
            preparedStatement.setString(5, cart.getUsername());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void increaseItemAmount(String productName, String username) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                BUNDLE.getString(BundleKeys.SQL_INCREASE_AMOUNT_CART))) {

            preparedStatement.setString(1, productName);
            preparedStatement.setString(2, productName);
            preparedStatement.setString(3, username);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void decreaseItemAmount(String productName, String username) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                BUNDLE.getString(BundleKeys.SQL_DECREASE_AMOUNT_CART))) {

            preparedStatement.setString(1, productName);
            preparedStatement.setString(2, productName);
            preparedStatement.setString(3, username);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void delete(long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                BUNDLE.getString(BundleKeys.SQL_DELETE_CART))) {

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void deleteByUsername(String username) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                BUNDLE.getString(BundleKeys.SQL_DELETE_BY_USERNAME_CART))) {

            preparedStatement.setString(1, username);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void deleteItem(Cart cart) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                BUNDLE.getString(BundleKeys.SQL_DELETE_ITEM_CART))) {

            preparedStatement.setString(1, cart.getEnProductName());
            preparedStatement.setString(2, cart.getRuProductName());
            preparedStatement.setString(3, cart.getUsername());
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

    private Cart extractResultSet(ResultSet resultSet) {
        Cart cart = new Cart();

        try {
            cart.setId(resultSet.getLong("id"));
            cart.setEnProductName(resultSet.getString("product_en_name"));
            cart.setRuProductName(resultSet.getString("product_ru_name"));
            cart.setPrice(resultSet.getLong("price"));
            cart.setAmount(resultSet.getInt("amount"));
            cart.setUsername(resultSet.getString("username"));
        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return cart;
    }
}
