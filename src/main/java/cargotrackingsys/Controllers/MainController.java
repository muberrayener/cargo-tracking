package cargotrackingsys.Controllers;

import cargotrackingsys.Models.Customer;
import cargotrackingsys.Models.Shipment;
import cargotrackingsys.Views.CustomerView;
import cargotrackingsys.Views.CargoView;

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
        this.customerView.getAddCustomerButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNewCustomer();
            }
        });

        this.customerView.getCustomerListArea().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                displaySelectedCustomerDetails();
            }
        });

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

    // Method to add a new customer
    private void addNewCustomer() {
        // Get the customer data from the view
        int customerId = customerView.getCustomerId();
        String name = customerView.getName();

        // Create a new customer and add it to the list
        Customer newCustomer = new Customer(customerId, name.split(" ")[0], name.split(" ")[1]);
        customerList.add(newCustomer);

        // Update the customer display in the view
        customerView.updateCustomerList(customerList);
        customerView.clearInputFields();  // Clear the input fields after adding
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

    // Method to display details of the selected customer
    private void displaySelectedCustomerDetails() {
        // Get the selected customer from the JList
        Customer selectedCustomer = customerView.getSelectedCustomer();

        if (selectedCustomer != null) {
            // Show customer details in the text fields or other areas
            customerView.displayCustomerDetails(selectedCustomer);
        }
    }
}