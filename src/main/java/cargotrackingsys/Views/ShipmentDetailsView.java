package cargotrackingsys.Views;

import cargotrackingsys.Models.City;
import cargotrackingsys.Models.Customer;
import cargotrackingsys.Models.Shipment;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ShipmentDetailsView {
    private JFrame detailsFrame;
    private JLabel customerIdLabel;
    private JLabel nameLabel;
    private JLabel cargoLabel;
    private JLabel routeLabel;
    private JTextArea routeField;
    private JLabel deliveryLabel;
    private JTextField deliveryField;
    private JTextField customerIdField;
    private JTextField nameField;
    private JTextField cargoField;
    private JButton closeButton;

    public ShipmentDetailsView(Shipment shipment) {
        detailsFrame = new JFrame("Cargo Details");
        detailsFrame.setSize(400, 300);
        detailsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        detailsFrame.setLayout(new GridLayout(8, 2));

        customerIdLabel = new JLabel("Cargo ID:");
        customerIdField = new JTextField(String.valueOf(shipment.getShipmentId()));
        customerIdField.setEditable(false);

        nameLabel = new JLabel("Start Location");
        nameField = new JTextField(shipment.getStartCity().getCityName());
        nameField.setEditable(false);

        cargoLabel = new JLabel("Destination City");
        cargoField = new JTextField(shipment.getEndCity().getCityName());
        cargoField.setEditable(false);

        deliveryLabel = new JLabel("Delivery Time");
        deliveryField = new JTextField(shipment.getDeliveryTime());
        deliveryField.setEditable(false);

        routeLabel = new JLabel("Route");
        routeField = new JTextArea(String.valueOf(shipment.showRoute()));
        routeField.setEditable(false);

        closeButton = new JButton("Close");
        closeButton.addActionListener(e -> detailsFrame.dispose());

        detailsFrame.add(customerIdLabel);
        detailsFrame.add(customerIdField);
        detailsFrame.add(nameLabel);
        detailsFrame.add(nameField);
        detailsFrame.add(cargoLabel);
        detailsFrame.add(cargoField);
        detailsFrame.add(deliveryLabel);
        detailsFrame.add(deliveryField);
        detailsFrame.add(routeLabel);
        detailsFrame.add(routeField);
        detailsFrame.add(new JLabel(""));
        detailsFrame.add(closeButton);

        detailsFrame.setVisible(true);
    }


}
