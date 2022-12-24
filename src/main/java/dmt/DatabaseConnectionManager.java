package dmt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionManager {
    // Private static variables for the singleton instance and the connection
    private static DatabaseConnectionManager instance;
    private static Connection conn;

    // Private constructor to prevent direct instantiation
    private DatabaseConnectionManager() {
        // Establish the connection to the database
        try {
            conn = DriverManager.getConnection("jdbc:sqlserver://aei-sql2.avans.nl:1443", "GeoGSSR", "Error404");
        } catch (SQLException e) {
            // Handle any errors
        }
    }

    // Public static method to get the singleton instance
    public static DatabaseConnectionManager getInstance() {
        // Check if the instance already exists
        if (instance == null) {
            // If not, create a new instance
            instance = new DatabaseConnectionManager();
        }

        // Return the instance
        return instance;
    }

    // Public method to get the connection
    public Connection getConnection() {
        return conn;
    }
}
