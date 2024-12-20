package cargotrackingsys.Models;
import java.util.LinkedList;

public class Customer {
    int customerId;
    String firstName;
    String lastName;
    LinkedList<Shipment> shipmentHistory;  // LinkedList to store the shipment history

    // Constructor
    public Customer(int customerId, String firstName, String lastName) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.shipmentHistory = new LinkedList<>();
    }

    // Method to add shipment while maintaining sorted order based on shipmentDate
    public void addShipment(Shipment shipment) {
        int index = 0;
        while (index < shipmentHistory.size() && shipmentHistory.get(index).shipmentDate.before(shipment.shipmentDate)) {
            index++;
        }
        shipmentHistory.add(index, shipment);
    }

    // Display customer shipment history
    public void displayShipmentHistory() {
        for (Shipment shipment : shipmentHistory) {
            System.out.println("Shipment ID: " + shipment.shipmentId + ", Date: " + shipment.shipmentDate + ", Status: " + shipment.status + ", Delivery Time: " + shipment.deliveryTime);
        }
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    public void setCustomerId(int customerId) {
    }

    public void setName(String name) {
    }
}
