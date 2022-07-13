package org.example.model;

import org.example.enums.Qualification;

public class Manager {
    private final String managerName;

    /* CONSTRUCTORS AND GETTER */
    public Manager(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerName() {
        return managerName;
    }

    /* MANAGER METHODS */

    /**
     * addProduct - adds product to the array of products, if exist update the quantity
     *
     * @param newProduct - new Product to be added.
     */



    public boolean addProduct(Product newProduct, Store store) {
        if (newProduct.getQuantity() <= 0) {
            System.out.println("There are no products to be added");
            return false;
        }
        if (store.getProductMap().containsKey(newProduct.getProductName())) {
            Product product = store.getProductMap().get(newProduct.getProductName());
            int oldProductQty = product.getQuantity();
            product.setQuantity(newProduct.getQuantity() + oldProductQty);
        } else {
            store.getProductMap().put(newProduct.getProductName(), newProduct);
            System.out.println("Successfully added " + newProduct.getProductName() + " to warehouse");
        }
        return true;
    }

    /**
     * HireCashier - checks if the maximum number of staffs needed is complete,
     * then hires cashier based on qualification and age.
     *
     * @param store   where cashier is meant to work.
     * @param cashier - the cashier to be hired.
     */

    public boolean hireCashier(Cashier cashier, Store store) {
        if (cashier.getStaffId() > 0){
            System.out.println("You are already a staff of this company");
            return true;
        }
        int maxStaffNeeded = store.getMaxStaffNeeded();
        if (store.getCashierStaffList().size() < maxStaffNeeded) {
            int cashierAge = cashier.getAge();
            Qualification cashierQualification = cashier.getQualification();
            if (cashierQualification.equals(Qualification.QUALIFIED)
                    || cashierAge < 18 || cashierAge >= 35) {
                cashier.setStaffId((store.getStaffId() + 1));
                store.setStaffId(store.getStaffId() + 1);
                System.out.println("Dear " + cashier.getName() + ", \n" +
                        "Congratulations, you are now a staff of " + store.getStoreName() + ".\n" +
                        "Here is your staffId " + cashier.getStaffId());
                store.getCashierStaffList().add(cashier);
                return true;
            } else {
                System.out.println("""
                        Thank you for taking the time to submit an application for our open cashier role.\s
                        Unfortunately, we are no longer considering you for this position.\s
                        Currently, we're looking for a candidate who's is within the age of 18 and 35 years and
                        has more experience.""");
                return false;
            }
        } else {
            System.out.println("We are not hiring!");
            return false;
        }
    }
}
