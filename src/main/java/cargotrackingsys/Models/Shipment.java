package cargotrackingsys.Models;

import java.util.Date;

public class Shipment implements Comparable<Shipment> {
    int shipmentId;
    Date shipmentDate;
    String status;
    public int deliveryTime; // in days

    // Constructor
    public Shipment(int shipmentId, Date shipmentDate, String status, int deliveryTime) {
        this.shipmentId = shipmentId;
        this.shipmentDate = shipmentDate;
        this.status = status;
        this.deliveryTime = deliveryTime;
    }

    public int getShipmentId(){
        return shipmentId;
    }

    public String getDeliveryStatus() {
        return status;
    }

    public void setShipmentId(int shipmentId) {
    }

    public void setDeliveryStatus(String status) {
    }

    public String getStatus() {
        return status;
    }

    public void displayShipment() {
        System.out.println("Cargo ID: " + shipmentId + ", Delivery Time: " + deliveryTime + " days, Status: " + status);
    }

    @Override
    public int compareTo(Shipment other) {
        return Integer.compare(this.deliveryTime, other.deliveryTime);
    }
}