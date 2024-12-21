package cargotrackingsys.Models;
import java.util.PriorityQueue;

class CargoQueue {
    PriorityQueue<Shipment> queue;

    public CargoQueue() {
        queue = new PriorityQueue<>();
    }

    public void addCargo(Shipment cargo) {
        queue.offer(cargo);
    }

    public Shipment processCargo() {
        return queue.poll();
    }
}