package org.example.model;

public class CustomerDTO {
    private final String customerName;
    private final String productName;
    private int productQuantity;

    public CustomerDTO(String customerName, String productName, int productQuantity) {
        this.customerName = customerName;
        this.productName = productName;
        this.productQuantity = productQuantity;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getProductName() {
        return productName;
    }


    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }
}
