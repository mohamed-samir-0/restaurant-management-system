package com.restaurant.singleton;

public class PaymentSystem {
    private static volatile PaymentSystem instance;

    // Private constructor to prevent instantiation from outside
    private PaymentSystem() {}

    // Double-checked locking for thread-safe lazy initialization
    public static PaymentSystem getInstance() {
        if (instance == null) {
            synchronized (PaymentSystem.class) {
                if (instance == null) {
                    instance = new PaymentSystem();
                }
            }
        }
        return instance;
    }

    // Example method to process payment
    public void processPayment(double amount) {
        // Code to process the payment, such as interacting with a payment gateway
        System.out.println("Processing payment of " + amount);
    }
}