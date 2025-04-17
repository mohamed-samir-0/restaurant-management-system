package com.restaurant.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import com.restaurant.database.DatabaseConnection;
import com.restaurant.model.Customer;
import com.restaurant.model.Reservation;
import com.restaurant.factory.TableFactory;
import com.restaurant.model.Table;
import com.toedter.calendar.JDateChooser;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ReservationWindow extends javax.swing.JFrame {
    private JTable customerTable;
    private JComboBox<String> tableTypeComboBox;
    private JDateChooser reservationDateChooser;
    private Customer selectedCustomer;

    public ReservationWindow() {
        // Set title and window properties
        setTitle("Make Reservation");
        setSize(1200, 800);  // Adjusted window size for better proportions
        setLocationRelativeTo(null);  // Center the window on screen
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);  // Prevent resizing

        // Set up the panel with GridBagLayout for better control over component positioning
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        panel.setBackground(new Color(40, 40, 40)); // Darker background for better contrast

        // Create and add labels and table for selecting customer
        JLabel customerLabel = new JLabel("Select Customer:");
        String[] columnNames = {"ID", "Name", "Email", "Phone", "Address"}; // Add more columns if needed
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        customerTable = new JTable(model);
        customerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        customerTable.setFont(new Font("Roboto", Font.PLAIN, 16));
        customerTable.setBackground(new Color(240, 240, 240));
        customerTable.setForeground(Color.BLACK);

        // Fetch customers and populate the table
        fetchCustomers(model);

        // Add a selection listener to capture the selected row
        customerTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                int selectedRow = customerTable.getSelectedRow();
                if (selectedRow != -1) {
                    int customerId = (int) customerTable.getValueAt(selectedRow, 0);
                    String customerName = (String) customerTable.getValueAt(selectedRow, 1);
                    selectedCustomer = new Customer(customerId, customerName);
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(customerTable);
        scrollPane.setPreferredSize(new Dimension(800, 200));  // Adjust the size of the table

        // Add a button for adding a new customer
        JButton addCustomerButton = new JButton("Add New Customer");
        addCustomerButton.setFont(new Font("Roboto", Font.BOLD, 16));
        addCustomerButton.setBackground(new Color(70, 130, 180)); // Blue background for button
        addCustomerButton.setForeground(Color.WHITE);
        addCustomerButton.setFocusPainted(false);
        addCustomerButton.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180))); // Match the border to button color
        addCustomerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addNewCustomer();
            }
        });

        JLabel tableTypeLabel = new JLabel("Select Table Type:");
        tableTypeComboBox = new JComboBox<>();
        tableTypeComboBox.addItem("VIP");
        tableTypeComboBox.addItem("Regular");
        tableTypeComboBox.addItem("Outdoor");

        JLabel reservationDateLabel = new JLabel("Reservation Time:");
        reservationDateChooser = new JDateChooser();

        // Customize components (e.g., fonts, colors)
        customerLabel.setFont(new Font("Roboto", Font.PLAIN, 16));
        tableTypeLabel.setFont(new Font("Roboto", Font.PLAIN, 16));
        reservationDateLabel.setFont(new Font("Roboto", Font.PLAIN, 16));

        customerLabel.setForeground(Color.WHITE);
        tableTypeLabel.setForeground(Color.WHITE);
        reservationDateLabel.setForeground(Color.WHITE);

        tableTypeComboBox.setFont(new Font("Roboto", Font.PLAIN, 16));
        reservationDateChooser.setFont(new Font("Roboto", Font.PLAIN, 16));

        tableTypeComboBox.setBackground(new Color(240, 240, 240));
        reservationDateChooser.setBackground(new Color(240, 240, 240));

        // Buttons for saving reservation
        JButton saveButton = new JButton("Save Reservation");
        saveButton.setFont(new Font("Roboto", Font.BOLD, 16));
        saveButton.setBackground(new Color(70, 130, 180)); // Blue background for button
        saveButton.setForeground(Color.WHITE);
        saveButton.setFocusPainted(false);
        saveButton.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180))); // Match the border to button color
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveReservation();
            }
        });

        // Add components to the panel with GridBagLayout
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10); // Add padding around the components
        panel.add(customerLabel, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        panel.add(scrollPane, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(tableTypeLabel, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        panel.add(tableTypeComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(reservationDateLabel, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        panel.add(reservationDateChooser, gbc);

        // Add space between fields
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel(), gbc);  // Empty label to add space

        // Add Save button
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 3; // Make the button span all columns
        gbc.insets = new Insets(20, 0, 0, 0); // Add margin above the button
        panel.add(saveButton, gbc);

        // Add Add Customer button
        gbc.gridy = 5;
        panel.add(addCustomerButton, gbc);

        // Add the panel to the frame's content pane
        add(panel, BorderLayout.CENTER);
    }

    private void saveReservation() {
        if (selectedCustomer == null) {
            JOptionPane.showMessageDialog(this, "Please select a customer.");
            return;
        }

        String tableType = (String) tableTypeComboBox.getSelectedItem();
        Date reservationTime = new Date(reservationDateChooser.getDate().getTime());

        try (Connection conn = DatabaseConnection.getConnection()) {
            // Use TableFactory to create the table object based on selected table type
            Table table = TableFactory.createTable(tableType);

            // Create the reservation object
            Reservation reservation = new Reservation(0, selectedCustomer, tableType, reservationTime);

            // Insert the reservation into the database
            String insertQuery = "INSERT INTO reservations (customer_id, table_type, reservation_time) VALUES (?, ?, ?)";
            PreparedStatement insertStmt = conn.prepareStatement(insertQuery);
            insertStmt.setInt(1, reservation.getCustomer().getId());
            insertStmt.setString(2, reservation.getTableType());
            insertStmt.setTimestamp(3, new Timestamp(reservation.getReservationTime().getTime()));
            insertStmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Reservation saved successfully.");
            dispose(); // Close the window after saving
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving reservation.");
        }
    }

    // Method to add a new customer
    private void addNewCustomer() {
        // Show input fields for new customer
        JTextField nameField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField phoneField = new JTextField();
        JTextField addressField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(new JLabel("Customer Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Customer Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Phone:"));
        panel.add(phoneField);
        panel.add(new JLabel("Address:"));
        panel.add(addressField);
      
        
        int option = JOptionPane.showConfirmDialog(this, panel, "Enter New Customer Info", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        
        if (option == JOptionPane.OK_OPTION) {
            String name = nameField.getText();
            String email = emailField.getText();
            String phone = phoneField.getText();
            String address = addressField.getText();

            if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.");
                return;
            }

            try (Connection conn = DatabaseConnection.getConnection()) {
                String checkQuery = "SELECT * FROM customers WHERE email = ?";
                PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
                checkStmt.setString(1, email);
                ResultSet rs = checkStmt.executeQuery();

                if (rs.next()) {
                    JOptionPane.showMessageDialog(this, "Customer already exists.");
                } else {
                    String insertQuery = "INSERT INTO customers (name, email, phone, address) VALUES (?, ?, ?, ?)";
                    PreparedStatement insertStmt = conn.prepareStatement(insertQuery);
                    insertStmt.setString(1, name);
                    insertStmt.setString(2, email);
                    insertStmt.setString(3, phone);
                    insertStmt.setString(4, address);
                    insertStmt.executeUpdate();
                    JOptionPane.showMessageDialog(this, "Customer added successfully.");
                    fetchCustomers((DefaultTableModel) customerTable.getModel()); // Refresh the table
                }
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error adding customer.");
            }
        }
    }

    private void fetchCustomers(DefaultTableModel model) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM customers";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String address = rs.getString("address");

                model.addRow(new Object[]{id, name, email, phone, address});
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching customers.");
        }
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
                new ReservationWindow().setVisible(true);
            }
        });
    }
}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
