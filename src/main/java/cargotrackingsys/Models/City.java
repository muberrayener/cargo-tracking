package cargotrackingsys.Models;

import java.util.ArrayList;

class City {
    int cityId;
    String cityName;
    ArrayList<City> children; // Child cities (connected cities)

    // Constructor
    public City(int cityId, String cityName) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.children = new ArrayList<>();
    }

    // Method to add a child city (sub-city)
    public void addChild(City city) {
        this.children.add(city);
    }

    // Display city information
    public void displayCity() {
        System.out.println("City ID: " + cityId + ", City Name: " + cityName);
    }
}