package com.carrental.servlet;

import com.carrental.dao.AdminDAO;
import com.carrental.model.Admin;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet for handling admin login functionality.
 */
@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Handles HTTP POST requests for admin login.
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

        // Create an instance of AdminDAO to interact with the database
        AdminDAO adminDAO = new AdminDAO();

        // Attempt to log in the admin using the provided credentials
        Admin admin = adminDAO.loginAdmin(email, password);

        // Check if the login was successful
        if (admin != null) {
            // Create or retrieve the current HTTP session
            HttpSession session = request.getSession();
            // Store the logged-in admin in the session
            session.setAttribute("admin", admin);
            // Redirect to the admin dashboard
            response.sendRedirect("admin_dashboard.jsp");
        } else {
            // Redirect back to the login page with an error message if credentials are invalid
            response.sendRedirect("admin_login.jsp?error=InvalidCredentials");
        }
    }
}
