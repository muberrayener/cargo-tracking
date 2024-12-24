package cargotrackingsys.Helpers;

import cargotrackingsys.Models.Customer;
import cargotrackingsys.Models.Shipment;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

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

    public static ArrayList<Shipment> collectAllShipments(ArrayList<Stack<Shipment>> stacksList) {
        ArrayList<Shipment> allShipments = new ArrayList<>();

        // Iterate through each stack and add all shipments to the list
        for (Stack<Shipment> stack : stacksList) {
            allShipments.addAll(stack);  // Add all shipments from the stack to the list
        }

        return allShipments;
    }

    public static ArrayList<Shipment> getAllShipments(LinkedList<Customer> customerList) {
        ArrayList<Stack<Shipment>> stacksList = new ArrayList<>();
        for (Customer customer : customerList) {
            stacksList.add(customer.getShipmentHistory());
        }
        return collectAllShipments(stacksList);
    }

    public static ArrayList<Shipment> filterShipmentsByStatus(ArrayList<Shipment> allShipments, Boolean deliveredFlag) {
        ArrayList<Shipment> deliveredShipments = new ArrayList<>();
        ArrayList<Shipment> nonDeliveredShipments = new ArrayList<>();

        // Loop through all shipments and separate them by their status
        for (Shipment shipment : allShipments) {
            if ("DELIVERED".equals(shipment.getStatus())) {
                deliveredShipments.add(shipment);
            } else {
                nonDeliveredShipments.add(shipment);
            }
        }

        if (deliveredFlag) {
            return deliveredShipments;
        }

        return nonDeliveredShipments;
    }
}
