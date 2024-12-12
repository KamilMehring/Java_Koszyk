package zad;

import java.util.Arrays;

public class FreeMugOver200 implements Promotion {
    @Override
    public void apply(Product[] products) {
        double total = Arrays.stream(products).mapToDouble(Product::getPrice).sum();
        if (total > 200) {
            System.out.println("Congratulations! You get a free mug!");
        }
    }
}



