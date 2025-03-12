package com.carrental.dao;

import com.carrental.config.DBConnection;
import com.carrental.model.User;
import java.sql.*;

/**
 * Data Access Object (DAO) for interacting with user data in the database.
 */
public class UserDAO {

    /**
     * Registers a new user by inserting their details into the Users table.
     * 
     * @param user The User object containing the registration details.
     * @return True if the registration is successful; otherwise, false.
     */
    public boolean registerUser(User user) {
        // SQL query to insert a new user into the Users table
        String query = "INSERT INTO Users (name, email, phone, password, address) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             // Prepare a statement with the query to prevent SQL injection
             PreparedStatement stmt = conn.prepareStatement(query)) {
            // Set the user attributes as parameters
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPhone());
            stmt.setString(4, user.getPassword());
            stmt.setString(5, user.getAddress());
            // Execute the query and return true if at least one row is affected
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            // Handle any SQL-related exceptions
            e.printStackTrace();
            // Return false if an exception occurs
            return false;
        }
    }

    /**
     * Attempts to log in a user using the provided email and password.
     * 
     * @param email    The user's email.
     * @param password The user's password.
     * @return A User object if the login is successful; otherwise, null.
     */
    public User loginUser(String email, String password) {
        // SQL query to select all columns from the Users table where email and password match
        String query = "SELECT * FROM Users WHERE email = ? AND password = ?";

        try (Connection conn = DBConnection.getConnection();
             // Prepare a statement with the query to prevent SQL injection
             PreparedStatement stmt = conn.prepareStatement(query)) {
            // Set the email and password parameters
            stmt.setString(1, email);
            stmt.setString(2, password);
            // Execute the query and store the results in a ResultSet
            ResultSet rs = stmt.executeQuery();

            // Check if a matching user is found
            if (rs.next()) {
                // Create a new User object to store the user details
                User user = new User();
                // Set the user attributes from the result set
                user.setUserId(rs.getInt("user_id"));
                user.setName(rs.getString("name"));
                // Return the user object if login is successful
                return user;
            }
        } catch (SQLException e) {
            // Handle any SQL-related exceptions
            e.printStackTrace();
        }
        // Return null if login fails or an exception occurs
        return null;
    }
}
