package com.tienda.aoporders.orders;

public class Order {

    private int id;
    private double total;
    private String customerName;

    public Order(int id, double total, String customerName) {
        this.id = id;
        this.total = total;
        this.customerName = customerName;
    }

    public int getId() {
        return id;
    }

    public double getTotal() {
        return total;
    }

    public String getCustomerName() {
        return customerName;
    }
}
