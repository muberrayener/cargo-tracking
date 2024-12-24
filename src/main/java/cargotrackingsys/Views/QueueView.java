package cargotrackingsys.Views;

import cargotrackingsys.Helpers.Finders;
import cargotrackingsys.Helpers.SortingAndSearching;
import cargotrackingsys.Models.Customer;
import cargotrackingsys.Models.Shipment;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class QueueView {
    private JFrame frame;
    private LinkedList<Customer> customerList;
    private JTextArea cargoArea;
    private ArrayList<Shipment> shipmentList;
    private PriorityQueue<Shipment> queue;
    public QueueView(LinkedList<Customer> customerList) {
        this.customerList = customerList;
        this.shipmentList = Finders.getAllShipments(customerList);
        ArrayList<Shipment> undeliveredShipments = Finders.filterShipmentsByStatus(shipmentList, false);
        this.queue = new PriorityQueue<>(byDeliveryTime);
        for (Shipment shipment : undeliveredShipments) {
            this.queue.offer(shipment);
        }

        this.frame = new JFrame("Cargo Queue");
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new GridLayout(2, 1));

        cargoArea = new JTextArea();
        cargoArea.setPreferredSize(new Dimension(600, 300));

        while (!queue.isEmpty()) {
            Shipment shipment = queue.poll();
            cargoArea.append("ID: "+shipment.getShipmentId()+ " Status: "+ shipment.getDeliveryStatus() + " Destination: "+ shipment.getEndCity().getCityName() + " Delivery Time: "+ shipment.getDeliveryTime() +"\n");
        }

        cargoArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(cargoArea);

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    Comparator<Shipment> byDeliveryTime = new Comparator<Shipment>() {
        @Override
        public int compare(Shipment s1, Shipment s2) {
            return Integer.compare(s1.getDeliveryTime(), s2.getDeliveryTime());
        }
    };

}
