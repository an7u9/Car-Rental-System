package com.carrental.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.carrental.config.DBConnection;

/**
 * Servlet for handling car deletion functionality.
 */
@WebServlet("/DeleteCarServlet")
public class DeleteCarServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Handles HTTP POST requests for deleting a car.
     * 
     * @param request  The HTTP request.
     * @param response The HTTP response.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException      If an I/O error occurs.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the car number to be deleted from the request
        String carNumber = request.getParameter("carNumber");
        System.out.println("Car to delete: " + carNumber); // Debugging statement

        try (Connection con = DBConnection.getConnection()) {
            // Step 1: Delete related bookings for the car
            String deleteBookingsQuery = "DELETE FROM Bookings WHERE car_number=?";
            try (PreparedStatement stmt1 = con.prepareStatement(deleteBookingsQuery)) {
                stmt1.setString(1, carNumber);
                // Execute the query to remove bookings
                stmt1.executeUpdate();
            }

            // Step 2: Delete the car from the Cars table
            String deleteCarQuery = "DELETE FROM Cars WHERE car_number=?";
            try (PreparedStatement stmt2 = con.prepareStatement(deleteCarQuery)) {
                stmt2.setString(1, carNumber);
                // Execute the query and check the number of rows affected
                int rowsAffected = stmt2.executeUpdate();

                if (rowsAffected > 0) {
                    // Redirect to admin car page with success message if car is deleted
                    response.sendRedirect("admin_car.jsp?message=Car Deleted Successfully");
                } else {
                    // Redirect to admin car page with error if car not found
                    response.sendRedirect("admin_car.jsp?error=Car Not Found");
                }
            }
        } catch (Exception e) {
            // Handle any exceptions during database operations
            e.printStackTrace();
            // Redirect to admin car page with error if something goes wrong
            response.sendRedirect("admin_car.jsp?error=Something Went Wrong");
        }
    }
}
