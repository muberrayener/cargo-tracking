package cargotrackingsys.Models;

class DeliveryRouteTree {
    City root;  // Root city (company headquarters)

    // Constructor
    public DeliveryRouteTree(City root) {
        this.root = root;
    }

    // Method to display the entire delivery route tree
    public void displayTree(City city, String indent) {
        city.displayCity();
        for (City child : city.children) {
            displayTree(child, indent + "  ");
        }
    }
}