package zad;

public class Coupon30Percent implements Promotion {
    private String productCode;

    public Coupon30Percent(String productCode) {
        this.productCode = productCode;
    }

    @Override
    public void apply(Product[] products) {
        for (Product product : products) {
            if (product.getCode().equals(productCode)) {
                product.applyDiscount(product.getPrice() * 0.3);
                break;
            }
        }
    }
}



