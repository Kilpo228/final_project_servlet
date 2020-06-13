package ua.testing.model.dao.imp;

import org.apache.log4j.Logger;
import ua.testing.model.annotation.DAO;
import ua.testing.model.annotation.InjectByType;
import ua.testing.model.dao.ProductDAO;
import ua.testing.model.entity.Ingredient;
import ua.testing.model.entity.Product;
import ua.testing.model.entity.ProductCategory;
import ua.testing.model.util.BundleKeys;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@DAO
@SuppressWarnings("Duplicates")
public class JDBCProductDAO implements ProductDAO {
    private static final Logger LOGGER = Logger.getLogger(JDBCProductDAO.class);

    @InjectByType
    private Connection connection;

    @Override
    public void decreaseAmount(String enProductName, int amount) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                BUNDLE.getString(BundleKeys.SQL_UPDATE_DECREASE_AMOUNT_PRODUCT))) {

            preparedStatement.setInt(1, amount);
            preparedStatement.setString(2, enProductName);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public Optional<Product> findProductByEnName(String name) {
        Map<String, Product> product = new HashMap<>();
        String productName = "";

        try (PreparedStatement preparedStatement = connection.prepareStatement(
                BUNDLE.getString(BundleKeys.SQL_FIND_BY_EN_NAME_PRODUCT))) {

            preparedStatement.setString(1, name);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    productName = resultSet.getString("product_en_name");
                    extractResultSet(resultSet, product);
                }
            }

        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return Optional.ofNullable(product.get(productName));
    }

    @Override
    public Optional<Product> findProductByRuName(String name) {
        Map<String, Product> product = new HashMap<>();
        String productName = "";

        try (PreparedStatement preparedStatement = connection.prepareStatement(
                BUNDLE.getString(BundleKeys.SQL_FIND_BY_RU_NAME_PRODUCT))) {

            preparedStatement.setString(1, name);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    productName = resultSet.getString("product_en_name");
                    extractResultSet(resultSet, product);
                }
            }

        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return Optional.ofNullable(product.get(productName));
    }

    @Override
    public Optional<Product> findProductByName(String name) {
        Map<String, Product> product = new HashMap<>();
        String productName = "";

        try (PreparedStatement preparedStatement = connection.prepareStatement(
                BUNDLE.getString(BundleKeys.SQL_FIND_BY_NAME_PRODUCT))) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, name);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    productName = resultSet.getString("product_en_name");
                    extractResultSet(resultSet, product);
                }
            }

        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return Optional.ofNullable(product.get(productName));
    }

    @Override
    public List<Product> findAllAvailableProducts() {
        Map<String, Product> products = new HashMap<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(
                BUNDLE.getString(BundleKeys.SQL_FIND_ALL_AVAILABLE_PRODUCT));
            ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                extractResultSet(resultSet, products);
            }

        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return new ArrayList<>(products.values());
    }

    @Override
    public List<Product> findAllOrderByEnName() {
        Map<String, Product> products = new LinkedHashMap<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(
                BUNDLE.getString(BundleKeys.SQL_FIND_ALL_ORDER_BY_EN_NAME_PRODUCT));
            ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                extractResultSet(resultSet, products);
            }

        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return new ArrayList<>(products.values());
    }

    @Override
    public List<Product> findAllOrderByRuName() {
        Map<String, Product> products = new LinkedHashMap<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(
                BUNDLE.getString(BundleKeys.SQL_FIND_ALL_ORDER_BY_RU_NAME_PRODUCT));
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                extractResultSet(resultSet, products);
            }

        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return new ArrayList<>(products.values());
    }

    @Override
    public List<Product> findAllOrderByAmount() {
        Map<String, Product> products = new LinkedHashMap<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(
                BUNDLE.getString(BundleKeys.SQL_FIND_ALL_ORDER_BY_AMOUNT_PRODUCT));
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                extractResultSet(resultSet, products);
            }

        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return new ArrayList<>(products.values());
    }

    @Override
    public List<Product> findAllOrderByCategory() {
        Map<String, Product> products = new LinkedHashMap<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(
                BUNDLE.getString(BundleKeys.SQL_FIND_ALL_ORDER_BY_CATEGORY_PRODUCT));
            ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                extractResultSet(resultSet, products);
            }

        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return new ArrayList<>(products.values());
    }

    @Override
    public List<Product> findAllOrderByPrice() {
        Map<String, Product> products = new LinkedHashMap<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(
                BUNDLE.getString(BundleKeys.SQL_FIND_ALL_ORDER_BY_PRICE_PRODUCT));
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                extractResultSet(resultSet, products);
            }

        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return new ArrayList<>(products.values());
    }

    @Override
    public List<Product> findAll() {
        Map<String, Product> products = new HashMap<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(
                BUNDLE.getString(BundleKeys.SQL_FIND_ALL_PRODUCT));
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                extractResultSet(resultSet, products);
            }

        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return new ArrayList<>(products.values());
    }

    @Override
    public void save(Product product) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                BUNDLE.getString(BundleKeys.SQL_SAVE_PRODUCT))) {

            connection.setAutoCommit(false);
            Iterator<Ingredient> iterator = product.getIngredients().iterator();

            do {
                preparedStatement.setString(1, product.getCategory().name());
                preparedStatement.setString(2, product.getEnName());
                preparedStatement.setString(3, product.getRuName());
                preparedStatement.setInt(4, product.getAmount());
                preparedStatement.setLong(5, product.getPrice());

                try {
                    preparedStatement.setString(6, iterator.next().getEnName());
                } catch (NoSuchElementException e) {
                    preparedStatement.setString(6, null);
                }

                preparedStatement.addBatch();
            } while (iterator.hasNext());

            preparedStatement.executeBatch();
            connection.commit();

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException rollBackException) {
                LOGGER.error(rollBackException);
            }

            LOGGER.error(e);
        }
    }

    @Override
    public void update(Product product) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                BUNDLE.getString(BundleKeys.SQL_UPDATE_PRODUCT))) {

            preparedStatement.setString(1, product.getCategory().name());
            preparedStatement.setString(2, product.getEnName());
            preparedStatement.setString(3, product.getRuName());
            preparedStatement.setInt(4, product.getAmount());
            preparedStatement.setLong(5, product.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void updateAmountAndPrice(long cents, int amount, String name) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                BUNDLE.getString(BundleKeys.SQL_UPDATE_AMOUNT_AND_PRICE_PRODUCT))) {

            preparedStatement.setInt(1, amount);
            preparedStatement.setLong(2, cents);
            preparedStatement.setString(3, name);
            preparedStatement.setString(4, name);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void delete(long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                BUNDLE.getString(BundleKeys.SQL_DELETE_PRODUCT))) {

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void deleteByName(String name) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                BUNDLE.getString(BundleKeys.SQL_DELETE_BY_NAME_PRODUCT))) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, name);
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

    private void extractResultSet(ResultSet resultSet, Map<String, Product> products) {
        Product product;

        try {
            product = products.computeIfAbsent(resultSet.getString("product_en_name"), key -> new Product());

            product.setId(resultSet.getLong("id"));
            product.setCategory(ProductCategory.valueOf(resultSet.getString("category")));
            product.setEnName(resultSet.getString("product_en_name"));
            product.setRuName(resultSet.getString("product_ru_name"));
            product.setAmount(resultSet.getInt("amount"));
            product.setPrice(resultSet.getLong("price"));
            product.getIngredients().add(
                    new Ingredient(
                            Optional.of(resultSet.getLong("id")).orElse(0L),
                            Optional.ofNullable(resultSet.getString("ingredient_en_name")).orElse(""),
                            Optional.ofNullable(resultSet.getString("ingredient_ru_name")).orElse(""),
                            Optional.of(resultSet.getInt("amount")).orElse(0))
            );
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }
}
