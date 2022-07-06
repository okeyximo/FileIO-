package org.example.model;

import org.example.model.Product;
import org.example.model.Store;

import java.util.ArrayList;

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
     * addToCart -  checks if the product is available in the store, then adds it to customers cart
     *
     * @param productName name of product
     * @param quantity    quantity to be purchased
     * @param store       name of store you are buying from
     * @return true if successfully and false if goods is not available in stock.
     */
    public boolean addToCart(String productName, int quantity, Store store) {
        if (store.findProduct(productName) >= 0) {
            int productIndex = store.findProduct(productName);
            Product productInStore = store.getProductArrayList().get(productIndex);
            if (productInStore.getQuantity() >= quantity) {
                Product addProduct = new Product(productInStore.getProductName(), productInStore.getUnitPrice(), quantity, productInStore.getCategory());
                this.customerCart.add(addProduct);
                productInStore.setQuantity(productInStore.getQuantity() - quantity);
                System.out.println("Successfully added " + productName + " to your cart");
                return true;
            }
            else {
                System.out.println("OUT OF STOCK");
                return false;
            }
        }


        else return false;
    }

    /**
     * removeFromCart - Removes goods from customers cart.
     *
     * @param productName name of the product to be removed
     * @return true on success and false if goods is not in customer cart.
     */

    public boolean removeFromCart(String productName) {
        // checks if the name of the matches the customer cart, then delete it from the cart
        if (findProduct(productName) >= 0) {
            int indexToBeRemove = findProduct(productName);
            customerCart.remove(indexToBeRemove);
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
     * findProduct - a method overload of the findProduct but this takes the product as a parameter
     *
     * @param product the product being searched for.
     * @return index of the product or -1 if the product does not exit.
     */

    private int findProduct(Product product) {
        return (this.customerCart.contains(product) ? this.customerCart.indexOf(product) : -1);
    }

    /**
     * queryCustomerCart - searches for item in the customer cart and prints if it in the cart or not
     *
     * @param name name of the item
     * @return true on success and false on failure
     */
    public boolean queryCustomerCart(String name) {
        //checks if an item is in the products list by name
        if (findProduct(name) >= 0) {
            int foundProductIndex = findProduct(name);
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

    /**
     * printCustomerCart - Prints the name, qty and amount of all the products inside the customer cart
     */
    public void printCustomerCart() {
        for (Product product : this.customerCart) {
            System.out.println(product.getProductName() + "  "
                    + product.getQuantity() + "  " + product.getUnitPrice());

        }
    }
}
