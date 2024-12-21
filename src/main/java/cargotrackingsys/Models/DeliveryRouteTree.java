package cargotrackingsys.Models;

class DeliveryRouteTree {
    City root;


    public DeliveryRouteTree(City root) {
        this.root = root;
    }


    public void displayTree(City city, String indent) {
        city.ShowCity();
        for (City child : city.children) {
            displayTree(child, indent + "  ");
        }
    }
}