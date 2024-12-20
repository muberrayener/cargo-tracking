package cargotrackingsys.Views;

import cargotrackingsys.Models.Customer;
import javax.swing.*;
import java.awt.*;

public class CustomerRenderer extends JLabel implements ListCellRenderer<Customer> {
    @Override
    public Component getListCellRendererComponent(JList<? extends Customer> list, Customer customer, int index, boolean isSelected, boolean cellHasFocus) {
        setText(customer.getName() + " (ID: " + customer.getCustomerId() + ")");
        setBackground(isSelected ? Color.LIGHT_GRAY : Color.WHITE);
        setOpaque(true);
        return this;
    }
}