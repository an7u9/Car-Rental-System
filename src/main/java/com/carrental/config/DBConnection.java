package com.carrental.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Utility class for establishing a connection to the database.
 */
public class DBConnection {
    // Database connection properties
    private static final String URL = "jdbc:mysql://localhost:3306/car"; // Database URL
    private static final String USER = "root"; // Database username
    private static final String PASSWORD = "anshu@12"; // Database password

    /**
     * Establishes a connection to the database using the provided properties.
     * 
     * @return A Connection object if successful; otherwise, null.
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver"); // MySQL Connector 9.0

            // Establish a connection to the database using the DriverManager
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            // Handle exceptions related to driver loading or connection establishment
            e.printStackTrace();
        }
        return conn;
    }
}
