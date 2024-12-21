package cargotrackingsys.Models;

import java.util.Stack;

class ShipmentHistory {
    Stack<Shipment> stack;

    // Constructor
    public ShipmentHistory() {
        stack = new Stack<>();
    }

    public void addShipment(Shipment shipment) {
        if (stack.size() == 5) {
            stack.pop();  // Remove the oldest shipment if more than 5
        }
        stack.push(shipment);
    }

    public void displayRecentShipments() {
        if (stack.isEmpty()) {
            System.out.println("No shipments to display.");
        } else {
            for (Shipment shipment : stack) {
                System.out.println("Shipment ID: " + shipment.shipmentId + ", Date: " + shipment.shipmentDate + ", Status: " + shipment.status + ", Delivery Time: " + shipment.deliveryTime);
            }
        }
    }
}
