package org.example.model;

import org.example.model.Product;
import org.example.model.Store;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Customer {
    private double wallet;
    private String customerName;
    private ArrayList<Product> customerCart;

    /* CONSTRUCTOR AND GETTERS AND SETTERS */
    public Customer(String customerName, double wallet) {
        this.wallet = wallet;
        this.customerCart = new ArrayList<Product>();
        this.customerName = customerName;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public double getWallet() {
        return this.wallet;
    }

    public ArrayList<Product> getCustomerCart() {
        return this.customerCart;
    }

    public void setWallet(double wallet) {
        this.wallet = wallet;
    }

    /* CUSTOMER METHODS */

    /**
     * removeFromCart - Removes goods from customers cart.
     *
     * @param productName name of the product to be removed
     * @return true on success and false if goods is not in customer cart.
     */

    public boolean removeFromCart(String productName) {
        // checks if the name of the matches the customer cart, then delete it from the cart
        int indexToBeRemoved = findProduct(productName);
        if (indexToBeRemoved >= 0) {
            customerCart.remove(indexToBeRemoved);
            System.out.println("Removed " + productName + " from your cart");
            return true;
        }
        System.out.println(productName + "is not in your cart");
        return false;
    }

    /**
     * findProduct - A helper method to locate the index of a product in customer cart
     *
     * @param productName name of product
     * @return index of the product or -1 if the product does not exist
     */

    public int findProduct(String productName) {
        for (Product product : this.customerCart) {
            if (productName.compareToIgnoreCase(product.getProductName()) == 0) {
                return this.customerCart.indexOf(product);
            }
        }
        return (-1);
    }

    /**
     * queryCustomerCart - searches for item in the customer cart and prints if it in the cart or not
     *
     * @param name name of the item
     * @return true on success and false on failure
     */
    public boolean queryCustomerCart(String name) {
        //checks if an item is in the products list by name
        int foundProductIndex = findProduct(name);
        if (foundProductIndex >= 0) {
            Product foundProduct = customerCart.get(foundProductIndex);
            System.out.println("There are " + foundProduct.getQuantity() + " "
                    + foundProduct.getProductName() + " in your cart.");
            return true;
        } else {
            System.out.println("Product not available in your cart");
            return false;
        }
    }

    /**
     * getTotalPurchaseAmount - calculate the total price of the goods the customer purchases
     *
     * @return the total price
     */

    public double getTotalPurchaseAmount() {
        double totalPrice = 0;
        double amount = 0;
        for (Product product : this.customerCart) {
            amount = product.getQuantity() * product.getUnitPrice();
            totalPrice += amount;
        }
        return totalPrice;
    }

    public int getTotalQtyPurchased() {
        int totalQtyPurchased = 0;
        for (Product product : this.customerCart) {
            totalQtyPurchased += product.getQuantity();
        }
        return totalQtyPurchased;
    }

    /**
     * printCustomerCart - Prints the name, qty and amount of all the products inside the customer cart
     */
    public void printCustomerCart() {
        for (Product product : this.customerCart) {
            System.out.println(product.getProductName() + "  "
                    + product.getQuantity() + "  " + product.getUnitPrice());
        }
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerName='" + customerName + '\'' +
                '}';
    }
}
