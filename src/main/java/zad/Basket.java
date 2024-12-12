package zad;

import java.util.Arrays;
import java.util.Comparator;

public class Basket {
    private Product[] products;

    public Basket(Product[] products) {
        this.products = products;
    }

    public Product getMostExpensive() {
        return Arrays.stream(products)
                     .max(Comparator.comparing(Product::getPrice))
                     .orElse(null);
    }

    public Product getCheapest() {
        return Arrays.stream(products)
                     .min(Comparator.comparing(Product::getPrice))
                     .orElse(null);
    }

    public Product[] getTopNExpensive(int n) {
        return Arrays.stream(products)
                     .sorted(Comparator.comparing(Product::getPrice).reversed())
                     .limit(n)
                     .toArray(Product[]::new);
    }

    public Product[] getTopNCheapest(int n) {
        return Arrays.stream(products)
                     .sorted(Comparator.comparing(Product::getPrice))
                     .limit(n)
                     .toArray(Product[]::new);
    }

    public void sortProducts(Comparator<Product> comparator) {
        Arrays.sort(products, comparator);
    }

    public double calculateTotalPrice() {
        return Arrays.stream(products).mapToDouble(Product::getPrice).sum();
    }

    public double calculateTotalDiscountPrice() {
        return Arrays.stream(products).mapToDouble(Product::getDiscountPrice).sum();
    }

    public void applyPromotion(Promotion promotion) {
        promotion.apply(products);
    }

    public void printProducts() {
        Arrays.stream(products).forEach(System.out::println);
    }
}
