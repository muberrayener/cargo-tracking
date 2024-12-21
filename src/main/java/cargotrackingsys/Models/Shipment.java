package cargotrackingsys.Models;

import java.util.Date;

public class Shipment implements Comparable<Shipment> {
    int shipmentId;
    Date shipmentDate;
    String status;
    public int deliveryTime;
    public City startCity;
    public City endCity;

    public Shipment(int shipmentId, Date shipmentDate, String status, int deliveryTime,City startCity, City endCity ) {
        this.shipmentId = shipmentId;
        this.shipmentDate = shipmentDate;
        this.status = status;
        this.deliveryTime = deliveryTime;
        this.startCity = startCity;
        this.endCity = endCity;
    }

    public Shipment(int shipmentId, Date shipmentDate, String status, int deliveryTime) {
        this.shipmentId = shipmentId;
        this.shipmentDate = shipmentDate;
        this.status = status;
        this.deliveryTime = deliveryTime;
    }
    public Shipment(){}

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

    public void setStatus(String status) {
        this.status = status;
    }

    public City getStartCity() {
        return startCity;
    }

    public City getEndCity() {
        return endCity;
    }

    public void setStartCity(City startCity) {
        this.startCity = startCity;
    }

    public void setEndCity(City endCity) {
        this.endCity = endCity;
    }

    public void displayShipment() {
        System.out.println("Cargo ID: " + shipmentId + ", Delivery Time: " + deliveryTime + " days, Status: " + status);
    }

    @Override
    public int compareTo(Shipment other) {
        return Integer.compare(this.deliveryTime, other.deliveryTime);
    }
}