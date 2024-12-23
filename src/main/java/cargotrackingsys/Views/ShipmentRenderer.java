package cargotrackingsys.Views;

import cargotrackingsys.Models.Shipment;

import javax.swing.*;
import java.awt.*;

public class ShipmentRenderer extends JLabel implements ListCellRenderer<Shipment> {
    @Override
    public Component getListCellRendererComponent(JList<? extends Shipment> list, Shipment shipment, int index, boolean isSelected, boolean cellHasFocus) {
        setText(" (ID: " + shipment.getShipmentId() + ")");
        setBackground(isSelected ? Color.LIGHT_GRAY : Color.WHITE);
        setOpaque(true);
        return this;
    }
}