package com.carrental.servlet;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.carrental.config.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/DownloadInvoiceServlet")
public class DownloadInvoiceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String bookingId = request.getParameter("bookingId");

        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT b.user_id, u.name, u.email, b.car_number, c.brand, c.model, b.pickup_date, b.return_date, b.total_price " +
                           "FROM bookings b " +
                           "JOIN Users u ON b.user_id = u.user_id " +
                           "JOIN Cars c ON b.car_number = c.car_number " +
                           "WHERE b.booking_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, bookingId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String userName = rs.getString("name");
                String userEmail = rs.getString("email");
                String carNumber = rs.getString("car_number");
                String carBrand = rs.getString("brand");
                String carModel = rs.getString("model");
                String pickupDate = rs.getString("pickup_date");
                String returnDate = rs.getString("return_date");
                double totalPrice = rs.getDouble("total_price");

                response.setContentType("application/pdf");
                response.setHeader("Content-Disposition", "attachment; filename=Invoice_" + bookingId + ".pdf");

                Document document = new Document();
                PdfWriter.getInstance(document, response.getOutputStream());
                document.open();

                Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
                Paragraph title = new Paragraph("Car Rental Invoice", titleFont);
                title.setAlignment(Element.ALIGN_CENTER);
                document.add(title);
                document.add(new Paragraph("\n"));

                document.add(new Paragraph("Invoice ID: " + bookingId));
                document.add(new Paragraph("Date: " + new SimpleDateFormat("yyyy-MM-dd").format(new Date())));
                document.add(new Paragraph("\n"));

                document.add(new Paragraph("Customer Name: " + userName));
                document.add(new Paragraph("Customer Email: " + userEmail));
                document.add(new Paragraph("\n"));

                document.add(new Paragraph("Car Number: " + carNumber));
                document.add(new Paragraph("Car Brand: " + carBrand));
                document.add(new Paragraph("Car Model: " + carModel));
                document.add(new Paragraph("\n"));

                document.add(new Paragraph("Pickup Date: " + pickupDate));
                document.add(new Paragraph("Return Date: " + returnDate));
                document.add(new Paragraph("\n"));

                Font priceFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14);
                document.add(new Paragraph("Total Price: $" + totalPrice, priceFont));

                document.close();
            } else {
                response.getWriter().write("Booking not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("Error generating invoice.");
        }
    }
}
