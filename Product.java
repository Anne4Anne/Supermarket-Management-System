package com.mycompany.courseworkdatastructures;

/**
 *
 * @author owenb
 */
import java.time.LocalDate;

public class Product {

    private String productId;
    private String productName;
    private LocalDate entryDate;
    private int quantity;

    private final StackActivity activities; // Custom stack for last 4 activities

    public Product(String productId, String productName, int quantity, LocalDate entryDate) {
        this.productId = productId;
        this.productName = productName;
        this.entryDate = entryDate;
        this.quantity = quantity;
        this.activities = new StackActivity();
    }

    // Getters & setters
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Add activity and update quantity
    public boolean addActivity(Activity activity) {
        if (activity.getActivityName().equalsIgnoreCase("RemoveFromStock")) {
            if (activity.getActivityQuantity() > quantity) {
                return false; // Cannot remove more than available
            }
            quantity -= activity.getActivityQuantity();
        } else if (activity.getActivityName().equalsIgnoreCase("AddToStock")) {
            quantity += activity.getActivityQuantity();
        } else {
            return false; // Invalid activity type
        }

        activities.push(activity);
        return true;
    }

    // Get last activities as array
    public Activity[] getLastActivities() {
        return activities.toArray();
    }
}
