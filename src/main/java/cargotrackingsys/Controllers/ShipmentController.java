package cargotrackingsys.Controllers;

import cargotrackingsys.Models.Shipment;
import cargotrackingsys.Views.CargoView;

public class ShipmentController {

    private Shipment shipment;
    private CargoView view;

    public ShipmentController(Shipment shipment, CargoView view) {
        this.shipment = shipment;
        this.view = view;
    }

    public void updateShipmentStatus(int shipmentId, String status) {
        shipment.setShipmentId(shipmentId);
        shipment.setDeliveryStatus(status);
        view.displayShipmentInfo(shipment.getShipmentId(),shipment.getStatus()); // Update the view
    }
}
