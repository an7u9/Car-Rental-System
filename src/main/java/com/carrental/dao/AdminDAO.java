package com.carrental.dao;

import com.carrental.config.DBConnection;
import com.carrental.model.Admin;
import java.sql.*;

/**
 * Data Access Object (DAO) for interacting with admin data in the database.
 */
public class AdminDAO {

    /**
     * Attempts to log in an admin using the provided email and password.
     * 
     * @param email    The admin's email.
     * @param password The admin's password.
     * @return An Admin object if the login is successful; otherwise, null.
     */
    public Admin loginAdmin(String email, String password) {
        // SQL query to select all columns from the admins table where email and password match
        String query = "SELECT * FROM admins WHERE email = ? AND password = ?";

        try (Connection conn = DBConnection.getConnection();
             // Prepare a statement with the query to prevent SQL injection
             PreparedStatement stmt = conn.prepareStatement(query)) {
            // Set the email and password parameters
            stmt.setString(1, email);
            stmt.setString(2, password);
            // Execute the query and store the results in a ResultSet
            ResultSet rs = stmt.executeQuery();

            // Check if a matching admin is found
            if (rs.next()) {
                // Create a new Admin object to store the admin details
                Admin admin = new Admin();
                // Set the admin attributes from the result set
                admin.setAdminId(rs.getInt("admin_id"));
                admin.setName(rs.getString("name"));
                // Return the admin object if login is successful
                return admin;
            }
        } catch (SQLException e) {
            // Handle any SQL-related exceptions
            e.printStackTrace();
        }
        // Return null if login fails or an exception occurs
        return null;
    }
}
