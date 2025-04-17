package com.restaurant.gui;

import com.restaurant.database.DatabaseConnection;
import com.restaurant.model.MenuItem;
import com.restaurant.model.Order;
import com.restaurant.model.Customer;
import com.restaurant.singleton.OrderManager;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class PlaceOrderFrame extends JFrame {
    private JTextField customerNameField;
    private JTextField customerPhoneField;
    private JTextField customerEmailField;
    private JTextField customerAddressField;
    private JTable menuTable;
    private JTextArea orderDetailsTextArea;
    private JLabel totalLabel;
    private JButton addItemButton, placeOrderButton;
    private double totalCost = 0.0;

    public PlaceOrderFrame() {
        setTitle("Place Order");
        setSize(1200, 800);  // Adjusted window size for better proportions
        setLocationRelativeTo(null);  // Center the window on screen
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);  // Prevent resizing

        // Set up the panel with GridBagLayout for better control over component positioning
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        panel.setBackground(new Color(40, 40, 40)); // Darker background for better contrast

        // Customer input fields panel
        JLabel customerNameLabel = new JLabel("Customer Name:");
        customerNameField = new JTextField(20);
        JLabel customerPhoneLabel = new JLabel("Customer Phone:");
        customerPhoneField = new JTextField(20);
        JLabel customerEmailLabel = new JLabel("Customer Email:");
        customerEmailField = new JTextField(20);
        JLabel customerAddressLabel = new JLabel("Customer Address:");
        customerAddressField = new JTextField(20);

        // Customize components (e.g., fonts, colors)
        customerNameLabel.setFont(new Font("Roboto", Font.PLAIN, 16)); 
        customerPhoneLabel.setFont(new Font("Roboto", Font.PLAIN, 16));
        customerEmailLabel.setFont(new Font("Roboto", Font.PLAIN, 16)); 
        customerAddressLabel.setFont(new Font("Roboto", Font.PLAIN, 16)); 

        customerNameLabel.setForeground(Color.WHITE);
        customerPhoneLabel.setForeground(Color.WHITE);
        customerEmailLabel.setForeground(Color.WHITE);
        customerAddressLabel.setForeground(Color.WHITE);

        customerNameField.setFont(new Font("Roboto", Font.PLAIN, 16));
        customerPhoneField.setFont(new Font("Roboto", Font.PLAIN, 16));
        customerEmailField.setFont(new Font("Roboto", Font.PLAIN, 16));
        customerAddressField.setFont(new Font("Roboto", Font.PLAIN, 16));

        customerNameField.setBackground(new Color(240, 240, 240));
        customerPhoneField.setBackground(new Color(240, 240, 240));
        customerEmailField.setBackground(new Color(240, 240, 240));
        customerAddressField.setBackground(new Color(240, 240, 240));

        customerNameField.setForeground(Color.BLACK);
        customerPhoneField.setForeground(Color.BLACK);
        customerEmailField.setForeground(Color.BLACK);
        customerAddressField.setForeground(Color.BLACK);

        // Add components to the panel with positioning
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(customerNameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        panel.add(customerNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(customerPhoneLabel, gbc);

        gbc.gridx = 1;
        panel.add(customerPhoneField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(customerEmailLabel, gbc);

        gbc.gridx = 1;
        panel.add(customerEmailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(customerAddressLabel, gbc);

        gbc.gridx = 1;
        panel.add(customerAddressField, gbc);

        // Menu Table
        menuTable = new JTable();
        loadMenuData();
        JScrollPane menuScrollPane = new JScrollPane(menuTable);
        menuScrollPane.setPreferredSize(new Dimension(500, 200));
        
        // Order details text area
        orderDetailsTextArea = new JTextArea(10, 30);
        orderDetailsTextArea.setEditable(false);
        JScrollPane orderScrollPane = new JScrollPane(orderDetailsTextArea);

        // Total cost label
        totalLabel = new JLabel("Total Cost: $0.00");
        totalLabel.setFont(new Font("Roboto", Font.BOLD, 16));
        totalLabel.setForeground(Color.WHITE);

        // Add Item button
        addItemButton = new JButton("Add Item");
        addItemButton.setFont(new Font("Roboto", Font.BOLD, 16));
        addItemButton.setBackground(new Color(70, 130, 180)); // Blue background for button
        addItemButton.setForeground(Color.WHITE);
        addItemButton.setFocusPainted(false);
        addItemButton.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180))); // Match the border to button color
        addItemButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = menuTable.getSelectedRow();
                if (selectedRow >= 0) {
                    String itemName = (String) menuTable.getValueAt(selectedRow, 1);
                    double itemPrice = (double) menuTable.getValueAt(selectedRow, 3);
                    orderDetailsTextArea.append(itemName + " - $" + itemPrice + "\n");
                    totalCost += itemPrice;
                    totalLabel.setText("Total Cost: $" + totalCost);
                }
            }
        });

        // Place Order button
        placeOrderButton = new JButton("Place Order");
        placeOrderButton.setFont(new Font("Roboto", Font.BOLD, 16));
        placeOrderButton.setBackground(new Color(70, 130, 180)); // Blue background for button
        placeOrderButton.setForeground(Color.WHITE);
        placeOrderButton.setFocusPainted(false);
        placeOrderButton.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180))); // Match the border to button color
        placeOrderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String customerName = customerNameField.getText();
                String customerPhone = customerPhoneField.getText();
                String customerEmail = customerEmailField.getText();
                String customerAddress = customerAddressField.getText();

                if (customerName.isEmpty() || customerPhone.isEmpty() || customerEmail.isEmpty() || customerAddress.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all customer details.");
                    return;
                }

                // Create Customer object
                Customer customer = new Customer(0, customerName, customerPhone, customerEmail, customerAddress);

                // Create Order object
                Order order = new Order(0, customer, totalCost, "Pending", "Unpaid");

                // Use the Singleton OrderManager to handle the order
                OrderManager orderManager = OrderManager.getInstance();
                orderManager.placeOrder(order);

                JOptionPane.showMessageDialog(null, "Order placed successfully!");
                dispose(); // Close the frame
            }
        });

        // Panel to hold buttons and total
        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.setBackground(new Color(40, 40, 40));  // Darker background for bottom panel
        bottomPanel.add(addItemButton);
        bottomPanel.add(placeOrderButton);
        bottomPanel.add(totalLabel);

        // Add components to the frame
        add(panel, BorderLayout.NORTH);
        add(menuScrollPane, BorderLayout.CENTER);
        add(orderScrollPane, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    // Load menu data into the JTable using the Factory Pattern
    private void loadMenuData() {
        DefaultTableModel model = new DefaultTableModel(new Object[]{"ID", "Name", "Category", "Price"}, 0);

        try (Connection conn = DatabaseConnection.getConnection()) {  // Using the connection from DatabaseConnection class
            if (conn != null) {
                String query = "SELECT * FROM menu_items";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    // Create MenuItem object using the factory pattern
                    MenuItem menuItem = new MenuItem(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("category"),
                            rs.getDouble("price")
                    );

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

        menuTable.setModel(model);  // Set the model for the table
    }


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
                PlaceOrderFrame frame = new PlaceOrderFrame();
                frame.setVisible(true);
            }
        });
    }
}


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables



