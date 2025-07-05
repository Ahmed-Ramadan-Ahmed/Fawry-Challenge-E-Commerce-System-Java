package com.shop.models;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
public class OrderDetails {
    private List<CartItem> orderedItems;
    private double subtotalAmount;
    private double shippingCost;
    private double totalAmount;
    private LocalDate orderDate;

    public OrderDetails(List<CartItem> items, double subtotal, double shipping) {
        this.orderedItems = new ArrayList<>(items);
        this.subtotalAmount = subtotal;
        this.shippingCost = shipping;
        this.totalAmount = subtotal + shipping;
        this.orderDate = LocalDate.now();
    }

    public List<CartItem> getOrderedItems() { return new ArrayList<>(orderedItems); }
    public double getSubtotalAmount() { return subtotalAmount; }
    public double getShippingCost() { return shippingCost; }
    public double getTotalAmount() { return totalAmount; }
    public LocalDate getOrderDate() { return orderDate; }

}
