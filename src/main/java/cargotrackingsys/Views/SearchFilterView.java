package cargotrackingsys.Views;

import cargotrackingsys.Helpers.Finders;
import cargotrackingsys.Helpers.SortingAndSearching;
import cargotrackingsys.Models.Customer;
import cargotrackingsys.Models.Shipment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;

public class SearchFilterView {
    private JFrame frame;
    private JLabel deliveredCargoIdLabel;
    private JTextField deliveredCargoIdField;
    private JTextField cargoNameField;
    private JButton findDeliveredCargoButton;
    private LinkedList<Customer> customerList;
    private ArrayList<Shipment> shipmentList;
    public SearchFilterView(LinkedList<Customer> customerList) {
        this.customerList = customerList;
        this.shipmentList = Finders.getAllShipments(customerList);
        frame = new JFrame("Cargo Details");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new GridLayout(8, 2));

        deliveredCargoIdLabel = new JLabel("Delivered Cargo Id");
        deliveredCargoIdField = new JTextField();

        findDeliveredCargoButton = new JButton("Find Delivered Cargo by Id");
        cargoNameField = new JTextField();

        frame.add(deliveredCargoIdField);
        frame.add(deliveredCargoIdLabel);
        frame.add(findDeliveredCargoButton);
        frame.add(cargoNameField);

        frame.setVisible(true);
        addButtonFunctionality();
    }

    private void addButtonFunctionality() {

        findDeliveredCargoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String cargoId = deliveredCargoIdField.getText();
                ArrayList<Shipment> deliveredShipments = Finders.filterShipmentsByStatus(shipmentList, true);
                Shipment shipment = SortingAndSearching.findShipmentById(deliveredShipments, Integer.parseInt(cargoId));
                if (shipment == null) {
                    JOptionPane.showMessageDialog(frame, "Cargo not found.");
                    clearInputFields();
                }
                cargoNameField.setText("ID: "+shipment.getShipmentId()+ " Status: "+ shipment.getDeliveryStatus() + " Destination: "+ shipment.getEndCity().getCityName() + " Delivery Time: "+ shipment.getDeliveryTime() );
            }
        });
    }

    public void clearInputFields() {
        deliveredCargoIdField.setText("");
    }
}
