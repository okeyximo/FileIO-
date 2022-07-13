package org.example.model;

import org.example.enums.Qualification;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ManagerTest {
    Manager manager;
    Store store;
    Cashier cashierGood;
    Cashier cashierBad;
    @Before
    public void setUp() throws Exception {
        manager = new Manager("Okey");
        cashierGood = new Cashier("Chi", 19, Qualification.QUALIFIED);
        cashierBad = new Cashier("Yomi", 25, Qualification.NOT_QUALIFIED);
        store = new Store("Shoprite", manager);
    }

    @Test
    public void hireCashierTest_TestGoodCashier() {
        boolean actual = manager.hireCashier(cashierGood, store);
        Assert.assertTrue(actual);
    }
    @Test
    public void hireCashierTest_TestBadCashier(){
        boolean actual = manager.hireCashier(cashierBad, store);
        Assert.assertFalse(actual);
    }
}