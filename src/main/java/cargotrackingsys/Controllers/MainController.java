package cargotrackingsys.Controllers;

import cargotrackingsys.Models.Customer;
import cargotrackingsys.Models.Shipment;
import cargotrackingsys.Views.CustomerView;
import cargotrackingsys.Views.CargoView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class MainController {

    private LinkedList<Customer> customerList;
    private Shipment shipment;
    private CustomerView customerView;
    private CargoView cargoView;

    public MainController(LinkedList<Customer> customerList, Shipment shipment, CustomerView customerView, CargoView cargoView) {
        this.customerList = customerList;
        this.shipment = shipment;
        this.customerView = customerView;
        this.cargoView = cargoView;

        this.cargoView.getUpdateButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateShipmentInfo();
            }
        });

        customerView.updateCustomerList(customerList);
        cargoView.displayShipmentInfo(shipment.getShipmentId(), shipment.getStatus());
    }
    private void updateShipmentInfo() {
        int shipmentId = cargoView.getShipmentId();
        String status = cargoView.getStatus();

        shipment.setShipmentId(shipmentId);
        shipment.setDeliveryStatus(status);

        cargoView.displayShipmentInfo(shipment.getShipmentId(), shipment.getStatus());

        cargoView.clearInputFields();
    }
}