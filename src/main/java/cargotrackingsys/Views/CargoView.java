package cargotrackingsys.Views;

import cargotrackingsys.MainScreen;
import cargotrackingsys.Models.Customer;
import cargotrackingsys.Models.Shipment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class CargoView {

    private JFrame cargoFrame;
    private JTextField shipmentIdField;
    private JTextField shipmentStatusField;
    private JButton updateButton;
    private JButton backButton;
    private JTextArea displayArea;  // For displaying shipment information
    public LinkedList<Customer> customerList;

    public CargoView(LinkedList<Customer> customerList) {
        // Initialize the Cargo view frame
        this.customerList = customerList;
        cargoFrame = new JFrame("Cargo Information");
        cargoFrame.setSize(400, 300);
        cargoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cargoFrame.setLayout(new BorderLayout());

        // Create a panel for input fields and buttons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));  // 3 rows, 2 columns

        // Add input fields and labels for Shipment details
        panel.add(new JLabel("Shipment ID:"));
        shipmentIdField = new JTextField(15);
        panel.add(shipmentIdField);

        panel.add(new JLabel("Shipment Status:"));
        shipmentStatusField = new JTextField(15);
        panel.add(shipmentStatusField);

        // Update button
        updateButton = new JButton("Update Shipment");
        panel.add(updateButton);

        // Back button to return to the main screen
        backButton = new JButton("Back to Main Screen");
        panel.add(backButton);

        // Display Area (for shipment info)
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setPreferredSize(new Dimension(400, 150));
        cargoFrame.add(panel, BorderLayout.NORTH);
        cargoFrame.add(new JScrollPane(displayArea), BorderLayout.CENTER);

        // Make the Cargo view frame visible
        cargoFrame.setVisible(false);

        // Adding functionality to buttons
        addButtonFunctionality();
    }

    // Method to add functionality to buttons
    private void addButtonFunctionality() {
        // Action listener for the update button
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve shipment data from the text fields
                int shipmentId = getShipmentId();
                String status = getShipmentStatus();

                // Display the updated shipment information
                displayShipmentInfo(shipmentId, status);

                // Clear the input fields
                clearInputFields();
            }
        });

        // Action listener for the back button
        backButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // Hide the CargoView
                cargoFrame.setVisible(false);

                // Return to the MainScreen
                new MainScreen(customerList);  // This will display the MainScreen
            }
        });
    }

    // Method to display shipment information in the view
    public void displayShipmentInfo(int shipmentId, String status) {
        displayArea.setText("Shipment ID: " + shipmentId + "\nStatus: " + status);
    }

    // Method to clear the input fields after update
    public void clearInputFields() {
        shipmentIdField.setText("");
        shipmentStatusField.setText("");
    }

    // Getters for shipment ID and shipment status
    public int getShipmentId() {
        return Integer.parseInt(shipmentIdField.getText()); // Assuming ID is a number
    }

    public String getShipmentStatus() {
        return shipmentStatusField.getText();
    }

    // Get the update button
    public JButton getUpdateButton() {
        return updateButton;
    }

    // Get the back button
    public JButton getBackButton() {
        return backButton;
    }

    // Get the frame
    public JFrame getCargoFrame() {
        return cargoFrame;
    }

    public String getStatus() {
        return shipmentStatusField.getText();
    }

    public void setVisibility (boolean visible) {
        cargoFrame.setVisible(visible);
    }
}