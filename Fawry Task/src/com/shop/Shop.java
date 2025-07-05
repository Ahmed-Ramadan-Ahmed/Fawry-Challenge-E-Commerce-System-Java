package com.shop;
import com.shop.models.*;
import com.shop.services.ShippingService;
import com.shop.interfaces.IShippableItem;
import java.util.*;
public class Shop {
    private Map<String, Item> availableItems;
    private ShippingService shippingService;

    public Shop() {
        this.availableItems = new HashMap<>();
        this.shippingService = new ShippingService();
    }

    public void addItemToShop(Item item) {
        availableItems.put(item.getItemName(), item);
    }

    public Item findItem(String itemName) {
        return availableItems.get(itemName);
    }

    public OrderDetails processCheckout(Customer customer) {
        Cart cart = customer.getShoppingCart();

        // Check if cart is empty
        if (cart.isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        // Check all items in cart
        for (CartItem cartItem : cart.getCartItems()) {
            Item item = cartItem.getItem();

            // Check if item is expired
            if (item.isExpired()) {
                throw new RuntimeException("Item '" + item.getItemName() + "' is expired");
            }

            // Check if item has enough stock
            if (!item.hasEnoughStock(cartItem.getQuantity())) {
                throw new RuntimeException("Item '" + item.getItemName() + "' is out of stock");
            }
        }

        // Calculate costs
        double subtotal = cart.getSubtotalAmount();

        // Find items that need shipping
        List<IShippableItem> itemsToShip = new ArrayList<>();
        for (CartItem cartItem : cart.getCartItems()) {
            if (cartItem.getItem().needsShipping()) {
                // Add each item separately for quantity
                for (int i = 0; i < cartItem.getQuantity(); i++) {
                    itemsToShip.add(new ShippableItemAdapter(cartItem.getItem()));
                }
            }
        }

        double shippingCost = 0.0;
        if (!itemsToShip.isEmpty()) {
            shippingCost = shippingService.calculateShippingCost(itemsToShip);
        }

        double totalAmount = subtotal + shippingCost;

        // Check if customer can afford
        if (!customer.canAfford(totalAmount)) {
            throw new RuntimeException("Customer's balance is insufficient");
        }

        // Process payment
        customer.payAmount(totalAmount);

        // Update stock quantities
        for (CartItem cartItem : cart.getCartItems()) {
            cartItem.getItem().reduceStock(cartItem.getQuantity());
        }

        // Create order
        OrderDetails order = new OrderDetails(cart.getCartItems(), subtotal, shippingCost);

        // Print checkout details
        printCheckoutInfo(order, customer.getAccountBalance());

        // Process shipping if needed
        if (!itemsToShip.isEmpty()) {
            shippingService.processShipment(itemsToShip);
        }

        // Clear customer's cart
        cart.clearCart();

        return order;
    }

    private void printCheckoutInfo(OrderDetails order, double remainingBalance) {
        System.out.println("\n--- CHECKOUT DETAILS ---");
        System.out.println("Order subtotal: $" + String.format("%.2f", order.getSubtotalAmount()));
        System.out.println("Shipping fees: $" + String.format("%.2f", order.getShippingCost()));
        System.out.println("Paid amount: $" + String.format("%.2f", order.getTotalAmount()));
        System.out.println("Customer current balance: $" + String.format("%.2f", remainingBalance));
        System.out.println("------------------------\n");
    }
}
