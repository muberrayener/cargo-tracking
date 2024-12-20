package cargotrackingsys.Controllers;

import cargotrackingsys.Models.Customer;
import cargotrackingsys.Views.CargoView;
import cargotrackingsys.Views.CustomerView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerController {

    private Customer customer;
    private CustomerView view;

    public CustomerController(Customer customer, CustomerView view) {
        this.customer = customer;
        this.view = view;

        // Set up the update button listener
        this.view.getUpdateButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateCustomerInfo();
            }
        });

        // Display initial customer information
        view.displayCustomerInfo(customer.getCustomerId(), customer.getName());
    }

    // Method to update customer information when the button is clicked
    private void updateCustomerInfo() {
        int customerId = view.getCustomerId();
        String name = view.getName();

        // Update the customer model with the new values
        customer.setCustomerId(customerId);
        customer.setName(name);

        // Update the view with the new customer data
        view.displayCustomerInfo(customer.getCustomerId(), customer.getName());

        // Clear the input fields after update
        view.clearInputFields();
    }
}
