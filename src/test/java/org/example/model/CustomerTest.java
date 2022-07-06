package org.example.model;

import junit.framework.TestCase;

public class CustomerTest extends TestCase {
    private Customer customer;
    private Store store;

    public void setUp() throws Exception {
        super.setUp();
        customer = new Customer("Okey", 2500);
        store = new Store("ShopRite", new Manager("uche"));
        store.restockStore("assignmentData.csv");
    }

    public void testAddToCart_CartNotEmpty() {
        customer.addToCart("Banana", 25, store);
        int actual = customer.getCustomerCart().size();
        int expected = 1;
        assertEquals(actual, expected);
    }

    public void testRemoveFromCart() {
        customer.addToCart("Banana", 25, store);
        customer.removeFromCart("Banana");
        int actual = customer.getCustomerCart().size();
        int expected = 0;
        assertEquals(actual, expected);
    }

    public void testFindProduct_ProductInCart() {
        customer.addToCart("Banana", 25, store);
        customer.addToCart("Carrot", 25, store);
        int actual = 1;
        int expected = customer.findProduct("carrot");
        assertEquals(expected, actual);
    }


    public void testGetTotalPurchaseAmount() {
        customer.addToCart("Banana", 25, store);
        customer.addToCart("Carrot", 25, store);
        double actual = customer.getTotalPurchaseAmount();
        double expected = 101.0;
        assertEquals(actual, expected);
    }
}