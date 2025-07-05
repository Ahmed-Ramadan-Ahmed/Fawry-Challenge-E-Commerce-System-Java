abstract public class Product {
    protected String name;
    protected double price;
    protected int quantity;
    public Product(String _name, double _price, int _quantity)
    {
        name = _name;
        price = _price;
        quantity = _quantity;
    }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public void reduceQuantity(int amount) {
        if (amount > quantity) {
            throw new IllegalArgumentException("Cannot reduce quantity by more than available");
        }
        this.quantity -= amount;
    }

    public abstract boolean isExpired();
    public abstract boolean requiresShipping();
}