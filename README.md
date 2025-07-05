# Link to C#-based Solution: [Link](https://github.com/Ahmed-Ramadan-Ahmed/Fawry-Challenge-E-Commerce-System)
# Fawry Quantum Internship Challenge 2025

A simple Java-based e-commerce system that supports product management, shopping cart functionality, and order processing with shipping capabilities.

## Project Structure

```
src/main/java/com/shop/
├── interfaces/
│   └── ShippableItem.java          # Interface for items that can be shipped
├── models/
│   ├── Item.java                   # Main product class
│   ├── ShippableItemAdapter.java   # Adapter for shipping interface
│   ├── CartItem.java               # Item in shopping cart
│   ├── Cart.java                   # Shopping cart functionality
│   ├── Customer.java               # Customer with balance and cart
│   └── OrderDetails.java           # Order information after checkout
├── services/
│   └── ShippingService.java        # Shipping cost calculation and processing
├── Shop.java                       # Main shop system
└── ShopApp.java                    # Application entry point with tests
```

### Prerequisites
- Java 8 or higher
- Any Java IDE (IntelliJ IDEA, Eclipse, VS Code)

## Item Creation Examples

### Non-Expiring Item (No Shipping)
```java
Item scratchCard = new Item("Mobile Scratch Card", 10.00, 50);
```

### Non-Expiring Item (With Shipping)
```java
Item tv = new Item("TV", 499.99, 5, 15.0); // weight: 15.0 kg
```

### Expiring Item (With Shipping)
```java
Item cheese = new Item("Cheese", 15.99, 10, 0.5, LocalDate.now().plusDays(7));
```

## Pricing & Shipping

### Shipping Cost Calculation
- **Rate**: $5.00 per kilogram
- **Digital Items**: No shipping cost
- **Physical Items**: Weight-based shipping cost

### Example Costs
- Cheese (0.5 kg): $2.50 shipping
- TV (15.0 kg): $75.00 shipping
- Mobile Scratch Card: $0.00 shipping (digital)

## Test Scenarios

The application includes comprehensive tests covering:

1. **Normal Purchase**: Mixed items with shipping
2. **Empty Cart**: Error handling for empty cart
3. **Insufficient Balance**: Customer doesn't have enough money
4. **Stock Validation**: Requesting more items than available
5. **Expired Items**: Attempting to buy expired products
6. **Heavy Shipping**: Large orders with significant shipping costs
7. **Digital Only**: Orders with no shipping requirements

## 📊 Sample Output Screens:
![Screen 1](https://github.com/Ahmed-Ramadan-Ahmed/Fawry-Challenge-E-Commerce-System-Java/blob/main/Screen%201.png?raw=true)
![Screen 2](https://github.com/Ahmed-Ramadan-Ahmed/Fawry-Challenge-E-Commerce-System-Java/blob/main/Screen%202.png?raw=true)
![Screen 2](https://github.com/Ahmed-Ramadan-Ahmed/Fawry-Challenge-E-Commerce-System-Java/blob/main/Screen%203.png?raw=true)

