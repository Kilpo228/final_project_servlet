package ua.testing.model.util;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ScriptRunner {
    private static final Logger LOGGER = Logger.getLogger(ScriptRunner.class);

    public static void execute(Connection connection, String path) {
        try (PreparedStatement statement = connection.prepareStatement(getStatementFromFile(path))) {
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    private static String getStatementFromFile(String path) {
        StringBuilder builder = new StringBuilder();
        String line;

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        } catch (IOException e) {
            LOGGER.error(e);
        }

        return builder.toString();
    }
}
