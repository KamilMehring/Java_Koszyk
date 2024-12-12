package zad;

public class Product {
    private String code;
    private String name;
    private double price;
    private double discountPrice;

    public Product(String code, String name, double price) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.discountPrice = price;
    }

    public String getCode() { return code; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public double getDiscountPrice() { return discountPrice; }

    public void applyDiscount(double discount) {
        this.discountPrice = this.price - discount;
    }

    @Override
    public String toString() {
        return String.format("Product[code=%s, name=%s, price=%.2f, discountPrice=%.2f]", code, name, price, discountPrice);
    }
}




