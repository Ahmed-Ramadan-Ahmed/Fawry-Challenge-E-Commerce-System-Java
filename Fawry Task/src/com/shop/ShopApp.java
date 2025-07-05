package com.shop;
import com.shop.models.*;
import java.time.LocalDate;

public class ShopApp {
    public static void main() {
        System.out.println("=== SIMPLE E-COMMERCE SHOP ===\n");

        // Create the shop
        Shop myShop = new Shop();

        // Add items to shop
        Item cheese = new Item("Cheese", 15.99, 10, 0.5, LocalDate.now().plusDays(7));
        Item biscuits = new Item("Biscuits", 3.99, 20, 0.3, LocalDate.now().plusDays(30));
        Item tv = new Item("TV", 499.99, 5, 15.0);
        Item mobile = new Item("Mobile", 299.99, 8, 2.0);
        Item scratchCard = new Item("Mobile Scratch Card", 10.00, 50);

        myShop.addItemToShop(cheese);
        myShop.addItemToShop(biscuits);
        myShop.addItemToShop(tv);
        myShop.addItemToShop(mobile);
        myShop.addItemToShop(scratchCard);

        // Test different scenarios
        runTests(myShop);

        System.out.println("=== ALL TESTS FINISHED ===");
    }

    private static void runTests(Shop shop) {
        // Test 1: Normal purchase
        System.out.println("Test 1: Normal purchase with shipping");
        try {
            Customer Ahmed = new Customer("Ahmed Ramadan", 1000.0);
            Ahmed.getShoppingCart().addItem(shop.findItem("Cheese"), 2);
            Ahmed.getShoppingCart().addItem(shop.findItem("Biscuits"), 3);
            Ahmed.getShoppingCart().addItem(shop.findItem("Mobile Scratch Card"), 1);

            OrderDetails order = shop.processCheckout(Ahmed);
            System.out.println("✓ Purchase successful!");

        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }

        // Test 2: Empty cart
        System.out.println("\nTest 2: Empty cart");
        try {
            Customer Mo = new Customer("Mo Salah", 500.0);
            shop.processCheckout(Mo);
        } catch (Exception e) {
            System.out.println("✓ Caught expected error: " + e.getMessage());
        }

        // Test 3: Not enough money
        System.out.println("\nTest 3: Not enough money");
        try {
            Customer poorCustomer = new Customer("Poor Customer", 50.0);
            poorCustomer.getShoppingCart().addItem(shop.findItem("TV"), 1);
            shop.processCheckout(poorCustomer);
        } catch (Exception e) {
            System.out.println("✓ Caught expected error: " + e.getMessage());
        }

        // Test 4: Too much quantity
        System.out.println("\nTest 4: Requesting too much quantity");
        try {
            Customer greedyCustomer = new Customer("Greedy Customer", 2000.0);
            greedyCustomer.getShoppingCart().addItem(shop.findItem("TV"), 100);
        } catch (Exception e) {
            System.out.println("✓ Caught expected error: " + e.getMessage());
        }

        // Test 5: Expired item
        System.out.println("\nTest 5: Expired item");
        try {
            Item expiredMilk = new Item("Expired Milk", 5.99, 10, 1.0, LocalDate.now().minusDays(1));
            shop.addItemToShop(expiredMilk);

            Customer customer = new Customer("Test Customer", 100.0);
            customer.getShoppingCart().addItem(shop.findItem("Expired Milk"), 1);
        } catch (Exception e) {
            System.out.println("✓ Caught expected error: " + e.getMessage());
        }

        // Test 6: Big order with heavy items
        System.out.println("\nTest 6: Big order with heavy shipping");
        try {
            Customer richCustomer = new Customer("Rich Customer", 2000.0);
            richCustomer.getShoppingCart().addItem(shop.findItem("TV"), 2);
            richCustomer.getShoppingCart().addItem(shop.findItem("Mobile"), 2);

            OrderDetails order = shop.processCheckout(richCustomer);
            System.out.println("✓ Big order successful!");

        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }

        // Test 7: Only digital items (no shipping)
        System.out.println("\nTest 7: Only digital items");
        try {
            Customer digitalCustomer = new Customer("Digital Customer", 100.0);
            digitalCustomer.getShoppingCart().addItem(shop.findItem("Mobile Scratch Card"), 8);

            OrderDetails order = shop.processCheckout(digitalCustomer);
            System.out.println("✓ Digital purchase successful!");

        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }
}
