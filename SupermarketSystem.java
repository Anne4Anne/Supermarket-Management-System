package com.mycompany.courseworkdatastructures;

import java.util.*; //package contains Map and HashMap commands

public class SupermarketSystem { //Class name used to contain and manage supermarket system

    private final Map<String, Product> products; // This is the Key: productId. 
    //stores all the products

    public SupermarketSystem() {
        this.products = new HashMap<>();
//constructor makes intilized product map to store inventory by creating new SupermarketSystem object 
    }

    // Uses Linear Search Algorithm to search products by ID
    public Product searchProduct(String productId) {
        for (String key : products.keySet()) { // linear search loop to check productID 
            if (key.equals(productId)) { //checks if key and product ID match
                return products.get(key);
            }
        }
        return null; // No matching products are found
    }

    // Create a new product to the system
    public boolean addProduct(Product product) {
        if (searchProduct(product.getProductId()) != null) { // checks if the same productID exists using linear search
            System.out.println("Product ID already in use!"); // Error message if prodictID exists 
            return false;
        }
        products.put(product.getProductId(), product);
        return true;
        // adds product to the HashMap if returns true

    }

    // Displays products as string for viewing
    public String displayProducts() {
        if (products.isEmpty()) { // Checks if there are any products in the system
            return "No products."; // if no products in system returns empty 
        }
        StringBuilder sb = new StringBuilder(); // creates StringBuilder object 
        for (Product p : products.values()) { //loops through all product objects in the map for display 
            sb.append(p.getProductId())
                    .append(" | ")
                    .append(p.getProductName())
                    .append(" | Qty: ")
                    .append(p.getQuantity())
                    .append("\n");
        } // formatted string is build for the products
        return sb.toString();
    }

    // Removes product from the system using the serach algorithm
    public boolean deleteProduct(String productId) {
        Product found = searchProduct(productId);
        if (found == null) {
            return false;
        }
        products.remove(productId);
        return true;
    }

    //  Adds product to the system using an activity
    public boolean updateStock(String productId, Activity activity) {
        Product p = searchProduct(productId);
        if (p == null) {
            System.out.println("Product not found!");
            return false;
        } // using the search algorithm finds and handles the product if it does not exist
        boolean ok = p.addActivity(activity);
        if (!ok) {
            System.out.println("Activity failed (e.g., removing more than available).");
        }
        return ok;
    } // Adds activity to the product and deals with possible failure

    // Takes the last 4 activities and formatts them to string activities using bubble sort 
    public String getSortedActivitiesInfo(String productId) {
        Product p = searchProduct(productId); // searches the system to find the product
        if (p == null) {
            return "Product not found!"; // If the product does not exist it returns not found
        }
        Activity[] arr = p.getLastActivities();
        if (arr.length == 0) {
            return "No activities.";
        } // Checks the activity array if empty returns no activity

        // Bubble Sort algorithm controls the quantity that pass through the array
        for (int i = 0; i < arr.length - 1; i++) { //Outter loop controls how many pass through 
            for (int j = 0; j < arr.length - 1 - i; j++) { // Inner loop compares elements next to each other 
                if (arr[j].getActivityQuantity() < arr[j + 1].getActivityQuantity()) { // Compares adjacent activities
                    Activity temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                } // Swaps activities that are adjacent
            }
        }

        StringBuilder sb = new StringBuilder();
        for (Activity a : arr) { // Formats the activities that are sorted into string
            sb.append(a.getActivityId())
                    .append(" | ")
                    .append(a.getActivityName())
                    .append(" | Qty: ")
                    .append(a.getActivityQuantity())
                    .append(" | Date: ")
                    .append(a.getActivityDate())
                    .append("\n");
        }
        return sb.toString();
    } // Creates readable output of all activity with their details

    // Using search algorithm checks existance in a readable format
    public boolean productExists(String productId) {
        return searchProduct(productId) != null;
    } // output using seach algorithm
}
