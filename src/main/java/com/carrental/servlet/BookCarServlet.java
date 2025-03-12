package com.carrental.servlet;

import com.carrental.config.DBConnection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

/**
 * Servlet for handling car booking functionality.
 */
@WebServlet("/BookCarServlet")
public class BookCarServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Handles HTTP POST requests for booking a car.
     * 
     * @param request  The HTTP request.
     * @param response The HTTP response.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException      If an I/O error occurs.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve booking details from request parameters
        String userEmail = request.getParameter("userEmail");
        String carNumber = request.getParameter("carNumber");
        String pickupDate = request.getParameter("pickupDate");
        String returnDate = request.getParameter("returnDate");

        try (Connection conn = DBConnection.getConnection()) {
            // Start a database transaction to ensure atomicity
            conn.setAutoCommit(false);

            // Step 1: Get user_id from email
            String userQuery = "SELECT user_id FROM Users WHERE email = ?";
            PreparedStatement userStmt = conn.prepareStatement(userQuery);
            userStmt.setString(1, userEmail);
            ResultSet userRs = userStmt.executeQuery();

            // Check if the user exists
            if (!userRs.next()) {
                // Redirect to rent car page with error if user not found
                response.sendRedirect("rentCar.jsp?error=User not found");
                return;
            }
            int userId = userRs.getInt("user_id");

            // Step 2: Get car price_per_day from Cars table
            String carQuery = "SELECT price_per_day FROM Cars WHERE car_number = ?";
            PreparedStatement carStmt = conn.prepareStatement(carQuery);
            carStmt.setString(1, carNumber);
            ResultSet carRs = carStmt.executeQuery();

            // Check if the car exists
            if (!carRs.next()) {
                // Redirect to rent car page with error if car not found
                response.sendRedirect("rentCar.jsp?error=Car not found");
                return;
            }
            double pricePerDay = carRs.getDouble("price_per_day");

            // Step 3: Calculate rental duration and total cost
            java.sql.Date pickup = java.sql.Date.valueOf(pickupDate);
            java.sql.Date returnD = java.sql.Date.valueOf(returnDate);
            
            // Calculate duration in days
            long duration = (returnD.getTime() - pickup.getTime()) / (1000 * 60 * 60 * 24);
            if (duration <= 0) {
                // Redirect to rent car page with error if dates are invalid
                response.sendRedirect("rentCar.jsp?error=Invalid dates");
                return;
            }
            
            // Calculate total cost based on duration and price per day
            double totalCost = duration * pricePerDay;

            // Step 4: Insert booking into database
            String bookingQuery = "INSERT INTO bookings (user_id, car_number, pickup_date, return_date, status, total_price) VALUES (?, ?, ?, ?, 'completed', ?)";
            PreparedStatement bookingStmt = conn.prepareStatement(bookingQuery, Statement.RETURN_GENERATED_KEYS);
            bookingStmt.setInt(1, userId);
            bookingStmt.setString(2, carNumber);
            bookingStmt.setString(3, pickupDate);
            bookingStmt.setString(4, returnDate);
            bookingStmt.setDouble(5, totalCost);
            bookingStmt.executeUpdate();
            
            ResultSet generatedKeys = bookingStmt.getGeneratedKeys();
            int bookingId = 0;
            if (generatedKeys.next()) {
                bookingId = generatedKeys.getInt(1);
            }
            
            // Step 5: Update car status to "Rented"
            String updateCarQuery = "UPDATE Cars SET status = 'Rented' WHERE car_number = ?";
            PreparedStatement updateCarStmt = conn.prepareStatement(updateCarQuery);
            updateCarStmt.setString(1, carNumber);
            updateCarStmt.executeUpdate();

            // Commit the transaction to persist changes
            conn.commit();

            // Step 6: Redirect to payment page with booking details
            response.sendRedirect("payment.jsp?userEmail=" + userEmail + "&carNumber=" + carNumber + "&pickupDate=" + pickupDate + "&returnDate=" + returnDate + "&totalPrice=" + totalCost +"&bookingId=" + bookingId);

        } catch (SQLException e) {
            // Handle any database-related exceptions
            e.printStackTrace();
            response.sendRedirect("rentCar.jsp?error=Database error");
        }
    }
}