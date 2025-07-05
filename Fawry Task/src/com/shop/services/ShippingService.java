package com.shop.services;
import com.shop.interfaces.IShippableItem;
import java.util.List;

public class ShippingService {
    private static final double SHIPPING_COST_PER_KG = 5.0;

    public double calculateShippingCost(List<IShippableItem> itemsToShip) {
        double totalWeight = 0.0;
        for (IShippableItem item : itemsToShip) {
            totalWeight += item.getWeight();
        }
        return totalWeight * SHIPPING_COST_PER_KG;
    }

    public void processShipment(List<IShippableItem> itemsToShip) {
        System.out.println("Processing shipment...");
        System.out.println("Items to ship:");

        double totalWeight = 0.0;
        for (IShippableItem item : itemsToShip) {
            System.out.println("- " + item.getName() + " (Weight: " + item.getWeight() + " kg)");
            totalWeight += item.getWeight();
        }

        System.out.println("Total weight: " + totalWeight + " kg");
        System.out.println("Shipping cost: $" + calculateShippingCost(itemsToShip));
        System.out.println("Shipment processed successfully!");
    }
}
