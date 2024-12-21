package cargotrackingsys.Views;

import cargotrackingsys.Models.Customer;
import javax.swing.*;
import java.awt.*;

public class CustomerDetailsView {
    private JFrame detailsFrame;
    private JLabel customerIdLabel;
    private JLabel nameLabel;
    private JLabel cargoLabel;
    private JTextField customerIdField;
    private JTextField nameField;
    private JTextField cargoField;
    private JButton closeButton;

    public CustomerDetailsView(Customer customer) {
        detailsFrame = new JFrame("Customer Details");
        detailsFrame.setSize(400, 300);
        detailsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        detailsFrame.setLayout(new GridLayout(4, 2));

        customerIdLabel = new JLabel("Customer ID:");
        customerIdField = new JTextField(String.valueOf(customer.getCustomerId()));
        customerIdField.setEditable(false);

        nameLabel = new JLabel("Full Name:");
        nameField = new JTextField(customer.getName());
        nameField.setEditable(false);

        cargoLabel = new JLabel("Cargo ID");
        cargoField = new JTextField(customer.getShipmentHistory().size());
        cargoField.setEditable(false);

        closeButton = new JButton("Close");
        closeButton.addActionListener(e -> detailsFrame.dispose());

        detailsFrame.add(customerIdLabel);
        detailsFrame.add(customerIdField);
        detailsFrame.add(nameLabel);
        detailsFrame.add(nameField);
        detailsFrame.add(cargoLabel);
        detailsFrame.add(cargoField);
        detailsFrame.add(new JLabel(""));
        detailsFrame.add(closeButton);

        detailsFrame.setVisible(true);
    }
}