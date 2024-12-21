package cargotrackingsys.Views;

import cargotrackingsys.MainScreen;
import cargotrackingsys.Models.Customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomerView {
    private JFrame frame;
    private JTextField customerIdField;
    private JTextField nameField;
    private JList<Customer> customerListArea;  // To display the list of customers
    private JButton addCustomerButton;
    private JButton backButton;
    private JButton viewDetailsButton;
    private LinkedList<Customer> customerList;
    private DefaultListModel<Customer> customerListModel;  // Linked list to hold customer objects

    public CustomerView(LinkedList<Customer> customerList) {
        // Initialize customer list
        this.customerList = customerList;
        customerListModel = new DefaultListModel<>();
        updateCustomerList(this.customerList);
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
        customerListArea = new JList<>(customerListModel);  // Set model to JList
        customerListArea.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        customerListArea.setVisibleRowCount(20);
        customerListArea.setCellRenderer(new CustomerRenderer());

        JScrollPane scrollPane = new JScrollPane(customerListArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Customer Details Panel
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new GridLayout(1, 2));

        viewDetailsButton = new JButton("View Details");
        detailsPanel.add(viewDetailsButton);

        frame.add(detailsPanel, BorderLayout.SOUTH);

        // Make frame visible
        frame.setVisible(false);

        // Button functionality
        addButtonFunctionality();
        addListSelectionListener();
    }

    // Method to update the display area with the current list of customers
    public void updateCustomerList(LinkedList<Customer> customers) {
        customerListModel.clear();
        for (Customer customer : customers) {
            customerListModel.addElement(customer);
        }
    }

    // Action listener functionality for buttons
    private void addButtonFunctionality() {
        addCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get customer data from the input fields
                try {
                    int customerId = Integer.parseInt(customerIdField.getText());
                    String name = nameField.getText();

                    if (name.trim().isEmpty() || customerId <= 0) {
                        JOptionPane.showMessageDialog(frame, "Please enter valid customer details.");
                        return;
                    }

                    // Create a new Customer object
                    Customer newCustomer = new Customer(customerId, name.split(" ")[0], name.split(" ")[1]);

                    // Add to the customer list
                    customerList.add(newCustomer);

                    // Update the display of customers in the list
                    updateCustomerList(customerList);

                    // Clear the input fields after adding
                    customerIdField.setText("");
                    nameField.setText("");
                    customerIdField.requestFocus();  // Focus on Customer ID for the next entry
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Customer ID must be a valid number.");
                }
            }
        });

        // Action listener for the back button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Hide the CustomerView
                frame.setVisible(false);

                // Return to the MainScreen
                new MainScreen(customerList);  // This will display the MainScreen
            }
        });

        // Action listener for the "View Details" button
        viewDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Customer selectedCustomer = getSelectedCustomer();
                if (selectedCustomer != null) {
                    // Open the Customer Details screen
                    new CustomerDetailsView(selectedCustomer);
                } else {
                    JOptionPane.showMessageDialog(frame, "Please select a customer from the list.");
                }
            }
        });
    }

    // Add a MouseListener to the customer list to allow selection of customers
    private void addListSelectionListener() {
        customerListArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Check if the click is on a valid customer in the list
                int index = customerListArea.locationToIndex(e.getPoint());
                if (index >= 0) {
                    Customer selectedCustomer = customerListArea.getModel().getElementAt(index);
                    displayCustomerDetails(selectedCustomer);
                }
            }
        });
    }

    // Display selected customer's details in the fields
    public void displayCustomerDetails(Customer customer) {
        customerIdField.setText(String.valueOf(customer.getCustomerId()));
        nameField.setText(customer.getName());
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

    public JList<Customer> getCustomerListArea() {
        return customerListArea;
    }

    public void clearInputFields() {
        customerIdField.setText("");
        nameField.setText("");
    }

    public Customer getSelectedCustomer() {
        return customerListArea.getSelectedValue();
    }

    public int getCustomerId() {
        return Integer.parseInt(customerIdField.getText());
    }

    public String getName() {
        return nameField.getText();
    }

    public void setVisibility (boolean visible) {
        frame.setVisible(visible);
    }
}
