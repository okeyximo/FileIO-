package org.example.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Store {
    private final String storeName;
    private final Manager manager;
    private final ArrayList<Cashier> cashierStaffList;
    private int maxStaffNeeded;
    private int staffId = 1000;

    private final Map<String, ArrayList<String>> categoryFinder;
    private final Map<String, Product> productMap;
    private Map<String, PriorityQueue<CustomerDTO>> productQueues;
    private Queue<Customer> customerQueue;
    private Map<String, Customer> customerFinderMap;



    /* CONSTRUCTOR, GETTERS AND SETTERS */
    public Store(String storeName, Manager manager) {

        this.storeName = storeName;
        this.manager = manager;
        this.cashierStaffList = new ArrayList<>();
        this.maxStaffNeeded = 3;
        this.categoryFinder = new HashMap<>();
        this.productMap = new HashMap<>();
        this.productQueues = new HashMap<>();
        this.customerQueue = new LinkedList<>();
        this.customerFinderMap = new HashMap<>();
    }

    public String getStoreName() {
        return storeName;
    }


    public ArrayList<Cashier> getCashierStaffList() {
        return cashierStaffList;
    }

    public Map<String, Product> getProductMap() {
        return productMap;
    }

    public Map<String, ArrayList<String>> getCategoryFinder() {
        return categoryFinder;
    }

    public int getMaxStaffNeeded() {
        return maxStaffNeeded;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public int getStaffId() {
        return staffId;
    }

    public Map<String, PriorityQueue<CustomerDTO>> getProductQueues() {
        return productQueues;
    }

    public Queue<Customer> getCustomerQueue() {
        return customerQueue;
    }
    public Map<String, Customer> getCustomerFinderMap() {
        return customerFinderMap;
    }

    /* STORE METHODS */

    /**
     * restockStore -  adds product in the store from a product CSV file
     *
     * @param path the path of the CSV file
     */


    public void readFromFileToHashMap(String path) throws IOException {
        try (FileReader fr = new FileReader(path); BufferedReader br = new BufferedReader(fr)) {
            String line = "";
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                //checks if the product exist in store wareHouse, if no, create newProduct and push to store wareHouse.
                if (this.productMap.containsKey(values[4])) {
                    Product product = this.productMap.get(values[4]);
                    product.setQuantity(product.getQuantity() + Integer.parseInt(values[5]));
                } else {
                    Product newProduct = new Product(values[4], Double.parseDouble(values[6]), Integer.parseInt(values[5]), values[3]);
                    this.productMap.put(values[4], newProduct);
                    if (!this.categoryFinder.containsKey(newProduct.getCategory())) {
                        ArrayList<String> productCategories = new ArrayList<>();
                        productCategories.add(newProduct.getProductName());
                        this.categoryFinder.put(newProduct.getCategory(), productCategories);
                    } else {
                        this.categoryFinder.get(values[3]).add(values[4]);
                    }
                }
            }
        }
    }

    /**
     * displayAllProduct - Prints the list of the product in the store.
     */

    public void displayAllProduct() {
        if (productMap.size() == 0) {
            System.out.println("There are no product available for purchase");
        } else {
            System.out.print("      ##############    " + storeName.toUpperCase() + "     ############## \n");
            System.out.println("----------------------------------------------------------------");
            System.out.printf("%s   %-15s%-15s%-10s%s\n", "S/N", "PRODUCT NAME", "QUANTITY", "PRICE", "CATEGORY");
            System.out.println("----------------------------------------------------------------");
            int count = 1;
            for (Product product : productMap.values()) {
                if (product.getQuantity() == 0) {
                    System.out.printf("%d   %-20s%-12s%-10s%s\n", count, product.getProductName(), "OUT OF STOCK", product.getUnitPrice(), product.getCategory());
                } else {
                    System.out.printf("%d   %-20s%-12s%-10.2f%s\n", count, product.getProductName(), product.getQuantity(), product.getUnitPrice(), product.getCategory());
                }
                count++;
            }
        }
    }

    /**
     * printProductByCategory - Prints a product of a particular category
     */
    public void printProductByCategory(String category) {
        int count = 1;
        if (categoryFinder.containsKey(category)) {
            System.out.print(category.toUpperCase() + " SECTION\n");
            System.out.printf("%s   %-15s%-15s%-10s\n", "S/N", "PRODUCT NAME", "QUANTITY", "PRICE");
            for (String productName : categoryFinder.get(category)) {
                System.out.printf("%d   %-20s%-12d%-10.2f\n", count, this.getProductMap().get(productName).getProductName(), this.getProductMap().get(productName).getQuantity(), this.getProductMap().get(productName).getUnitPrice());
                count++;
            }
        } else System.out.println("There is no product in " + category.toUpperCase() + " Section.");
    }

    /**
     * addToCart -  checks if the product is available in the store, then adds it to customers cart
     *
     * @param productName name of product
     * @param quantity    quantity to be purchased
     * @param customer    customer shopping
     * @return true if successfully and false if goods is not available in stock.
     */
    public boolean addToCustomerCart(Customer customer, String productName, int quantity) {
        if (this.productMap.containsKey(productName)) {
            Product productInStore = this.productMap.get(productName);
            if (productInStore.getQuantity() >= quantity) {
                Product addProduct = new Product(productInStore.getProductName(), productInStore.getUnitPrice(), quantity, productInStore.getCategory());
                customer.getCustomerCart().add(addProduct);
                productInStore.setQuantity(productInStore.getQuantity() - quantity);
                System.out.println("Successfully added " + productName + " to " + customer.getCustomerName() + "'s cart");
                return true;
            } else {
                System.out.println("OUT OF STOCK");
                return false;
            }
        } else return false;
    }


    public void addToCustomerQueue(Customer customer){
        this.customerQueue.add(customer);
        System.out.println(customer.getCustomerName() + " joined the Queue");
        this.customerFinderMap.put(customer.getCustomerName(), customer);
        for (Product product : customer.getCustomerCart()){
            CustomerDTO customerDTO = new CustomerDTO(customer.getCustomerName(), product.getProductName(), product.getQuantity());
            if (productQueues.containsKey(product.getProductName())){
                PriorityQueue<CustomerDTO> productQueue = productQueues.get(product.getProductName());
                productQueue.add(customerDTO);
            }
            else {
                PriorityQueue<CustomerDTO> productQueue = new PriorityQueue<>();
                productQueue.add(customerDTO);
                productQueues.put(product.getProductName(), productQueue);
            }
        }
    }
}





