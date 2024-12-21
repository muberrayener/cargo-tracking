package cargotrackingsys.Models;
import java.util.LinkedList;

public class Customer {
    int customerId;
    String firstName;
    String lastName;
    LinkedList<Shipment> shipmentHistory;

    public Customer(int customerId, String firstName, String lastName) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.shipmentHistory = new LinkedList<>();
    }

    public void addShipment(Shipment shipment) {
        int index = 0;
        while (index < shipmentHistory.size() && shipmentHistory.get(index).shipmentDate.before(shipment.shipmentDate)) {
            index++;
        }
        shipmentHistory.add(index, shipment);
        System.out.println(shipment.status);
    }

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

    public LinkedList<Shipment> getShipmentHistory() {
        return this.shipmentHistory;
    }

    public void setCustomerId(int customerId) {
    }

    public void setName(String name) {
    }
}
