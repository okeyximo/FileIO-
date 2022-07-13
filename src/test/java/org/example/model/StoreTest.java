package org.example.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StoreTest {
    private Store store;

    @Before
    public void setUp() throws Exception {
        Manager manager = new Manager("Okey");
        store = new Store("ShopRite", manager);
        store.readFromFileToHashMap("assignmentData.csv");
    }

    @Test
    public void testRestockStore_TotalItems() {
        int actual = store.getProductMap().size();
        int expected = 9;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testProductCategory() {
        boolean actual = store.getCategoryFinder().containsKey("Bars");
        Assert.assertTrue(actual);

    }

    @Test
    public void testProductCategorySize() {
        int actual = store.getCategoryFinder().get("Bars").size();
        int expected = 3;
        Assert.assertEquals(expected, actual);

    }
}