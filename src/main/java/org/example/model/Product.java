package org.example.model;

public class Product {
    private String productName;
    private double unitPrice;
    private int quantity;
    private String category;


    /* CONSTRUCTOR, GETTERS AND SETTERS */
    public Product(String productName, double unitPrice, int quantity, String category) {
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.category = category;
    }

    public Product() {
    }

    public String getProductName() {
        return productName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "Product{" +
                "productName='" + productName + '\'' +
                ", unitPrice=" + unitPrice +
                ", quantity=" + quantity +
                ", category='" + category + '\'' +
                '}';
    }

}
