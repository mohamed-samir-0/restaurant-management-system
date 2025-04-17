package com.restaurant.database;

import java.sql.*;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/restaurant_management"; // Replace with your database URL
    private static final String USER = "root"; // Replace with your database username
    private static final String PASSWORD = "3356"; // Replace with your database password

    // Method to establish a database connection
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Method to retrieve menu items from the database
    public static ResultSet getMenuItems() throws SQLException {
        Connection conn = getConnection();
        String query = "SELECT * FROM menu"; // Replace with the actual table and fields from your database
        Statement stmt = conn.createStatement();
        return stmt.executeQuery(query);
    }
}