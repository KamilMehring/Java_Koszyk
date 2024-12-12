package zad;

import java.util.Arrays;
import java.util.Comparator;

public class ThirdProductFree implements Promotion {
    @Override
    public void apply(Product[] products) {
        Arrays.sort(products, Comparator.comparing(Product::getPrice));
        if (products.length >= 3) {
            products[2].applyDiscount(products[2].getPrice());
        }
    }
}



