package com.carrental.servlet;
import com.carrental.config.DBConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

/**
 * Servlet for handling car addition and update operations.
 */
@WebServlet("/AddCarServlet")
public class AddCarServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Handles HTTP GET requests. Used for fetching car details when editing.
     * 
     * @param request  The HTTP request.
     * @param response The HTTP response.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException      If an I/O error occurs.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        // Check if a car number is provided for editing
        String carNumber = request.getParameter("carNumber");
        if (carNumber != null && !carNumber.isEmpty()) {
            try (Connection conn = DBConnection.getConnection();
                 // Prepare a query to fetch car details by car number
                 PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Cars WHERE car_number = ?")) {
                
                // Set the car number parameter
                stmt.setString(1, carNumber);
                ResultSet rs = stmt.executeQuery();

                // Check if a car with the given number exists
                if (rs.next()) {
                    // Set request attributes for the car details
                    request.setAttribute("carNumber", rs.getString("car_number"));
                    request.setAttribute("brand", rs.getString("brand"));
                    request.setAttribute("model", rs.getString("model"));
                    request.setAttribute("year", rs.getInt("year"));
                    request.setAttribute("pricePerDay", rs.getDouble("price_per_day"));
                    request.setAttribute("status", rs.getString("status")); // Add status attribute

                    // Forward to the update car page
                    RequestDispatcher dispatcher = request.getRequestDispatcher("update_car.jsp");
                    dispatcher.forward(request, response);
                    return;
                }
            } catch (Exception e) {
                // Handle any exceptions during database operations
                e.printStackTrace();
            }
        }
        // Redirect to car list if no car is found or car number is missing
        response.sendRedirect("car_list.jsp?error=CarNotFound");
    }

    /**
     * Handles HTTP POST requests. Used for adding or updating a car.
     * 
     * @param request  The HTTP request.
     * @param response The HTTP response.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException      If an I/O error occurs.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        // Determine the action type (add or update)
        String action = request.getParameter("action"); // "add" or "update"
        String carNumber = request.getParameter("car_number");
        String brand = request.getParameter("brand");
        String model = request.getParameter("model");
        String status = request.getParameter("status"); // New field for status (Available, Rented, Maintenance)

        int year = 0;
        double pricePerDay = 0.0;

        try {
            // Parse year and price per day from request parameters
            year = Integer.parseInt(request.getParameter("year"));
            pricePerDay = Double.parseDouble(request.getParameter("price_per_day"));
        } catch (NumberFormatException e) {
            // Handle invalid year or price format
            response.sendRedirect("update_car.jsp?error=InvalidYearOrPrice");
            return;
        }

        // Determine if this is an update operation
        boolean isUpdate = "update".equalsIgnoreCase(action);

        // Construct the SQL query based on the action type
        String query = isUpdate
            ? "UPDATE Cars SET brand=?, model=?, year=?, price_per_day=?, status=? WHERE car_number=?"
            : "INSERT INTO Cars (car_number, brand, model, year, price_per_day, status) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             // Prepare the statement for the constructed query
             PreparedStatement stmt = conn.prepareStatement(query)) {

            if (isUpdate) {
                // Set parameters for update query
                stmt.setString(1, brand);
                stmt.setString(2, model);
                stmt.setInt(3, year);
                stmt.setDouble(4, pricePerDay);
                stmt.setString(5, status);
                stmt.setString(6, carNumber);
            } else {
                // Set parameters for insert query
                stmt.setString(1, carNumber);
                stmt.setString(2, brand);
                stmt.setString(3, model);
                stmt.setInt(4, year);
                stmt.setDouble(5, pricePerDay);
                // Default status for new cars is "Available"
                stmt.setString(6, "Available");
            }

            // Execute the query and check the number of rows affected
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                // Redirect to admin car page with success message
                response.sendRedirect("admin_car.jsp?success=" + (isUpdate ? "CarUpdated" : "CarAdded"));
            } else {
                // Redirect to update car page if operation fails
                response.sendRedirect("update_car.jsp?error=OperationFailed");
            }

        } catch (Exception e) {
            // Handle any database-related exceptions
            e.printStackTrace();
            response.sendRedirect("update_car.jsp?error=DBError");
        }
    }
}