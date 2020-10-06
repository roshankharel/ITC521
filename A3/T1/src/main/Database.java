package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public final class Database {
    private static Database instance;
    private final Connection connection;

    public Database() throws ClassNotFoundException, SQLException {
        // Load JDBC driver
        Class.forName(Env.JDBC_DRIVER);
        // Establish a connection
        connection = DriverManager.getConnection(Env.JDBC_DB_URL, getConnectionProperties());
    }

    public static Database getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = new Database();
        }

        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public void close() throws SQLException {
        connection.close();
        instance = null;
    }

    private Properties getConnectionProperties() {
        Properties properties = new Properties();
        properties.setProperty("user", Env.DB_USERNAME);
        properties.setProperty("password", Env.DB_PASSWORD);
        properties.setProperty("profileSQL", "true");

        return properties;
    }
}
