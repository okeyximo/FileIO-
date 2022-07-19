package org.example.model;

public class CustomerDTO implements Comparable<CustomerDTO> {
    private String customerName;
    private String productName;
    private int productQuantity;

    public CustomerDTO(String customerName, String productName, int productQuantity) {
        this.customerName = customerName;
        this.productName = productName;
        this.productQuantity = productQuantity;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    @Override
    public int compareTo(CustomerDTO o) {
        if (o.getProductQuantity() == this.getProductQuantity())
            return 0;
        return o.getProductQuantity() > this.getProductQuantity() ? 1 : -1;
    }
}
