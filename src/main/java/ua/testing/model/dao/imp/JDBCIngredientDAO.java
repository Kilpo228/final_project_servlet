package ua.testing.model.dao.imp;

import org.apache.log4j.Logger;
import ua.testing.model.annotation.DAO;
import ua.testing.model.annotation.InjectByType;
import ua.testing.model.dao.IngredientDAO;
import ua.testing.model.entity.Ingredient;
import ua.testing.model.util.BundleKeys;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DAO
public class JDBCIngredientDAO implements IngredientDAO {
    private static final Logger LOGGER = Logger.getLogger(JDBCIngredientDAO.class);

    @InjectByType
    private Connection connection;

    @Override
    public List<Ingredient> findAll() {
        List<Ingredient> ingredients = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(
                BUNDLE.getString(BundleKeys.SQL_FIND_ALL_INGREDIENT));
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                ingredients.add(extractResultSet(resultSet));
            }

        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return ingredients;
    }

    @Override
    public Optional<Ingredient> findByName(String name) {
        Ingredient ingredient = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(
                BUNDLE.getString(BundleKeys.SQL_FIND_BY_NAME_INGREDIENT))) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, name);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    ingredient = extractResultSet(resultSet);
                }
            }

        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return Optional.ofNullable(ingredient);
    }

    @Override
    public List<Ingredient> findAllOrderByEnName() {
        List<Ingredient> ingredients = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(
                BUNDLE.getString(BundleKeys.SQL_FIND_ALL_ORDER_BY_EN_NAME_INGREDIENT));
             ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    ingredients.add(extractResultSet(resultSet));
                }

        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return ingredients;
    }

    @Override
    public List<Ingredient> findAllOrderByRuName() {
        List<Ingredient> ingredients = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(
                BUNDLE.getString(BundleKeys.SQL_FIND_ALL_ORDER_BY_RU_NAME_INGREDIENT));
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                ingredients.add(extractResultSet(resultSet));
            }

        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return ingredients;
    }

    @Override
    public List<Ingredient> findAllOrderByAmount() {
        List<Ingredient> ingredients = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(
                BUNDLE.getString(BundleKeys.SQL_FIND_ALL_ORDER_BY_AMOUNT_INGREDIENT));
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                ingredients.add(extractResultSet(resultSet));
            }

        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return ingredients;
    }

    @Override
    public int findAmountByName(String name) {
        int amount = 0;

        try (PreparedStatement preparedStatement = connection.prepareStatement(
                BUNDLE.getString(BundleKeys.SQL_FIND_AMOUNT_INGREDIENT))) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, name);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    amount = resultSet.getInt("amount");
                }
            }

        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return amount;
    }

    @Override
    public List<Ingredient> findAllWithAmountGreaterThanZero() {
        List<Ingredient> ingredients = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(
                BUNDLE.getString(BundleKeys.SQL_FIND_ALL_WITH_AMOUNT_GREATER_THAN_ZERO));
            ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                ingredients.add(extractResultSet(resultSet));
            }

        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return ingredients;
    }

    @Override
    public void save(Ingredient ingredient) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                BUNDLE.getString(BundleKeys.SQL_SAVE_INGREDIENT))) {

            preparedStatement.setString(1, ingredient.getEnName());
            preparedStatement.setString(2, ingredient.getRuName());
            preparedStatement.setInt(3, ingredient.getAmount());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void update(Ingredient ingredient) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                BUNDLE.getString(BundleKeys.SQL_UPDATE_INGREDIENT))) {

            preparedStatement.setString(1, ingredient.getEnName());
            preparedStatement.setString(2, ingredient.getRuName());
            preparedStatement.setInt(3, ingredient.getAmount());
            preparedStatement.setLong(4, ingredient.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void increaseAmountByName(String name) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                BUNDLE.getString(BundleKeys.SQL_UPDATE_INCREASE_AMOUNT_INGREDIENT))) {

            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void decreaseAmountByName(String name) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                BUNDLE.getString(BundleKeys.SQL_UPDATE_DECREASE_AMOUNT_INGREDIENT))) {

            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void delete(long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                BUNDLE.getString(BundleKeys.SQL_DELETE_INGREDIENT))) {

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void deleteByName(String name) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                BUNDLE.getString(BundleKeys.SQL_DELETE_BY_NAME_INGREDIENT))) {

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

    private Ingredient extractResultSet(ResultSet resultSet) {
        Ingredient ingredient = new Ingredient();

        try {
            ingredient.setId(resultSet.getLong("id"));
            ingredient.setEnName(resultSet.getString("ingredient_en_name"));
            ingredient.setRuName(resultSet.getString("ingredient_ru_name"));
            ingredient.setAmount(resultSet.getInt("amount"));
        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return ingredient;
    }
}
