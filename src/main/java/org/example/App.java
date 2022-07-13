package org.example;

import org.example.enums.Qualification;
import org.example.model.*;

import java.io.IOException;


public class App {
    public static void main(String[] args) {
        Customer customer = new Customer("Okey", 250);
        Customer customer2 = new Customer("Uche", 250);
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

        store.displayAllProduct();


        store.addToCustomerCart(customer, "Carrot", 2000);
        store.addToCustomerCart(customer, "Banana", 10);
        store.addToCustomerCart(customer, "Whole Wheat", 1000);

        store.displayAllProduct();
        try {
            cashier.printReceiptToTxtFile(customer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            cashier.printReceiptToTxtFile(customer2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("_________________________");
    }
}
