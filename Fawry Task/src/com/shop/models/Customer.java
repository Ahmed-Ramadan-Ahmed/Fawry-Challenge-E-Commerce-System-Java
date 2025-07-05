package com.shop.models;

public class Customer {
    private String customerName;
    private double accountBalance;
    private Cart shoppingCart;

    public Customer(String name, double balance) {
        this.customerName = name;
        this.accountBalance = balance;
        this.shoppingCart = new Cart();
    }

    public String getCustomerName() { return customerName; }
    public double getAccountBalance() { return accountBalance; }
    public Cart getShoppingCart() { return shoppingCart; }

    public void payAmount(double amount) {
        if (amount > accountBalance) {
            throw new RuntimeException("Not enough money in account");
        }
        accountBalance -= amount;
    }

    public boolean canAfford(double amount) {
        return accountBalance >= amount;
    }
}
