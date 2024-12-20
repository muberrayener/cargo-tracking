package cargotrackingsys.Views;

import cargotrackingsys.MainScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CustomerView {

    private JFrame customerFrame;
    private JTextField customerIdField;
    private JTextField customerNameField;
    private JButton updateButton;
    private JButton backButton;
    private JTextArea displayArea;  // For displaying customer information

    public CustomerView() {
        // Initialize the customer view frame
        customerFrame = new JFrame("Customer Information");
        customerFrame.setSize(400, 300);
        customerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        customerFrame.setLayout(new BorderLayout());

        // Create a panel for input fields and buttons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));  // 3 rows, 2 columns

        // Add input fields and labels for Customer details
        panel.add(new JLabel("Customer ID:"));
        customerIdField = new JTextField(15);
        panel.add(customerIdField);

        panel.add(new JLabel("Customer Name:"));
        customerNameField = new JTextField(15);
        panel.add(customerNameField);

        // Update button
        updateButton = new JButton("Update Customer");
        panel.add(updateButton);

        // Back button to return to the main screen
        backButton = new JButton("Back to Main Screen");
        panel.add(backButton);

        // Display Area (for customer info)
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setPreferredSize(new Dimension(400, 150));
        customerFrame.add(panel, BorderLayout.NORTH);
        customerFrame.add(new JScrollPane(displayArea), BorderLayout.CENTER);

        // Make the customer view frame visible
        customerFrame.setVisible(true);
        addButtonFunctionality();
    }

    private void addButtonFunctionality() {
        // Action listener for the update button
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve shipment data from the text fields
                int customerId = getCustomerId();
                String name = getName();

                // Display the updated shipment information
                displayCustomerInfo(customerId, name);

                // Clear the input fields
                clearInputFields();
            }
        });

        // Action listener for the back button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Hide the CargoView
                customerFrame.setVisible(false);

                // Return to the MainScreen
                new MainScreen();  // This will display the MainScreen
            }
        });
    }

    // Method to display customer information in the view
    public void displayCustomerInfo(int customerId, String name) {
        displayArea.setText("Customer ID: " + customerId + "\nName: " + name);
    }

    // Method to clear the input fields after update
    public void clearInputFields() {
        customerIdField.setText("");
        customerNameField.setText("");
    }

    // Getters for customer ID and customer name
    public int getCustomerId() {
        return Integer.parseInt(customerIdField.getText()); // Assuming ID is a number
    }

    public String getName() {
        return customerNameField.getText();
    }

    // Get the update button
    public JButton getUpdateButton() {
        return updateButton;
    }

    // Get the back button
    public JButton getBackButton() {
        return backButton;
    }

    // Main method to test the CustomerView directly
    public static void main(String[] args) {
        new CustomerView();  // Open CustomerView on its own if needed
    }
}