package cargotrackingsys.Views;

import cargotrackingsys.Models.Customer;
import javax.swing.*;
import java.awt.*;

public class CustomerDetailsView {
    private JFrame detailsFrame;
    private JLabel customerIdLabel;
    private JLabel nameLabel;
    private JTextField customerIdField;
    private JTextField nameField;
    private JButton closeButton;

    public CustomerDetailsView(Customer customer) {
        // Initialize the details frame
        detailsFrame = new JFrame("Customer Details");
        detailsFrame.setSize(400, 300);
        detailsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // Close the details window only
        detailsFrame.setLayout(new GridLayout(3, 2));  // Layout with 3 rows, 2 columns

        // Customer ID and Name labels
        customerIdLabel = new JLabel("Customer ID:");
        customerIdField = new JTextField(String.valueOf(customer.getCustomerId()));
        customerIdField.setEditable(false);  // Make the customer ID field non-editable

        nameLabel = new JLabel("Full Name:");
        nameField = new JTextField(customer.getName());
        nameField.setEditable(false);  // Make the name field non-editable

        // Close button to close the details screen
        closeButton = new JButton("Close");
        closeButton.addActionListener(e -> detailsFrame.dispose());  // Close the details screen

        // Add components to the frame
        detailsFrame.add(customerIdLabel);
        detailsFrame.add(customerIdField);
        detailsFrame.add(nameLabel);
        detailsFrame.add(nameField);
        detailsFrame.add(new JLabel(""));  // Empty label for spacing
        detailsFrame.add(closeButton);

        // Make the details frame visible
        detailsFrame.setVisible(true);
    }
}