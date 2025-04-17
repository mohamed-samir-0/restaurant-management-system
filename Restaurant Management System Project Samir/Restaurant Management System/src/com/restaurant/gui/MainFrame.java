package com.restaurant.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import com.restaurant.database.DatabaseConnection;  // Import the new DatabaseConnection class
import com.restaurant.factory.MenuItemFactory;

public class MainFrame extends JFrame {
    private JTabbedPane tabbedPane;
    private JPanel menuPanel;
    private JPanel ordersPanel;
    private JPanel reservationsPanel;
    private JPanel customerDetailsPanel;
    private JButton placeOrderButton;
    private JButton makeReservationButton; // New button for reservation

    private JTable menuTable;
    private JTable ordersTable;
    private JTable reservationsTable;
    private JTable customerDetailsTable;

    public MainFrame() {
        // Set title and window properties
        setTitle("Restaurant Management");
        setSize(1200, 800);  // Adjusted window size for better proportions
        setLocationRelativeTo(null);  // Center the window on screen
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);  // Prevent resizing

        // Set up the panel with GridBagLayout for better control over component positioning
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        panel.setBackground(new Color(40, 40, 40)); // Darker background for better contrast

        // Initialize the tabbed pane
        tabbedPane = new JTabbedPane();
        tabbedPane.setBackground(new Color(60, 60, 60)); // Set background color for tabbed pane
        tabbedPane.setFont(new Font("Roboto", Font.PLAIN, 16)); // Font style for tabbed pane

        // Menu tab
        menuPanel = new JPanel(new BorderLayout());
        menuTable = createTableForMenu();
        menuPanel.add(new JScrollPane(menuTable), BorderLayout.CENTER);
        JButton updateMenuButton = new JButton("Update Menu");
        updateMenuButton.setFont(new Font("Roboto", Font.PLAIN, 16)); // Font style for button
        updateMenuButton.setBackground(new Color(30, 30, 30)); // Dark button background
        updateMenuButton.setForeground(Color.WHITE); // White text for button
        updateMenuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reloadMenuData();
            }
        });
        menuPanel.add(updateMenuButton, BorderLayout.SOUTH);

        // Orders tab
        ordersPanel = new JPanel(new BorderLayout());
        ordersTable = createTableForOrders();
        ordersPanel.add(new JScrollPane(ordersTable), BorderLayout.CENTER);
        JButton updateOrdersButton = new JButton("Update Orders");
        updateOrdersButton.setFont(new Font("Roboto", Font.PLAIN, 16));
        updateOrdersButton.setBackground(new Color(30, 30, 30));
        updateOrdersButton.setForeground(Color.WHITE);
        updateOrdersButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reloadOrdersData();
            }
        });
        ordersPanel.add(updateOrdersButton, BorderLayout.SOUTH);

        // Reservations tab
        reservationsPanel = new JPanel(new BorderLayout());
        reservationsTable = createTableForReservations();
        reservationsPanel.add(new JScrollPane(reservationsTable), BorderLayout.CENTER);
        JButton updateReservationsButton = new JButton("Update Reservations");
        updateReservationsButton.setFont(new Font("Roboto", Font.PLAIN, 16));
        updateReservationsButton.setBackground(new Color(30, 30, 30));
        updateReservationsButton.setForeground(Color.WHITE);
        updateReservationsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reloadReservationsData();
            }
        });
        reservationsPanel.add(updateReservationsButton, BorderLayout.SOUTH);

        // Customer Details tab
        customerDetailsPanel = new JPanel(new BorderLayout());
        customerDetailsTable = createTableForCustomerDetails();
        customerDetailsPanel.add(new JScrollPane(customerDetailsTable), BorderLayout.CENTER);
        JButton updateCustomerDetailsButton = new JButton("Update Customer Details");
        updateCustomerDetailsButton.setFont(new Font("Roboto", Font.PLAIN, 16));
        updateCustomerDetailsButton.setBackground(new Color(30, 30, 30));
        updateCustomerDetailsButton.setForeground(Color.WHITE);
        updateCustomerDetailsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reloadCustomerDetailsData();
            }
        });
        customerDetailsPanel.add(updateCustomerDetailsButton, BorderLayout.SOUTH);

        // Add tabs
        tabbedPane.addTab("Menu", menuPanel);
        tabbedPane.addTab("Orders", ordersPanel);
        tabbedPane.addTab("Reservations", reservationsPanel);
        tabbedPane.addTab("Customer Details", customerDetailsPanel);

        // Place Order button
        placeOrderButton = new JButton("Place Order");
        placeOrderButton.setFont(new Font("Roboto", Font.PLAIN, 16));
        placeOrderButton.setBackground(new Color(30, 30, 30));
        placeOrderButton.setForeground(Color.WHITE);
        placeOrderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open new JFrame to place the order
                PlaceOrderFrame placeOrderFrame = new PlaceOrderFrame();
                placeOrderFrame.setVisible(true);
            }
        });

        // Make Reservation button
        makeReservationButton = new JButton("Make Reservation");
        makeReservationButton.setFont(new Font("Roboto", Font.PLAIN, 16));
        makeReservationButton.setBackground(new Color(30, 30, 30));
        makeReservationButton.setForeground(Color.WHITE);
        makeReservationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open the ReservationWindow
                ReservationWindow reservationWindow = new ReservationWindow();
                reservationWindow.setVisible(true);
            }
        });

        // Bottom panel with buttons
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(40, 40, 40)); // Bottom panel with dark background
        bottomPanel.add(placeOrderButton);
        bottomPanel.add(makeReservationButton);

        // Add components to the frame
        add(tabbedPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    // Method to create a JTable for Menu data
    private JTable createTableForMenu() {
        DefaultTableModel model = new DefaultTableModel(new Object[]{"ID", "Name", "Category", "Price"}, 0);
        try (Connection conn = DatabaseConnection.getConnection()) {
            if (conn != null) {
                String query = "SELECT * FROM menu_items";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    // Create MenuItem object using the factory pattern
                    com.restaurant.model.MenuItem menuItem = MenuItemFactory.createMenuItem(
                            rs.getString("name"),      // Name from the ResultSet
                            rs.getString("category"),  // Category from the ResultSet
                            rs.getDouble("price")      // Price from the ResultSet
                    );

                    // Set the ID manually after creation (from the ResultSet)
                    menuItem.setId(rs.getInt("id"));

                    // Add the created MenuItem object to the table model
                    model.addRow(new Object[]{
                            menuItem.getId(),
                            menuItem.getName(),
                            menuItem.getCategory(),
                            menuItem.getPrice()
                    });
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new JTable(model);
    }

    // Method to reload Menu data
    private void reloadMenuData() {
        DefaultTableModel model = (DefaultTableModel) menuTable.getModel();
        model.setRowCount(0); // Clear existing data
        try (Connection conn = DatabaseConnection.getConnection()) {
            if (conn != null) {
                String query = "SELECT * FROM menu_items";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    com.restaurant.model.MenuItem menuItem = MenuItemFactory.createMenuItem(
                            rs.getString("name"),
                            rs.getString("category"),
                            rs.getDouble("price")
                    );
                    menuItem.setId(rs.getInt("id"));
                    model.addRow(new Object[]{
                            menuItem.getId(),
                            menuItem.getName(),
                            menuItem.getCategory(),
                            menuItem.getPrice()
                    });
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to create a JTable for Orders data
    private JTable createTableForOrders() {
        DefaultTableModel model = new DefaultTableModel(new Object[]{"ID", "Customer ID", "Total Cost", "Order Status", "Payment Status"}, 0);
        try (Connection conn = DatabaseConnection.getConnection()) {
            if (conn != null) {
                String query = "SELECT * FROM orders";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    model.addRow(new Object[]{
                            rs.getInt("id"),
                            rs.getInt("customer_id"),
                            rs.getDouble("total_cost"),
                            rs.getString("order_status"),
                            rs.getString("payment_status")
                    });
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new JTable(model);
    }

    // Method to reload Orders data
    private void reloadOrdersData() {
        DefaultTableModel model = (DefaultTableModel) ordersTable.getModel();
        model.setRowCount(0);
        try (Connection conn = DatabaseConnection.getConnection()) {
            if (conn != null) {
                String query = "SELECT * FROM orders";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    model.addRow(new Object[]{
                            rs.getInt("id"),
                            rs.getInt("customer_id"),
                            rs.getDouble("total_cost"),
                            rs.getString("order_status"),
                            rs.getString("payment_status")
                    });
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to create a JTable for Reservations data
    private JTable createTableForReservations() {
        DefaultTableModel model = new DefaultTableModel(new Object[]{"ID", "Customer ID", "Table Type", "Reservation Time"}, 0);
        try (Connection conn = DatabaseConnection.getConnection()) {
            if (conn != null) {
                String query = "SELECT * FROM reservations";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    model.addRow(new Object[]{
                            rs.getInt("id"),
                            rs.getInt("customer_id"),
                            rs.getString("table_type"),
                            rs.getTimestamp("reservation_time")
                    });
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new JTable(model);
    }

    // Method to reload Reservations data
    private void reloadReservationsData() {
        DefaultTableModel model = (DefaultTableModel) reservationsTable.getModel();
        model.setRowCount(0);
        try (Connection conn = DatabaseConnection.getConnection()) {
            if (conn != null) {
                String query = "SELECT * FROM reservations";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    model.addRow(new Object[]{
                            rs.getInt("id"),
                            rs.getInt("customer_id"),
                            rs.getString("table_type"),
                            rs.getTimestamp("reservation_time")
                    });
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to create a JTable for Customer Details data
    private JTable createTableForCustomerDetails() {
        DefaultTableModel model = new DefaultTableModel(new Object[]{"ID", "Name", "Phone", "Email", "Address"}, 0);
        try (Connection conn = DatabaseConnection.getConnection()) {
            if (conn != null) {
                String query = "SELECT * FROM customers";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    model.addRow(new Object[]{
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("phone"),
                            rs.getString("email"),
                            rs.getString("address")
                    });
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new JTable(model);
    }

    // Method to reload Customer Details data
    private void reloadCustomerDetailsData() {
        DefaultTableModel model = (DefaultTableModel) customerDetailsTable.getModel();
        model.setRowCount(0);
        try (Connection conn = DatabaseConnection.getConnection()) {
            if (conn != null) {
                String query = "SELECT * FROM customers";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    model.addRow(new Object[]{
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("phone"),
                            rs.getString("email"),
                            rs.getString("address")
                    });
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to create a JTable for Order Items data
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
     public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

