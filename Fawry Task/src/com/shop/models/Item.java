package com.shop.models;
import java.time.LocalDate;

public class Item {
    private String itemName;
    private double itemPrice;
    private int stockQuantity;
    private boolean canExpire;
    private LocalDate expireDate;
    private boolean needsShipping;
    private double itemWeight;

    // Constructor for items that don't expire and don't need shipping
    public Item(String name, double price, int quantity) {
        this.itemName = name;
        this.itemPrice = price;
        this.stockQuantity = quantity;
        this.canExpire = false;
        this.needsShipping = false;
        this.itemWeight = 0.0;
    }

    // Constructor for items that don't expire but need shipping
    public Item(String name, double price, int quantity, double weight) {
        this.itemName = name;
        this.itemPrice = price;
        this.stockQuantity = quantity;
        this.canExpire = false;
        this.needsShipping = true;
        this.itemWeight = weight;
    }

    // Constructor for items that can expire and need shipping
    public Item(String name, double price, int quantity, double weight, LocalDate expireDate) {
        this.itemName = name;
        this.itemPrice = price;
        this.stockQuantity = quantity;
        this.canExpire = true;
        this.expireDate = expireDate;
        this.needsShipping = true;
        this.itemWeight = weight;
    }

    // Getters
    public String getItemName() { return itemName; }
    public double getItemPrice() { return itemPrice; }
    public int getStockQuantity() { return stockQuantity; }
    public boolean canExpire() { return canExpire; }
    public LocalDate getExpireDate() { return expireDate; }
    public boolean needsShipping() { return needsShipping; }
    public double getItemWeight() { return itemWeight; }

    // Check if item is expired
    public boolean isExpired() {
        if (!canExpire) return false;
        return LocalDate.now().isAfter(expireDate);
    }

    // Reduce stock when item is purchased
    public void reduceStock(int amount) {
        if (amount > stockQuantity) {
            throw new RuntimeException("Not enough stock available");
        }
        stockQuantity -= amount;
    }

    // Check if item has enough stock
    public boolean hasEnoughStock(int requestedQuantity) {
        return stockQuantity >= requestedQuantity;
    }
}
