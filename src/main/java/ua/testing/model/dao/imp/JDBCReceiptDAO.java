package ua.testing.model.dao.imp;

import org.apache.log4j.Logger;
import ua.testing.model.annotation.DAO;
import ua.testing.model.annotation.InjectByType;
import ua.testing.model.dao.ReceiptDAO;
import ua.testing.model.entity.Receipt;
import ua.testing.model.util.BundleKeys;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@DAO
@SuppressWarnings("Duplicates")
public class JDBCReceiptDAO implements ReceiptDAO {
    private static final Logger LOGGER = Logger.getLogger(JDBCReceiptDAO.class);

    @InjectByType
    private Connection connection;

    @Override
    public void save(Receipt receipt) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                BUNDLE.getString(BundleKeys.SQL_SAVE_RECEIPT))) {

            preparedStatement.setString(1, receipt.getDateTime().toString());
            preparedStatement.setString(2, receipt.getEnProductName());
            preparedStatement.setString(3, receipt.getRuProductName());
            preparedStatement.setLong(4, receipt.getPrice());
            preparedStatement.setInt(5, receipt.getAmount());
            preparedStatement.setString(6, receipt.getUsername());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public List<Receipt> findAll() {
        List<Receipt> receipts = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(
                BUNDLE.getString(BundleKeys.SQL_FIND_ALL_RECEIPT))) {

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    receipts.add(extractResultSet(resultSet));
                }
            }

        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return receipts;
    }

    @Override
    public List<Receipt> findAllReceiptByUsername(String username) {
        List<Receipt> receipts = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(
                BUNDLE.getString(BundleKeys.SQL_FIND_ALL_BY_USERNAME_RECEIPT))) {

            preparedStatement.setString(1, username);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    receipts.add(extractResultSet(resultSet));
                }
            }

        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return receipts;
    }

    @Override
    public void update(Receipt receipt) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                BUNDLE.getString(BundleKeys.SQL_UPDATE_RECEIPT))) {

            preparedStatement.setObject(1, receipt.getDateTime());
            preparedStatement.setString(2, receipt.getEnProductName());
            preparedStatement.setString(3, receipt.getRuProductName());
            preparedStatement.setLong(4, receipt.getPrice());
            preparedStatement.setInt(5, receipt.getAmount());
            preparedStatement.setString(6, receipt.getUsername());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void delete(long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                BUNDLE.getString(BundleKeys.SQL_DELETE_RECEIPT))) {

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

    private Receipt extractResultSet(ResultSet resultSet) {
        Receipt receipt = new Receipt();

        try {
            receipt.setId(resultSet.getLong("id"));
            receipt.setDateTime(LocalDateTime.parse(resultSet.getString("date_of_order")));
            receipt.setEnProductName(resultSet.getString("product_en_name"));
            receipt.setRuProductName(resultSet.getString("product_ru_name"));
            receipt.setPrice(resultSet.getLong("price"));
            receipt.setAmount(resultSet.getInt("amount"));
            receipt.setUsername(resultSet.getString("username"));
        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return receipt;
    }
}
