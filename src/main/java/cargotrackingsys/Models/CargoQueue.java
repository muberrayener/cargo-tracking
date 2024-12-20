package cargotrackingsys.Models;
import java.util.PriorityQueue;

class CargoQueue {
    PriorityQueue<Shipment> queue;

    // Constructor
    public CargoQueue() {
        queue = new PriorityQueue<>();
    }

    // Method to add cargo to the queue
    public void addCargo(Shipment cargo) {
        queue.offer(cargo);
    }

    // Method to process (retrieve) cargo with the shortest delivery time
    public Shipment processCargo() {
        return queue.poll();  // Retrieves and removes the head of the queue
    }
}