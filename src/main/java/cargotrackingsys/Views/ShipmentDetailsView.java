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
    private JLabel statusLabel;
    private JLabel nameLabel;
    private JLabel cargoLabel;
    private JLabel routeLabel;
    private JTextArea routeField;
    private JLabel deliveryLabel;
    private JTextField deliveryField;
    private JTextField statusField;
    private JTextField nameField;
    private JTextField cargoField;
    private JTextField cargoIdField;
    private JLabel cargoIdLabel;
    private JButton closeButton;

    public ShipmentDetailsView(Shipment shipment) {
        detailsFrame = new JFrame("Cargo Details");
        detailsFrame.setSize(600, 400);
        detailsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        detailsFrame.setLayout(new GridLayout(8, 2));

        cargoIdLabel = new JLabel("Cargo Id");
        cargoIdField = new JTextField(String.valueOf(shipment.getShipmentId()));
        cargoIdField.setEditable(false);

        nameLabel = new JLabel("Start Location");
        nameField = new JTextField(shipment.getStartCity().getCityName());
        nameField.setEditable(false);

        cargoLabel = new JLabel("Destination City");
        cargoField = new JTextField(shipment.getEndCity().getCityName());
        cargoField.setEditable(false);

        statusLabel = new JLabel("Status");
        statusField = new JTextField(shipment.getDeliveryStatus());
        statusField.setEditable(false);

        routeLabel = new JLabel("Route");
        String route = City.listRoute(shipment.endCity.getCityName());
        routeField = new JTextArea(route);
        routeField.setEditable(false);
        int count =  City.countArrowOccurrences(route);
        shipment.setDeliveryTime(count);

        deliveryLabel = new JLabel("Delivery Time");
        deliveryField = new JTextField(String.valueOf(shipment.getDeliveryTime())+" days");
        deliveryField.setEditable(false);


        closeButton = new JButton("Close");
        closeButton.addActionListener(e -> detailsFrame.dispose());


        detailsFrame.add(cargoIdLabel);
        detailsFrame.add(cargoIdField);
        detailsFrame.add(statusLabel);
        detailsFrame.add(statusField);
        detailsFrame.add(nameLabel);
        detailsFrame.add(nameField);
        detailsFrame.add(cargoLabel);
        detailsFrame.add(cargoField);
        detailsFrame.add(routeLabel);
        detailsFrame.add(routeField);
        detailsFrame.add(deliveryLabel);
        detailsFrame.add(deliveryField);
        detailsFrame.add(new JLabel(""));
        detailsFrame.add(closeButton);

        detailsFrame.setVisible(true);
    }


}
