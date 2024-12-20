package cargotrackingsys.Helpers;

import cargotrackingsys.Models.Shipment;

import java.util.Arrays;

class SortingAndSearching {

    // Binary Search for completed shipments
    public static Shipment binarySearch(Shipment[] shipments, int shipmentId) {
        int left = 0, right = shipments.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (shipments[mid].getShipmentId() == shipmentId) {
                return shipments[mid];
            } else if (shipments[mid].getShipmentId() < shipmentId) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null; // Not found
    }

    // Merge Sort for undelivered shipments based on delivery time
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