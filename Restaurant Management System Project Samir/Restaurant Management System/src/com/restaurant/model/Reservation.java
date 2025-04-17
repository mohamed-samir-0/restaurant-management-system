package com.restaurant.model;

import java.util.Date;

public class Reservation {
    private int id;
    private Customer customer;
    private String tableType;
    private Date reservationTime;

    public Reservation(int id, Customer customer, String tableType, Date reservationTime) {
        this.id = id;
        this.customer = customer;
        this.tableType = tableType;
        this.reservationTime = reservationTime;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    public String getTableType() { return tableType; }
    public void setTableType(String tableType) { this.tableType = tableType; }

    public Date getReservationTime() { return reservationTime; }
    public void setReservationTime(Date reservationTime) { this.reservationTime = reservationTime; }
}