package cargotrackingsys.Controllers;

import cargotrackingsys.Models.Customer;
import cargotrackingsys.Models.Shipment;
import cargotrackingsys.Views.CargoView;
import cargotrackingsys.Views.CustomerView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainController {

    private Customer customer;
    private Shipment shipment;
    private CustomerView customerView;
    private CargoView cargoView;

    public MainController(Customer customer, Shipment shipment, CustomerView customerView, CargoView cargoView) {
        this.customer = customer;
        this.shipment = shipment;
        this.customerView = customerView;
        this.cargoView = cargoView;

        // Set up action listeners for both views
        this.customerView.getUpdateButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateCustomerInfo();
            }
        });

        this.cargoView.getUpdateButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateShipmentInfo();
            }
        });

        // Initialize views with existing data
        customerView.displayCustomerInfo(customer.getCustomerId(), customer.getName());
        cargoView.displayShipmentInfo(shipment.getShipmentId(), shipment.getStatus());
    }

    private void updateCustomerInfo() {
        int customerId = customerView.getCustomerId();
        String name = customerView.getName();

        // Update the customer model with new data
        customer.setCustomerId(customerId);
        customer.setName(name);

        // Update the view with new customer data
        customerView.displayCustomerInfo(customer.getCustomerId(), customer.getName());

        // Clear the input fields
        customerView.clearInputFields();
    }

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