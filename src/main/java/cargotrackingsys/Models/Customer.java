package cargotrackingsys.Models;
import java.util.LinkedList;
import java.util.Stack;

public class Customer {
    int customerId;
    String firstName;
    String lastName;
    Stack<Shipment> shipmentHistory;

    public Customer(int customerId, String firstName, String lastName) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.shipmentHistory = new Stack<Shipment>();
    }

    public void addShipment(Shipment shipment) {

        shipmentHistory.push(shipment);
        shipment.displayShipment();
    }


    public int getCustomerId() {
        return customerId;
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    public Stack<Shipment> getShipmentHistory() {
        return this.shipmentHistory;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setName(String name) {
        this.firstName = name.split(" ")[0];
        this.lastName = name.split(" ")[1];
    }

}
