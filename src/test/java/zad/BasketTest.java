package zad;

import java.util.Comparator;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class BasketTest {
    
    @Test
    public void testGetMostExpensive() {
        Product[] products = {
            new Product("001", "Laptop", 1500),
            new Product("002", "Phone", 800),
            new Product("003", "Headphones", 200)
        };
        Basket basket = new Basket(products);
        assertEquals("Laptop", basket.getMostExpensive().getName());
    }

    @Test
    public void testGetCheapest() {
        Product[] products = {
            new Product("001", "Laptop", 1500),
            new Product("002", "Phone", 800),
            new Product("003", "Headphones", 200)
        };
        Basket basket = new Basket(products);
        assertEquals("Headphones", basket.getCheapest().getName());
    }

    @Test
    public void testGetTopNExpensive() {
        Product[] products = {
            new Product("001", "Laptop", 1500),
            new Product("002", "Phone", 800),
            new Product("003", "Headphones", 200),
            new Product("004", "Monitor", 400)
        };
        Basket basket = new Basket(products);
        Product[] top2 = basket.getTopNExpensive(2);
        assertEquals(2, top2.length);
        assertEquals("Laptop", top2[0].getName());
        assertEquals("Phone", top2[1].getName());
    }

    @Test
    public void testGetTopNCheapest() {
        Product[] products = {
            new Product("001", "Laptop", 1500),
            new Product("002", "Phone", 800),
            new Product("003", "Headphones", 200),
            new Product("004", "Monitor", 400)
        };
        Basket basket = new Basket(products);
        Product[] top2 = basket.getTopNCheapest(2);
        assertEquals(2, top2.length);
        assertEquals("Headphones", top2[0].getName());
        assertEquals("Monitor", top2[1].getName());
    }
    
    @Test
    public void testApplyDiscountOver300() {
        Product[] products = {
            new Product("001", "Laptop", 250),
            new Product("002", "Phone", 100),
            new Product("003", "Headphones", 50)
        };
        Basket basket = new Basket(products);
        basket.applyPromotion(new DiscountOver300());
        assertEquals(380.0, basket.calculateTotalDiscountPrice(), 0.01);
    }

    @Test
    public void testThirdProductFree() {
        Product[] products = {
            new Product("001", "Laptop", 1500),
            new Product("002", "Phone", 800),
            new Product("003", "Headphones", 200),
            new Product("004", "Monitor", 400)
        };
        Basket basket = new Basket(products);
        basket.applyPromotion(new ThirdProductFree());
        assertEquals(0.0, products[2].getDiscountPrice());
    }

    @Test
    public void testFreeMugOver200() {
        Product[] products = {
            new Product("001", "Laptop", 250),
            new Product("002", "Phone", 100)
        };
        Basket basket = new Basket(products);
        basket.applyPromotion(new FreeMugOver200());
        assertTrue(basket.calculateTotalPrice() > 200);
    }

    @Test
    public void testCoupon30Percent() {
        Product[] products = {
            new Product("001", "Laptop", 1000),
            new Product("002", "Phone", 500)
        };
        Basket basket = new Basket(products);
        basket.applyPromotion(new Coupon30Percent("002"));
        assertEquals(350.0, products[1].getDiscountPrice(), 0.01);
    }

    @Test
    public void testSortProductsByName() {
        Product[] products = {
            new Product("002", "Phone", 800),
            new Product("003", "Headphones", 200),
            new Product("001", "Laptop", 1500)
        };
        Basket basket = new Basket(products);
        basket.sortProducts(Comparator.comparing(Product::getName));
        assertEquals("Headphones", products[0].getName());
        assertEquals("Laptop", products[1].getName());
        assertEquals("Phone", products[2].getName());
    }

    @Test
    public void testSortProductsByPrice() {
        Product[] products = {
            new Product("002", "Phone", 800),
            new Product("003", "Headphones", 200),
            new Product("001", "Laptop", 1500)
        };
        Basket basket = new Basket(products);
        basket.sortProducts(Comparator.comparing(Product::getPrice).reversed());
        assertEquals("Laptop", products[0].getName());
        assertEquals("Phone", products[1].getName());
        assertEquals("Headphones", products[2].getName());
    }

    @Test
    public void testEmptyProductArray() {
        Product[] products = {};
        Basket basket = new Basket(products);
        assertNull(basket.getMostExpensive());
        assertNull(basket.getCheapest());
    }

    @Test
    public void testNegativePriceProduct() {
        Product[] products = {
            new Product("001", "Laptop", -1500),
            new Product("002", "Phone", 800)
        };
        Basket basket = new Basket(products);
        assertEquals(-1500, basket.getCheapest().getPrice());
    }

    @Test
    public void testDuplicateProducts() {
        Product[] products = {
            new Product("001", "Laptop", 1500),
            new Product("001", "Laptop", 1500),
            new Product("002", "Phone", 800)
        };
        Basket basket = new Basket(products);
        basket.sortProducts(Comparator.comparing(Product::getName));
        assertEquals(3, products.length);
        assertEquals("Laptop", products[0].getName());
    }


    @Test
    public void testApplyPromotionOnEmptyBasket() {
        Product[] products = {};
        Basket basket = new Basket(products);
        Promotion discountOver300 = new DiscountOver300();
        basket.applyPromotion(discountOver300);
        assertEquals(0.0, basket.calculateTotalDiscountPrice());
    }

    @Test
    public void testAddProductWithNullValues() {
        Product[] products = {
            new Product(null, null, 0)
        };
        Basket basket = new Basket(products);
        assertNull(products[0].getCode());
        assertNull(products[0].getName());
        assertEquals(0, products[0].getPrice());
    }

    @Test
    public void testDiscountGreaterThanPrice() {
        Product[] products = {
            new Product("001", "Laptop", 1500),
            new Product("002", "Phone", 800)
        };
        products[0].applyDiscount(2000);
        assertEquals(-500, products[0].getDiscountPrice());
    }

    @Test
    public void testApplyThirdProductFreeWithLessThanThreeProducts() {
        Product[] products = {
            new Product("001", "Laptop", 1500),
            new Product("002", "Phone", 800)
        };
        Basket basket = new Basket(products);
        Promotion thirdProductFree = new ThirdProductFree();
        basket.applyPromotion(thirdProductFree);
        assertEquals(1500, products[0].getDiscountPrice());
        assertEquals(800, products[1].getDiscountPrice());
    }
}
