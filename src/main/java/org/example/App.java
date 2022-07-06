package org.example;

import org.example.enums.Qualification;
import org.example.model.Cashier;
import org.example.model.Customer;
import org.example.model.Manager;
import org.example.model.Store;

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
            store.restockStore(path);
        } catch (RuntimeException | IOException e) {
            e.printStackTrace();
        }
        store.printAllProduct();
        store.printProductByCategory("bars");

        try {
            store.restockStore(path2);
        } catch (RuntimeException | IOException e) {
            e.printStackTrace();
        }

        store.printAllProduct();
//        store.getProductArrayList().forEach(System.out::println);
        customer.addToCart("Carrot", 2000, store);
        customer2.addToCart("Carrot", 2000, store);
//        customer.addToCart("Carrot", 25, store);
//        customer.addToCart("banana", 10, store);
//        customer.addToCart("banana", 10, store);
//        customer.addToCart("whole wheat", 10, store);

        store.printAllProduct();

        try {
            cashier.printReceiptToTxtFile(customer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }try {
            cashier.printReceiptToTxtFile(customer2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        System.out.println("_________________________");
//        store.queryProductsByCategory("Bars").forEach(System.out::println);
        System.out.println("_________________________");
//        store.queryProductsByCategory("Snacks").forEach(System.out::println);
        System.out.println("_________________________");
//        store.queryProductsByCategory("Cookies").forEach(System.out::println);

//        store.printProductByCategory("cookies");

    }


}
