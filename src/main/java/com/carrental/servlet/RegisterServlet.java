package com.carrental.servlet;

import com.carrental.dao.UserDAO;
import com.carrental.model.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet for handling user registration functionality.
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Handles HTTP POST requests for user registration.
     * 
     * @param request  The HTTP request.
     * @param response The HTTP response.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException      If an I/O error occurs.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Create a new User object to store registration details
        User user = new User();
        // Set user attributes from request parameters
        user.setName(request.getParameter("name"));
        user.setEmail(request.getParameter("email"));
        user.setPhone(request.getParameter("phone"));
        user.setPassword(request.getParameter("password"));
        user.setAddress(request.getParameter("address"));

        // Create an instance of UserDAO to interact with the database
        UserDAO userDAO = new UserDAO();

        // Attempt to register the user
        if (userDAO.registerUser(user)) {
            // Redirect to the login page if registration is successful
            response.sendRedirect("login.jsp");
        } else {
            // Redirect back to the registration page with an error message if registration fails
            response.sendRedirect("register.jsp?error=RegistrationFailed");
        }
    }
}
