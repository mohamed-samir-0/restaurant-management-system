package com.restaurant.model;

public class Order {
    private int id;
    private Customer customer;
    private double totalCost;
    private String orderStatus;
    private String paymentStatus;

    public Order(int id, Customer customer, double totalCost, String orderStatus, String paymentStatus) {
        this.id = id;
        this.customer = customer;
        this.totalCost = totalCost;
        this.orderStatus = orderStatus;
        this.paymentStatus = paymentStatus;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    public double getTotalCost() { return totalCost; }
    public void setTotalCost(double totalCost) { this.totalCost = totalCost; }

    public String getOrderStatus() { return orderStatus; }
    public void setOrderStatus(String orderStatus) { this.orderStatus = orderStatus; }

    public String getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }
}