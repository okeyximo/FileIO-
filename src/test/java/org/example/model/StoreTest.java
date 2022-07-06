package org.example.model;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class StoreTest {
    private Manager manager;
    private Store store;

    @Before
    public void setUp() throws Exception {
        manager = new Manager("Okey");
        store = new Store("ShopRite", manager);
        try {
            store.restockStore("assignmentData.csv");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testRestockStore_TotalItems() {
        int actual = store.getProductArrayList().size();
        int expected = 9;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testFindProduct_ProductFound() {
        int actual = store.findProduct("banana");
        int expected = 8;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testFindProduct_ProductNotFound() {
        int actual = store.findProduct("garri");
        int expected = -1;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testSearchForProduct_ProductFound() {
        int actual = store.searchForProduct("garri");
        int expected = -1;
        Assert.assertEquals(expected, actual);

    }


    @Test
    public void testQueryProductsByCategory() {
        int actual = store.queryProductsByCategory("bars").size();
        int expected = 3;
        Assert.assertEquals(expected, actual);
    }
}