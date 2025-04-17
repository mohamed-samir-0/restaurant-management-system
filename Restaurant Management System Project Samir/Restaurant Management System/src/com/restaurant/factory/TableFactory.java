package com.restaurant.factory;

import com.restaurant.model.*;

public class TableFactory {
    public static Table createTable(String tableType) {
        switch (tableType) {
            case "VIP":
                return new VIPTable();
            case "Regular":
                return new RegularTable();
            case "Outdoor":
                return new OutdoorTable();
            default:
                throw new IllegalArgumentException("Invalid table type: " + tableType);
        }
    }
}