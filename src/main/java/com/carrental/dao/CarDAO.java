package com.carrental.dao;

import com.carrental.config.DBConnection;
import com.carrental.model.Car;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Data Access Object (DAO) for interacting with car data in the database.
 */
public class CarDAO {

    /**
     * Retrieves all cars from the database and returns them as a map where the key is the car number.
     * 
     * @return A map of cars where each key is a car number and the value is the corresponding Car object.
     */
    public Map<String, Car> getAllCarsAsMap() {
        // Initialize an empty map to store cars
        Map<String, Car> cars = new HashMap<>();

        try (Connection conn = DBConnection.getConnection()) {
            // Check if the database connection was successful
            if (conn == null) {
                System.err.println("❌ Database connection failed!");
                return null;
            }
            System.out.println("✅ Connected to the database.");

            // SQL query to select all columns from the Cars table
            String query = "SELECT * FROM Cars";
            // Create a statement object to execute the query
            Statement stmt = conn.createStatement();
            // Execute the query and store the results in a ResultSet
            ResultSet rs = stmt.executeQuery(query);

            // Iterate through each row in the result set
            while (rs.next()) {
                // Create a new Car object for each row
                Car car = new Car();
                // Set the car attributes from the current row
                car.setCarNumber(rs.getString("car_number"));
                car.setBrand(rs.getString("brand"));
                car.setModel(rs.getString("model"));
                car.setYear(rs.getInt("year"));
                car.setPricePerDay(rs.getDouble("price_per_day"));
                car.setStatus(rs.getString("status"));

                // Add the car to the map using its car number as the key
                cars.put(car.getCarNumber(), car);
            }
        } catch (SQLException e) {
            // Handle any SQL-related exceptions
            e.printStackTrace();
        }
        return cars;
    }
}
