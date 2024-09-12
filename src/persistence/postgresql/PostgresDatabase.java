package persistence.postgresql;

import persistence.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PostgresDatabase implements Database {

    private static final Logger LOGGER = Logger.getLogger(PostgresDatabase.class.getName());

    @Override
    public String getUrl() {
        return "jdbc:postgresql://localhost:5432/postgres";
    }

    @Override
    public String getUser() {
        return "postgres";
    }

    @Override
    public String getPassword() {
        return "5432";
    }

    @Override
    public Connection getConnection() {
        try {
            return DriverManager.getConnection(getUrl(), getUser(), getPassword());
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Unable to acquire JDBC connection: " + e.getMessage());
            System.exit(0);
            throw new RuntimeException(e);
        }
    }
}
