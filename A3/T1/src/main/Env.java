package main;

public final class Env {
    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    public static final String JDBC_DB_URL = "jdbc:mysql://localhost:3306/A3T1";

    public static final String DB_USERNAME = "root";
    public static final String DB_PASSWORD = "";

    public static final int WINDOW_WIDTH = 750;
    public static final int WINDOW_HEIGHT = 550;
    public static final String WINDOW_TITLE = "Staff Record Manager";
    public static final boolean WINDOW_IS_RESIZABLE = false;

    private Env() {
    }
}
