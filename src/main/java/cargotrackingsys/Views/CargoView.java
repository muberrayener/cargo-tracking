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
    private JTextArea displayArea;
    public LinkedList<Customer> customerList;

    public CargoView(LinkedList<Customer> customerList) {

        this.customerList = customerList;
        cargoFrame = new JFrame("Cargo Information");
        cargoFrame.setSize(400, 300);
        cargoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cargoFrame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        panel.add(new JLabel("Shipment ID:"));
        shipmentIdField = new JTextField(15);
        panel.add(shipmentIdField);

        panel.add(new JLabel("Shipment Status:"));
        shipmentStatusField = new JTextField(15);
        panel.add(shipmentStatusField);

        updateButton = new JButton("Update Shipment");
        panel.add(updateButton);

        backButton = new JButton("Back to Main Screen");
        panel.add(backButton);

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setPreferredSize(new Dimension(400, 150));
        cargoFrame.add(panel, BorderLayout.NORTH);
        cargoFrame.add(new JScrollPane(displayArea), BorderLayout.CENTER);

        cargoFrame.setVisible(false);

        addButtonFunctionality();
    }

    private void addButtonFunctionality() {

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int shipmentId = getShipmentId();
                String status = getShipmentStatus();

                displayShipmentInfo(shipmentId, status);

                clearInputFields();
            }
        });

        backButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cargoFrame.setVisible(false);

                new MainScreen(customerList);
            }
        });
    }

    public void displayShipmentInfo(int shipmentId, String status) {
        displayArea.setText("Shipment ID: " + shipmentId + "\nStatus: " + status);
    }

    public void clearInputFields() {
        shipmentIdField.setText("");
        shipmentStatusField.setText("");
    }

    public int getShipmentId() {
        return Integer.parseInt(shipmentIdField.getText()); // Assuming ID is a number
    }

    public String getShipmentStatus() {
        return shipmentStatusField.getText();
    }

    public JButton getUpdateButton() {
        return updateButton;
    }

    public JButton getBackButton() {
        return backButton;
    }

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