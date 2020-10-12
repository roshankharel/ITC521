package main;

/**
 * Env This class holds the database connection, window dimension, window title, and window
 * resizability properties.
 *
 * @author Roshan Kharel
 */
public final class Env {
    // publicly accessible static constants

    // database constants (* these three constants must be edited per MYSQL configuration)
    public static final String DATABASE_NAME = "A3T1";
    public static final String DATABASE_USERNAME = "root";
    public static final String DATABASE_PASSWORD = "";
    public static final int DATABASE_PORT = 3306;
    public static final String DATABASE_ADDRESS = "//localhost";

    // window constants
    public static final int WINDOW_WIDTH = 750;
    public static final int WINDOW_HEIGHT = 550;
    public static final String WINDOW_TITLE = "Staff Record Manager";
    public static final boolean WINDOW_IS_RESIZABLE = false;

    // JDBC specific constants
    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    public static final String JDBC_DB_URL = String.format("jdbc:mysql:%s:%d/%s", DATABASE_ADDRESS, DATABASE_PORT, DATABASE_NAME);

    /**
     * default private constructor, cannot be initialized
     */
    private Env() {
    }
}
