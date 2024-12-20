package cargotrackingsys.Controllers;

import cargotrackingsys.Models.Customer;
import cargotrackingsys.Models.Shipment;
import cargotrackingsys.Views.CustomerView;
import cargotrackingsys.Views.CargoView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class MainController {

    private LinkedList<Customer> customerList;  // List to store customers
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

        this.cargoView.getUpdateButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateShipmentInfo();
            }
        });

        // Initialize views with existing data
        updateCustomerListDisplay();
        cargoView.displayShipmentInfo(shipment.getShipmentId(), shipment.getStatus());
    }

    private void addNewCustomer() {
        // Get the customer data from the view
        int customerId = customerView.getCustomerId();
        String name = customerView.getName();

        // Create new customer and add it to the list
        Customer newCustomer = new Customer(customerId, name.split(" ")[0], name.split(" ")[1]);
        customerList.add(newCustomer);

        // Update the customer display in the view
        customerView.updateCustomerList();
        customerView.clearInputFields();  // Clear the input fields after adding
    }

    private void updateCustomerInfo() {
        int customerId = customerView.getCustomerId();
        String name = customerView.getName();

        // Search for the customer in the list and update their information
        for (Customer customer : customerList) {
            if (customer.getCustomerId() == customerId) {
                customer.setName(name);
                break;
            }
        }

        // Update the view with the new customer data
        customerView.updateCustomerList();
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

    // Utility method to update the customer list in the view
    private void updateCustomerListDisplay() {
        customerView.updateCustomerList();
    }
}
