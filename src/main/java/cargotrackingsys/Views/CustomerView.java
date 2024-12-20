package cargotrackingsys.Views;

import cargotrackingsys.MainScreen;
import cargotrackingsys.Models.Customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class CustomerView {
    private JFrame frame;
    private JTextField customerIdField;
    private JTextField nameField;
    private JTextArea customerListArea;  // To display the list of customers
    private JButton addCustomerButton;
    private JButton backButton;
    private LinkedList<Customer> customerList;  // Linked list to hold customer objects

    public CustomerView() {
        // Initialize customer list
        customerList = new LinkedList<>();

        // Setup JFrame for the CustomerView
        frame = new JFrame("Customer Management");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Input Panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));  // 3 rows, 2 columns

        inputPanel.add(new JLabel("Customer ID:"));
        customerIdField = new JTextField();
        inputPanel.add(customerIdField);

        inputPanel.add(new JLabel("Full Name (First Last):"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        addCustomerButton = new JButton("Add Customer");
        inputPanel.add(addCustomerButton);

        backButton = new JButton("Back to Main Screen");
        inputPanel.add(backButton);

        frame.add(inputPanel, BorderLayout.NORTH);

        // Customer List Area
        customerListArea = new JTextArea();
        customerListArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(customerListArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Make frame visible
        frame.setVisible(true);

        // Button functionality
        addButtonFunctionality();
    }

    // Method to update the display area with the current list of customers
    public void updateCustomerList() {
        customerListArea.setText("");  // Clear the text area
        for (Customer customer : customerList) {
            customerListArea.append("ID: " + customer.getCustomerId() + ", Name: " + customer.getName() + "\n");
        }
    }

    // Action listener functionality for buttons
    private void addButtonFunctionality() {
        addCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get customer data from the input fields
                int customerId = Integer.parseInt(customerIdField.getText());
                String name = nameField.getText();

                // Create a new Customer object
                Customer newCustomer = new Customer(customerId, name.split(" ")[0], name.split(" ")[1]);

                // Add to the customer list
                customerList.add(newCustomer);

                // Update the display of customers in the list
                updateCustomerList();

                // Clear the input fields after adding
                customerIdField.setText("");
                nameField.setText("");
            }
        });

        // Action listener for the back button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Hide the CargoView
                frame.setVisible(false);

                // Return to the MainScreen
                new MainScreen();  // This will display the MainScreen
            }
        });
    }

    // Getter for the Add Customer button
    public JButton getAddCustomerButton() {
        return addCustomerButton;
    }

    public JTextField getCustomerIdField() {
        return customerIdField;
    }

    public JTextField getNameField() {
        return nameField;
    }

    public JTextArea getCustomerListArea() {
        return customerListArea;
    }

    public void clearInputFields() {
        customerIdField.setText("");
        nameField.setText("");
    }

    public int getCustomerId() {
        return Integer.parseInt(customerIdField.getText());
    }

    public String getName() {
        return nameField.getText();
    }
}
