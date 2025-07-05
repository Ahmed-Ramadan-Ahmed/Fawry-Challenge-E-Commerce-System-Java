package com.shop.models;
import java.util.ArrayList;
import java.util.List;
public class Cart {
    private List<CartItem> cartItems;

    public Cart() {
        this.cartItems = new ArrayList<>();
    }

    public void addItem(Item item, int quantity) {

        if (quantity <= 0) {
            throw new RuntimeException("Quantity must be more than 0");
        }

        if (item.isExpired()) {
            throw new RuntimeException("Cannot add expired item: " + item.getItemName());
        }

        if (!item.hasEnoughStock(quantity)) {
            throw new RuntimeException("Not enough stock for item: " + item.getItemName());
        }

        for (CartItem cartItem : cartItems) {
            if (cartItem.getItem().getItemName().equals(item.getItemName())) {
                int newQuantity = cartItem.getQuantity() + quantity;
                if (!item.hasEnoughStock(newQuantity)) {
                    throw new RuntimeException("Total quantity exceeds available stock");
                }
                cartItem.setQuantity(newQuantity);
                return;
            }
        }

        cartItems.add(new CartItem(item, quantity));
    }

    public List<CartItem> getCartItems() {
        return new ArrayList<>(cartItems);
    }

    public boolean isEmpty() {
        return cartItems.isEmpty();
    }

    public double getSubtotalAmount() {
        double subtotal = 0.0;
        for (CartItem cartItem : cartItems) {
            subtotal += cartItem.getTotalPrice();
        }
        return subtotal;
    }

    public void clearCart() {
        cartItems.clear();
    }
}
