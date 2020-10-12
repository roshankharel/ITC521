package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public final class Database {
    /**
     * Holds reference to self instance
     */
    private static Database instance;

    /**
     * Holds reference to database connection instance
     */
    private final Connection connection;

    /**
     * private class constructor, follows singleton pattern
     */
    public Database() throws ClassNotFoundException, SQLException {
        // Load JDBC driver
        Class.forName(Env.JDBC_DRIVER);
        // Establish a connection
        connection = DriverManager.getConnection(Env.JDBC_DB_URL, getConnectionProperties());
    }

    /**
     * A static method to get the instance this class
     */
    public static Database getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = new Database();
        }

        return instance;
    }

    /**
     * Return reference to the database connection
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Releases database resources and nulls previous self reference
     */
    public void close() throws SQLException {
        connection.close();
        instance = null;
    }

    /**
     * This methods creates and returns database connection properties
     *
     * @return database connection properties
     */
    private Properties getConnectionProperties() {

        Properties properties = new Properties();
        properties.setProperty("user", Env.DB_USERNAME);
        properties.setProperty("password", Env.DB_PASSWORD);
        properties.setProperty("profileSQL", "true");

        return properties;
    }
}
