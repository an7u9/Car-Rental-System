package com.carrental.servlet;

import com.carrental.dao.UserDAO;
import com.carrental.model.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet for handling user login functionality.
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Handles HTTP POST requests for user login.
     * 
     * @param request  The HTTP request.
     * @param response The HTTP response.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException      If an I/O error occurs.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        // Retrieve email and password from request parameters
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Create an instance of UserDAO to interact with the database
        UserDAO userDAO = new UserDAO();

        // Attempt to log in the user using the provided credentials
        User user = userDAO.loginUser(email, password);

        // Check if the login was successful
        if (user != null) {
            // Create or retrieve the current HTTP session
            HttpSession session = request.getSession();
            // Store the logged-in user in the session
            session.setAttribute("user", user);
            // Redirect to the user dashboard
            response.sendRedirect("dashboard.jsp");
        } else {
            // Redirect back to the login page with an error message if credentials are invalid
            response.sendRedirect("login.jsp?error=InvalidCredentials");
        }
    }
}
