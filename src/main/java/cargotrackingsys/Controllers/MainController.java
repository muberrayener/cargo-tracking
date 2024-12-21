package cargotrackingsys.Controllers;

import cargotrackingsys.Models.Customer;
import cargotrackingsys.Models.Shipment;
import cargotrackingsys.Views.CustomerDetailsView;
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

        // Set up action listeners for both views


        this.cargoView.getUpdateButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateShipmentInfo();
            }
        });

        // Initialize views with existing data
        customerView.updateCustomerList(customerList);  // Update with the current customer list
        cargoView.displayShipmentInfo(shipment.getShipmentId(), shipment.getStatus());


    }





    // Method to handle the update of shipment info
    private void updateShipmentInfo() {
        int shipmentId = cargoView.getShipmentId();
        String status = cargoView.getStatus();

        // Update the shipment model with new data
        shipment.setShipmentId(shipmentId);
        shipment.setDeliveryStatus(status);

        // Update the view with new shipment data
        cargoView.displayShipmentInfo(shipment.getShipmentId(), shipment.getStatus());

        // Clear the input fields
        cargoView.clearInputFields();
    }




}