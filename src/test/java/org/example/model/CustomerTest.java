package org.example.model;

import junit.framework.TestCase;

public class CustomerTest extends TestCase {
    private Customer customer;
    private Store store;

    public void setUp() throws Exception {
        super.setUp();
        customer = new Customer("Okey", 2500);
        store = new Store("ShopRite", new Manager("uche"));
        store.readFromFileToHashMap("assignmentData.csv");
    }

    public void testAddToCart_CartNotEmpty() {
        store.addToCustomerCart(customer, "Banana", 15);
        int actual = customer.getCustomerCart().size();
        int expected = 1;
        assertEquals(expected, actual);
    }

    public void testRemoveFromCart() {
        store.addToCustomerCart(customer, "Banana", 25);
        customer.removeFromCart("Banana");
        int actual = customer.getCustomerCart().size();
        int expected = 0;
        assertEquals(expected, actual);


    }

    public void testFindProduct_ProductInCart() {
        store.addToCustomerCart(customer, "Carrot", 25);
        int actual = 1;
        int expected = customer.findProduct("carrot");
        assertEquals(expected, actual);
    }


    public void testGetTotalPurchaseAmount() {
        store.addToCustomerCart(customer, "Banana", 25);
        store.addToCustomerCart(customer, "Carrot", 25);
        double actual = customer.getTotalPurchaseAmount();
        double expected = 101.0;
        assertEquals(expected, actual);
    }
}