package projects.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import projects.exception.DbException;

/**
 * Utility class for obtaining database connections.
 */
public class DbConnection {
    private static String HOST = "localhost";
    private static String PASSWORD = "Andr0!ds";
    private static int PORT = 3306;
    private static String SCHEMA = "projects";
    private static String USER = "projects";

    /**
     * Obtains a connection to the MySQL database.
     *
     * @return A Connection object.
     * @throws DbException If the connection cannot be established.
     */
    
    public static Connection getConnection() {
        String uri = String.format("jdbc:mysql://%s:%d/%s?user=%s&password=%s", HOST, PORT, SCHEMA, USER, PASSWORD);
        try {
            Connection conn = DriverManager.getConnection(uri);
            System.out.println("Connection to schema '" + SCHEMA + "' is successful.");
            return conn;
        } catch (SQLException e) {
            System.out.println("Unable to get connection at " + uri);
            throw new DbException("Unable to get connection at " + uri);
        }
    }
}