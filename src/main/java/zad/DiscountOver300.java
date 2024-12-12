package zad;

import java.util.Arrays;

public class DiscountOver300 implements Promotion {
    @Override
    public void apply(Product[] products) {
        double total = Arrays.stream(products).mapToDouble(Product::getPrice).sum();
        if (total > 300) {
            for (Product product : products) {
                product.applyDiscount(product.getPrice() * 0.05);
            }
        }
    }
}



