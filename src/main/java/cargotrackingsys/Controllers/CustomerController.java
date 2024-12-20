package cargotrackingsys.Controllers;

import cargotrackingsys.Models.Customer;
import cargotrackingsys.Views.CargoView;
import cargotrackingsys.Views.CustomerView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class CustomerController {
    private CustomerView view;
    private LinkedList<Customer> customerList;

    public CustomerController(CustomerView view) {
        this.view = view;
        this.customerList = new LinkedList<>();
    }

    // Method to add a customer and update the view
    public void addCustomer(int customerId, String name) {
        // Split name into first and last name
        String[] nameParts = name.split(" ");
        String firstName = nameParts[0];
        String lastName = nameParts[1];

        // Create a new Customer object
        Customer newCustomer = new Customer(customerId, firstName, lastName);

        // Add the customer to the list
        customerList.add(newCustomer);

        // Update the customer list in the view
        view.updateCustomerList();
    }
}

