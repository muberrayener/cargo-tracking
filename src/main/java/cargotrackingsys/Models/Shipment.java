package cargotrackingsys.Models;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Shipment implements Comparable<Shipment> {
    int shipmentId;
    Date shipmentDate;
    String status;
    public int deliveryTime;
    public City startCity;
    public City endCity;

    public Shipment(int shipmentId, Date shipmentDate, String status,City startCity, City endCity ) {
        this.shipmentId = shipmentId;
        this.shipmentDate = shipmentDate;
        this.status = status;
        this.startCity = startCity;
        this.endCity = endCity;
        String route = City.listRoute(this.endCity.getCityName());
        int count =  City.countArrowOccurrences(route);
        this.deliveryTime = count;
    }

    public Shipment(int shipmentId, Date shipmentDate, String status, int deliveryTime) {
        this.shipmentId = shipmentId;
        this.shipmentDate = shipmentDate;
        this.status = status;
        this.deliveryTime = deliveryTime;
    }
    public Shipment(){}

    public int getShipmentId(){
        return this.shipmentId;
    }

    public String getDeliveryStatus() {
        return this.status;
    }

    public int getDeliveryTime() {
        return this.deliveryTime;
    }

    public void setShipmentId(int shipmentId) {
        this.shipmentId = shipmentId;
    }

    public void setDeliveryStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public City getStartCity() {
        return this.startCity;
    }

    public City getEndCity() {
        return this.endCity;
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


    public int calculateDistance(){
        return City.findDistance(this.startCity, this.endCity);
    }

    public void setDeliveryTime(int count) {
        this.deliveryTime = count;
    }
}