package ua.testing.model.dao.connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.log4j.Logger;
import ua.testing.model.util.BundleKeys;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionPoolHolder {
    private static final Logger LOGGER = Logger.getLogger(ConnectionPoolHolder.class);
    private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("db");
    private static ConnectionPoolHolder instance;
    private HikariDataSource dataSource;

    private ConnectionPoolHolder() {
        HikariConfig config = new HikariConfig();

        config.setJdbcUrl(BUNDLE.getString(BundleKeys.DB_URL));
        config.setUsername(BUNDLE.getString(BundleKeys.DB_USERNAME));
        config.setPassword(BUNDLE.getString(BundleKeys.DB_PASSWORD));
        config.addDataSourceProperty("cachePrepStmts" , "true");
        config.addDataSourceProperty("prepStmtCacheSize" , "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit" , "2048");

        dataSource = new HikariDataSource(config);
    }

    public static ConnectionPoolHolder getInstance() {
        if (instance == null) {
            synchronized (ConnectionPoolHolder.class) {
                if (instance == null) {
                    instance = new ConnectionPoolHolder();
                }
            }
        }

        return instance;
    }

    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            LOGGER.error(e);
            return null;
        }
    }
}
