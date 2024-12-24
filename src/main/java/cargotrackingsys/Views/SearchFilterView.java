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
    private JTextArea undeliveredCargoArea;
    private JLabel  undeliveredCargoAreaLabel;
    private JTextField deliveredCargoIdField;
    private JTextField cargoNameField;
    private JButton findDeliveredCargoButton;
    private JButton sortUndeliveredCargoButton;
    private LinkedList<Customer> customerList;
    private ArrayList<Shipment> shipmentList;
    public SearchFilterView(LinkedList<Customer> customerList) {
        this.customerList = customerList;
        this.shipmentList = Finders.getAllShipments(customerList);
        frame = new JFrame("Cargo Details");
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new GridLayout(6, 1));

        deliveredCargoIdLabel = new JLabel("Delivered Cargo Id");
        deliveredCargoIdField = new JTextField();

        findDeliveredCargoButton = new JButton("Find Delivered Cargo by Id");
        cargoNameField = new JTextField();
        sortUndeliveredCargoButton = new JButton("Sort NonDelivered Cargos");
        undeliveredCargoAreaLabel = new JLabel("Undelivered Cargo Id");
        undeliveredCargoArea = new JTextArea(3,1);
        undeliveredCargoArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(undeliveredCargoArea);

        frame.add(deliveredCargoIdLabel);
        frame.add(deliveredCargoIdField);
        frame.add(findDeliveredCargoButton);
        frame.add(cargoNameField);
        frame.add(sortUndeliveredCargoButton);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setVisible(true);
        addButtonFunctionality();
    }

    private void addButtonFunctionality() {

        findDeliveredCargoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String cargoId = deliveredCargoIdField.getText().trim();
                ArrayList<Shipment> deliveredShipments = Finders.filterShipmentsByStatus(shipmentList, true);
                Shipment shipment = SortingAndSearching.findShipmentById(deliveredShipments, Integer.parseInt(cargoId));
                if (shipment == null) {
                    JOptionPane.showMessageDialog(frame, "Delivered Cargo not found.");
                    clearInputFields();
                }
                cargoNameField.setText("ID: "+shipment.getShipmentId()+ " Status: "+ shipment.getDeliveryStatus() + " Destination: "+ shipment.getEndCity().getCityName() + " Delivery Time: "+ shipment.getDeliveryTime() );
            }
        });

        sortUndeliveredCargoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ArrayList<Shipment> undeliveredShipments = Finders.filterShipmentsByStatus(shipmentList, false);
                SortingAndSearching.mergeSort(undeliveredShipments);
                if (undeliveredShipments.size() == 0) {
                    JOptionPane.showMessageDialog(frame, "Non-Delivered Cargo not found.");
                    clearInputFields();
                }
                for(Shipment shipment : undeliveredShipments) {
                    undeliveredCargoArea.append("ID: "+shipment.getShipmentId()+ " Status: "+ shipment.getDeliveryStatus() + " Destination: "+ shipment.getEndCity().getCityName() + " Delivery Time: "+ shipment.getDeliveryTime() +"\n" );
                }
            }
        });

    }

    public void clearInputFields() {
        deliveredCargoIdField.setText("");
    }
}
