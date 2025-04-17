package com.restaurant.singleton;

import com.restaurant.model.Order;
import com.restaurant.model.Customer;
import com.restaurant.database.DatabaseConnection;

import java.sql.*;
import java.util.List;

public class OrderManager {
    private static OrderManager instance;

    private OrderManager() {}

    public static OrderManager getInstance() {
        if (instance == null) {
            instance = new OrderManager();
        }
        return instance;
    }

    public void placeOrder(Order order) {
        // Insert customer data into the database
        try (Connection conn = DatabaseConnection.getConnection()) {
            if (conn != null) {
                // Insert Customer
                String customerQuery = "INSERT INTO customers (name, phone, email, address) VALUES (?, ?, ?, ?)";
                try (PreparedStatement ps = conn.prepareStatement(customerQuery, Statement.RETURN_GENERATED_KEYS)) {
                    ps.setString(1, order.getCustomer().getName());
                    ps.setString(2, order.getCustomer().getPhone());
                    ps.setString(3, order.getCustomer().getEmail());
                    ps.setString(4, order.getCustomer().getAddress());
                    ps.executeUpdate();

                    // Retrieve the auto-generated ID of the customer
                    ResultSet generatedKeys = ps.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        int customerId = generatedKeys.getInt(1);
                        order.getCustomer().setId(customerId);
                    }
                }

                // Insert Order
                String orderQuery = "INSERT INTO orders (customer_id, total_cost, order_status, payment_status) VALUES (?, ?, ?, ?)";
                try (PreparedStatement ps = conn.prepareStatement(orderQuery)) {
                    ps.setInt(1, order.getCustomer().getId());
                    ps.setDouble(2, order.getTotalCost());
                    ps.setString(3, order.getOrderStatus());
                    ps.setString(4, order.getPaymentStatus());
                    ps.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}