package org.example.model;

import junit.framework.TestCase;
import org.example.enums.Qualification;
import org.junit.Test;

public class ManagerTest extends TestCase {
    Manager manager;
    Store store;
    Cashier cashierGood;
    Cashier cashierBad;
    public void setUp() throws Exception {
        super.setUp();
        manager = new Manager("Okey");
        cashierGood = new Cashier("Chi", 19, Qualification.QUALIFIED);
        cashierBad = new Cashier("Yomi", 25, Qualification.NOT_QUALIFIED);
        store = new Store("Shoprite", manager);
    }

    @Test
    void hireCashierTest_TestGoodCashier() {
        boolean actual = manager.hireCashier(cashierGood, store);
        assertTrue( actual);
    }
    @Test
    void hireCashierTest_TestBadCashier(){
        boolean actual = manager.hireCashier(cashierBad, store);
        assertFalse(actual);
    }
}