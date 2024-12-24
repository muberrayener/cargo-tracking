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

            // Compare the shipment ID with the mid element's shipment ID
            if (midShipment.getShipmentId() == shipmentId) {
                return midShipment;  // Return the shipment if found
            } else if (midShipment.getShipmentId() < shipmentId) {
                left = mid + 1;  // Search in the right half
            } else {
                right = mid - 1;  // Search in the left half
            }
        }

        return null;  // Return null if not found
    }

    public static Shipment findShipmentById(ArrayList<Shipment> shipments, int shipmentId) {
        // Sort the shipments by shipmentId
        Collections.sort(shipments, new Comparator<Shipment>() {
            @Override
            public int compare(Shipment s1, Shipment s2) {
                return Integer.compare(s1.getShipmentId(), s2.getShipmentId());
            }
        });

        // Perform binary search to find the shipment by ID
        return binarySearch(shipments, shipmentId);
    }

    public static void mergeSort(Shipment[] shipments) {
        if (shipments.length <= 1) return;

        int mid = shipments.length / 2;
        Shipment[] left = Arrays.copyOfRange(shipments, 0, mid);
        Shipment[] right = Arrays.copyOfRange(shipments, mid, shipments.length);

        mergeSort(left);
        mergeSort(right);

        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i].deliveryTime < right[j].deliveryTime) {
                shipments[k++] = left[i++];
            } else {
                shipments[k++] = right[j++];
            }
        }
        while (i < left.length) {
            shipments[k++] = left[i++];
        }
        while (j < right.length) {
            shipments[k++] = right[j++];
        }
    }
}