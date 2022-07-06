package org.example.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Store {
    private final String storeName;

    private final ArrayList<Product> productArrayList;
    private final Manager manager;
    private final ArrayList<Cashier> cashierStaffList;
    private int maxStaffNeeded;
    private int staffId = 1000;
    private final Map<Integer, ArrayList<Integer>> categoryFinder;
    private Map<String, Integer> productFinder;

    /* CONSTRUCTOR, GETTERS AND SETTERS */
    public Store(String storeName, Manager manager) {
        productArrayList = new ArrayList<>();
        this.storeName = storeName;
        this.manager = manager;
        this.cashierStaffList = new ArrayList<>();
        this.maxStaffNeeded = 3;
        this.categoryFinder = new HashMap<>();
        this.productFinder = new HashMap<>();
    }

    public String getStoreName() {
        return storeName;
    }

    public ArrayList<Product> getProductArrayList() {
        return this.productArrayList;
    }

    public ArrayList<Cashier> getCashierStaffList() {
        return cashierStaffList;
    }

    public int getMaxStaffNeeded() {
        return maxStaffNeeded;
    }

    public void setMaxStaffNeeded(int maxStaffNeeded) {
        this.maxStaffNeeded = maxStaffNeeded;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public int getStaffId() {
        return staffId;
    }

    public Map<String, Integer> getProductFinder() {
        return productFinder;
    }

    public Map<Integer, ArrayList<Integer>> getCategoryFinder() {
        return categoryFinder;
    }

    /* STORE METHODS */

    /**
     * restockStore -  adds product in the store from a product CSV file
     *
     * @param path the path of the CSV file
     */

    public void restockStore(String path) throws RuntimeException, IOException {
        BufferedReader br = null;
        br = new BufferedReader(new FileReader(path));
        String line = "";
        br.readLine();

        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            //checks if the product exist in store wareHouse, if no, create newProduct and push to store wareHouse.
            if (findProduct(values[4]) < 0) {
                Product newProduct = new Product();
                newProduct.setProductName(values[4]);
                newProduct.setUnitPrice(Double.parseDouble(values[6]));
                newProduct.setQuantity(Integer.parseInt(values[5]));
                newProduct.setCategory(values[3]);
                this.productArrayList.add(newProduct);
                /*  create a hashmap of productName vs productIndex in wareHouse for easy search */
                int productIndex = this.productArrayList.indexOf(newProduct);
                productFinder.put(newProduct.getProductName(), productIndex);

            } else {
//                int productIndex = findProduct(values[4]);
                int productIndex = productFinder.get(values[4]);
                Product product = this.productArrayList.get(productIndex);
                product.setQuantity(product.getQuantity() + Integer.parseInt(values[5]));
            }
        }
    }

    /**
     * printAllProduct - Prints the list of the product in the store.
     */
    public void printAllProduct() {
        System.out.println("      ##############    " + storeName + "     ############## \n" +
                "S/N | PRODUCT NAME  |    PRICE     | QUANTITY IN STOCK"
        );
        if (productArrayList.size() == 0) {
            System.out.println("There are no product available for purchase");
        } else {
            int count = 1;
            for (Product product : productArrayList) {
                if (product.getQuantity() == 0) {
                    System.out.println(count + ".    " + product.getProductName() + "            "
                            + product.getUnitPrice() + "            " + " OUT OF STOCK");
                } else {
                    System.out.println(count + ".   " + product.getProductName() + "            "
                            + product.getUnitPrice() + "            " + product.getQuantity());
                    count++;
                }
            }
        }
    }

    /**
     * findProduct - Returns the index of the first marched product.
     *
     * @param productName name of the product being searched for
     * @return the index of the product or -1 if not found.
     */

    public int findProduct(String productName) {
        for (Product product : this.getProductArrayList()) {
            if (productName.compareToIgnoreCase(product.getProductName()) == 0) {
                return this.getProductArrayList().indexOf(product);
            }
        }
        return (-1);
    }

    /**
     * searchForProduct - An optimized version of the findProduct with O(1) complexity
     *
     * @param productName name of the product being searched for
     * @return the index of the product or -1 if not found
     */

    public int searchForProduct(String productName) {
        if (productFinder.isEmpty()) {
            return -1;
        }
        return this.productFinder.get(productName);
    }

    /**
     * queryProductsByCategory - groups the products into categories and return them as an array
     *
     * @param category category
     * @return Array of products in a particular category
     */
    public List<Product> queryProductsByCategory(String category) {
        return this.productArrayList.stream()
                .filter(product -> product.getCategory().compareToIgnoreCase(category) == 0)
                .collect(Collectors.toList());
    }

    /**
     * printProductByCategory - Prints a product of a particular category
     */
    public void printProductByCategory(String category) {
        int count = 1;
        if (queryProductsByCategory(category).size() > 0) {
            System.out.println(category.toUpperCase() + " SECTION\n" +
                    "S/N | ProductName  |   price  | quantity Remaining");
            for (Product product : queryProductsByCategory(category)) {
                System.out.println(count + ".    " + product.getProductName() + "  " + product.getUnitPrice() + " " + product.getQuantity());
                count++;
            }
        } else System.out.println("There is no product in " + category.toUpperCase() + " Section.");
    }


}


