package cargotrackingsys.Helpers;

import cargotrackingsys.Models.Customer;

import java.util.LinkedList;

public class Finders
{

    public static Customer findCustomerById(LinkedList<Customer> customerList, int customerId) {
        for (Customer customer : customerList) {
            if (customer.getCustomerId() == customerId) {
                return customer;
            }
        }
        return null;
    }
}
