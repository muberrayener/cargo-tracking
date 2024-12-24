package cargotrackingsys.Helpers;

import cargotrackingsys.Models.Shipment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class SortingAndSearching {

    public static Shipment binarySearch(ArrayList<Shipment> shipments, int shipmentId) {
        int left = 0;
        int right = shipments.size() - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            Shipment midShipment = shipments.get(mid);


            if (midShipment.getShipmentId() == shipmentId) {
                return midShipment;
            } else if (midShipment.getShipmentId() < shipmentId) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return null;
    }

    public static Shipment findShipmentById(ArrayList<Shipment> shipments, int shipmentId) {
        Collections.sort(shipments, new Comparator<Shipment>() {
            @Override
            public int compare(Shipment s1, Shipment s2) {
                return Integer.compare(s1.getShipmentId(), s2.getShipmentId());
            }
        });

        return binarySearch(shipments, shipmentId);
    }

    public static void mergeSort(ArrayList<Shipment> shipments) {
        if (shipments.size() <= 1) return;

        int mid = shipments.size() / 2;

        ArrayList<Shipment> left = new ArrayList<>(shipments.subList(0, mid));
        ArrayList<Shipment> right = new ArrayList<>(shipments.subList(mid, shipments.size()));

        mergeSort(left);
        mergeSort(right);

        merge(shipments, left, right);
    }

    private static void merge(ArrayList<Shipment> shipments, ArrayList<Shipment> left, ArrayList<Shipment> right) {
        int i = 0, j = 0, k = 0;

        while (i < left.size() && j < right.size()) {
            if (left.get(i).getDeliveryTime() < right.get(j).getDeliveryTime()) {
                shipments.set(k++, left.get(i++));
            } else {
                shipments.set(k++, right.get(j++));
            }
        }

        while (i < left.size()) {
            shipments.set(k++, left.get(i++));
        }

        while (j < right.size()) {
            shipments.set(k++, right.get(j++));
        }
    }

}