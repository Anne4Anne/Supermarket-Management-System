package com.mycompany.courseworkdatastructures;

/**
 *
 * @author owenb
 */
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class SupermarketGUI {

    private final SupermarketSystem system = new SupermarketSystem();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SupermarketGUI().createGUI());
    }
    // GUI initialization 

    public void createGUI() {
        JFrame frame = new JFrame("Supermarket Console");
        frame.setSize(400, 350);
        frame.setLayout(new GridLayout(6, 1, 10, 10)); // layout set for 6 buttons 
        // all buttons 
        JButton btnAdd = new JButton("Add Product");
        JButton btnDisplay = new JButton("Display Products");
        JButton btnDelete = new JButton("Delete Product");
        JButton btnActivity = new JButton("Activity`s (add/remove product)");
        JButton btnShowAct = new JButton("Show Sorted Activities");
        JButton btnExit = new JButton("Exit");
        // add buttons to frame
        frame.add(btnAdd);
        frame.add(btnDisplay);
        frame.add(btnDelete);
        frame.add(btnActivity);
        frame.add(btnShowAct);
        frame.add(btnExit);

        // Action Listeners
        btnAdd.addActionListener(e -> addProduct());// calls addProduct function
        btnDisplay.addActionListener(e -> displayProducts());// calls displayProducts function
        btnDelete.addActionListener(e -> deleteProduct());// calls deleteProduct function
        btnActivity.addActionListener(e -> addActivity());// calls addActivity function
        btnShowAct.addActionListener(e -> showActivities());// calls showActivities function
        btnExit.addActionListener(e -> System.exit(0));//close programme

        frame.setVisible(true);
    }

    // Add Product to the database
    private void addProduct() {
        JTextField id = new JTextField();//input fields for id, name and quantity
        JTextField name = new JTextField();
        JTextField qty = new JTextField();

        // dialog layout
        Object[] fields = {
            "Product ID:", id,
            "Product Name:", name,
            "Quantity:", qty
        };

        int option = JOptionPane.showConfirmDialog(null, fields,
                "Add Product", JOptionPane.OK_CANCEL_OPTION);

        //if user selects "ok", try....
        if (option == JOptionPane.OK_OPTION) {
            try {
                String pid = id.getText();
                String pname = name.getText();
                int quantity = Integer.parseInt(qty.getText());

                if (pid.isEmpty()) {//error message for no product ID
                    JOptionPane.showMessageDialog(null, "Enter a product ID.");
                    return;
                }
                if (system.productExists(pid)) {//error message if duplicate ID
                    JOptionPane.showMessageDialog(null, "Product ID is in use.");
                    return;
                }
                if (quantity < 0) {
                    throw new NumberFormatException();//prevents negative inputs as you can only add stock when adding a new product    
                }

                Product p = new Product(pid, pname, quantity, LocalDate.now());
                system.addProduct(p); //creates the product and adds it to the system

                JOptionPane.showMessageDialog(null, "Product added.");//confirmation prompt
            } catch (HeadlessException | NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid input.");//invalid prompt/errror catch 
            }
        }
    }

    // Display Products
    private void displayProducts() {
        String info = system.displayProducts();// gets product list
        JTextArea area = new JTextArea(info, 15, 30); //create a text area to display 
        area.setEditable(false);//make read-only 

        JOptionPane.showMessageDialog(null, new JScrollPane(area),
                "Products", JOptionPane.INFORMATION_MESSAGE);
    }

    // Delete Product
    private void deleteProduct() {
        String id = JOptionPane.showInputDialog("Enter Product ID:");//ask user for product ID
        if (id == null) { // if ID is empty or cancel is selected, return user
            return;
        }

        boolean removed = system.deleteProduct(id); //calls deleteProduct in SupermarketSystem class to attempt deletion     
        if (removed) {
            JOptionPane.showMessageDialog(null, "Product deleted.");//confirmation prompt
        } else {
            JOptionPane.showMessageDialog(null, "Product ID does not exist.");//error catch 
        }
    }

    // Add Activity (no Activity ID)
    private void addActivity() {
        JTextField pidField = new JTextField(); // Inpout ID
        String[] types = {"AddToStock", "RemoveFromStock"}; //Activity options
        JComboBox<String> typeBox = new JComboBox<>(types);//Dropdown for slection of activitys
        JTextField qty = new JTextField(); //Quantity input 
        // activity dialog layout
        Object[] form = {
            "Product ID:", pidField,
            "Activity Type:", typeBox,
            "Quantity:", qty
        };

        int option = JOptionPane.showConfirmDialog(null, form,
                "Add Activity", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            try {
                String pid = pidField.getText();
                String type = typeBox.getSelectedItem().toString();
                int q = Integer.parseInt(qty.getText());

                if (pid.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Product ID cannot be empty.");// Error catch
                    return;
                }
                if (q < 0) {
                    throw new NumberFormatException();// Prevents negative quantity input

                }

                Activity a = new Activity("", type, q); // leave ID blank
                boolean ok = system.updateStock(pid, a);

                if (ok) {
                    JOptionPane.showMessageDialog(null, "Activity added.");//confirmation prompt
                } else {
                    JOptionPane.showMessageDialog(null, "Activity failed.");
                }
            } catch (HeadlessException | NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid input.");//invalid prompt
            }
        }
    }

    // Show Last 4 Activities Sorted
    private void showActivities() {
        String id = JOptionPane.showInputDialog("Enter Product ID:");
        if (id == null) {//if cancel is selected, below message is shown 
            JOptionPane.showMessageDialog(null, "Invalid input.");//invalid prompt
            return;
        }

        String info = system.getSortedActivitiesInfo(id); // returns Sorted Activities
        JTextArea area = new JTextArea(info, 15, 30);//area to display Activities
        area.setEditable(false);

        JOptionPane.showMessageDialog(null, new JScrollPane(area),
                "Sorted Activities", JOptionPane.INFORMATION_MESSAGE);
    }
}
