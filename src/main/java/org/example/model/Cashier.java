package org.example.model;

import org.example.enums.Qualification;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Cashier {
    private String name;
    private Qualification qualification;
    private int age;
    private int staffId;

    /* CONSTRUCTOR, GETTER AND SETTER */
    public Cashier(String name, int age, Qualification qualification) {
        this.name = name;
        this.age = age;
        this.qualification = qualification;
        this.staffId = 0;
    }

    public Cashier() {

    }

    public String getName() {
        return name;
    }

    public Qualification getQualification() {
        return qualification;
    }

    public int getAge() {
        return age;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }
    /* CASHIER METHOD */

    /**
     * printReceipt - Prints the customers receipt
     * @param customer customer whose receipt is to be printed
     */
    public void printReceipt(Customer customer) {
        // loops through the customerCart and prints the name of each item with
        // their corresponding price, then the total price,
        // then the name of the customer
        String customerName = customer.getCustomerName();
        ArrayList<Product> customerCart = customer.getCustomerCart();
        System.out.println("#### RECEIPT ####\n" +
                "s/n |   Product Name   | Qty | Amount  ");
        int count = 1;
        double totalCostPrice = 0;
//        int totalQty = 0;

        for (Product product : customerCart) {
            double amount = product.getQuantity() * product.getUnitPrice();
            System.out.println(count + ". " + "     " + product.getProductName() + "             "
                    + product.getQuantity() + "     " + amount);
            totalCostPrice += amount;
            count++;
        }
        System.out.println("Total Amount" + " --------> " + totalCostPrice);
        System.out.println("customer - " + customer.getCustomerName() + "\n" + "Thanks for your patronage\n");
    }

    /**
     * printReceiptToTxtFile - prints the receipt in a txt file for the customer
     * @param customer -  the customer
     */
    public void  printReceiptToTxtFile(Customer customer) throws IOException {
        StringBuilder heading, body;
        heading = new StringBuilder();
        body = new StringBuilder();
        heading.append("#### RECEIPT ####\n" +
                "s/n |   Product Name   | Qty | Amount  ");
       int count = 1;
       double totalPrice = 0;
       int totalQty = 0;

        for (Product product : customer.getCustomerCart()){
            double amount = product.getQuantity() * product.getUnitPrice();
            body.append(count + ". " + "     " + product.getProductName() + "             "
                    + product.getQuantity() + "     " + amount + "\n");
            totalPrice += amount;
            count++;
        }
        body.append("Total amount ---------> " + totalPrice + "\n");
        body.append("Customer - " + customer.getCustomerName() + "\n" + "Thanks for your Patronage");

        BufferedWriter writer = new BufferedWriter(new FileWriter((customer.getCustomerName() + ".txt")));
        writer.write(heading.toString());
        writer.write("\n");
        writer.write(body.toString());
        writer.close();

    }
}
