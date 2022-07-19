package org.example;

import org.example.enums.Qualification;
import org.example.model.*;

import java.io.IOException;


public class App {
    public static void main(String[] args) {
        Customer customer = new Customer("customer1", 250);
        Customer customer2 = new Customer("customer2", 250);
        Customer customer3 = new Customer("customer3", 250);
        Customer customer4 = new Customer("customer4", 250);
        Cashier cashier = new Cashier("uche", 25, Qualification.QUALIFIED);
        Manager manager = new Manager("Okey");
        Store store = new Store("shoprite", manager);
        manager.hireCashier(cashier, store);
        String path = "assignmentData.csv";
        String path2 = "assignmentData.csv";

        try {
            store.readFromFileToHashMap(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        store.displayAllProduct();

        store.printProductByCategory("Bars");
        store.printProductByCategory("Snacks");
        store.printProductByCategory("Cookies");
        store.printProductByCategory("Snacks");
        store.printProductByCategory("Soap");

        try {
            store.readFromFileToHashMap(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//        store.displayAllProduct();


        store.addToCustomerCart(customer, "Carrot", 10);
        store.addToCustomerCart(customer2, "Carrot", 20);
        store.addToCustomerCart(customer3, "Carrot", 30);
        store.addToCustomerCart(customer4, "Carrot", 40);
        store.addToCustomerCart(customer2, "Banana", 10);
        store.addToCustomerCart(customer4, "Banana", 10);
        store.addToCustomerCart(customer3, "Whole Wheat", 100);
        store.addToCustomerCart(customer4, "Whole Wheat", 200);

        store.addToCustomerQueue(customer);
        store.addToCustomerQueue(customer3);
        store.addToCustomerQueue(customer2);
        store.addToCustomerQueue(customer4);

//        cashier.sellByPriority(store, "Carrot");
//        cashier.sellByPriority(store, "Banana");
////        cashier.sellByPriority(store);
//        cashier.sellByFIFO(store);
        cashier.sell(store);


//






//        store.displayAllProduct();
//        try {
//            cashier.printReceiptToTxtFile(customer);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        try {
//            cashier.printReceiptToTxtFile(customer2);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println("_________________________");
    }
}
