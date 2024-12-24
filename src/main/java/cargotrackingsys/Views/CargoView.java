package cargotrackingsys.Views;

import cargotrackingsys.Helpers.Finders;
import cargotrackingsys.MainScreen;
import cargotrackingsys.Models.City;
import cargotrackingsys.Models.Customer;
import cargotrackingsys.Models.Shipment;

import javax.swing.*;
import java.awt.*;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

public class CargoView {

    private JFrame cargoFrame;
    private JTextField shipmentIdField;
    private JTextField customerIdField;
    private JTextField customerNameField;
    private JComboBox<String> shipmentStatusField;
    private JButton addCargoButton;
    private JButton backButton;
    private JButton findCustomerButton;
    private JList<Shipment> shipmentListArea;
    public LinkedList<Customer> customerList;
    private JComboBox<String> rCityComboBox;
    private JComboBox<String> sCityComboBox;
    private static String[] cities = { "İstanbul", "Ankara", "İzmir", "Antalya", "Bursa", "Adana" };
    private static String[] center = { "Cargo Center"};
    private static String[] status = { "ADMITTED","IN TRANSIT", "DELIVERED"};
    private DefaultListModel<Shipment> shipmentListModel;
    private JButton viewDetailsButton;
    private JButton findCargoButton;

    public CargoView(LinkedList<Customer> customerList) {

        this.customerList = customerList;
        shipmentListModel = new DefaultListModel<>();
        cargoFrame = new JFrame("Cargo Management");
        cargoFrame.setSize(600, 400);
        cargoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cargoFrame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 2));

        panel.add(new JLabel("Customer ID:"));
        customerIdField = new JTextField(15);
        panel.add(customerIdField);

        findCustomerButton = new JButton("Find Customer");
        panel.add(findCustomerButton);
        panel.add(new JLabel(""));

        panel.add(new JLabel("Customer Name Surname:"));
        customerNameField = new JTextField(15);
        panel.add(customerNameField);

        panel.add(new JLabel("Shipment Status:"));
        shipmentStatusField = new JComboBox<>(status);
        panel.add(shipmentStatusField);

        panel.add(new JLabel("Start Location"));
        sCityComboBox = new JComboBox<>(center);
        panel.add(sCityComboBox);

        panel.add(new JLabel("Receiver City"));
        rCityComboBox = new JComboBox<>(cities);
        panel.add(rCityComboBox);

        addCargoButton = new JButton("Add Shipment");
        panel.add(addCargoButton);

        backButton = new JButton("Back to Main Screen");
        panel.add(backButton);

        findCargoButton = new JButton("Find Cargo");
        panel.add(findCargoButton);


        shipmentListArea = new JList<>(shipmentListModel);
        shipmentListArea.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        shipmentListArea.setVisibleRowCount(20);
        shipmentListArea.setCellRenderer(new ShipmentRenderer());
        JScrollPane scrollPane = new JScrollPane(shipmentListArea);
        cargoFrame.add(scrollPane, BorderLayout.CENTER);

        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new GridLayout(1, 2));

        viewDetailsButton = new JButton("View Details");
        detailsPanel.add(viewDetailsButton);

        cargoFrame.add(detailsPanel, BorderLayout.SOUTH);
        cargoFrame.add(panel, BorderLayout.NORTH);
        cargoFrame.setVisible(false);

        addButtonFunctionality();
        addListSelectionListener();
    }

    private void addButtonFunctionality() {

        findCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String customerId = customerIdField.getText();
                Customer customer = Finders.findCustomerById(customerList,Integer.parseInt(customerId));
                if (customer == null) {
                    JOptionPane.showMessageDialog(cargoFrame, "Customer not found.");
                    clearInputFields();
                }
                customerNameField.setText(customer.getName());
                updateShipmentList(customer.getShipmentHistory());

            }
        });
        addCargoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int customerId = getCustomerId();
                Customer customer = Finders.findCustomerById(customerList,customerId);
                if (customer == null) {
                    JOptionPane.showMessageDialog(cargoFrame, "Customer not found.");
                    clearInputFields();
                }
                int shipmentId = customerId*1000+customer.getShipmentHistory().size();
                LocalDateTime localDateTime = LocalDateTime.now();
                Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
                String selectedSenderCity = (String) sCityComboBox.getSelectedItem();
                String selectedReceiverCity = (String) rCityComboBox.getSelectedItem();
                Shipment cargo = new Shipment( shipmentId,date, (String)shipmentStatusField.getSelectedItem(),new City(selectedSenderCity), new City(selectedReceiverCity));
                customer.addShipment(cargo);
                updateShipmentList(customer.getShipmentHistory());
                clearInputFields();
            }
        });

        backButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cargoFrame.setVisible(false);

                new MainScreen(customerList);
            }
        });

        viewDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Shipment selectedShipment = getSelectedShipment();
                if (selectedShipment != null) {
                    new ShipmentDetailsView(selectedShipment);
                } else {
                    JOptionPane.showMessageDialog(cargoFrame, "Please select a customer from the list.");
                }
            }
        });
    }

    public void clearInputFields() {
        customerIdField.setText("");
        customerNameField.setText("");
        shipmentStatusField.setSelectedIndex(0);
        sCityComboBox.setSelectedIndex(0);
        rCityComboBox.setSelectedIndex(0);
    }

    public int getShipmentId() {
        return Integer.parseInt(shipmentIdField.getSelectedText().trim());
    }

    public int getCustomerId() {
        return Integer.parseInt(customerIdField.getText().trim());
    }

    public String getShipmentStatus() {
        return shipmentStatusField.getSelectedItem().toString();
    }

    public JButton getAddCargoButton() {
        return addCargoButton;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public JFrame getCargoFrame() {
        return cargoFrame;
    }

    public String getStatus() {
        return shipmentStatusField.getSelectedItem().toString() ;
    }

    public void setVisibility (boolean visible) {
        cargoFrame.setVisible(visible);
    }

    private void addListSelectionListener() {
        shipmentListArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = shipmentListArea.locationToIndex(e.getPoint());
                if (index >= 0) {
                    Shipment selectedShipment = shipmentListArea.getModel().getElementAt(index);
                }
            }
        });
    }

    public void updateShipmentList(Stack<Shipment> shipments) {
        shipmentListModel.clear();  // Clear the current shipment list in the model

        // If the stack has more than 5 shipments, take the last 5
        int size = shipments.size();
        int startIndex = size > 5 ? size - 5 : 0;  // Ensure we don't start with a negative index

        // Create a temporary list to hold the last 5 shipments (or less if there are fewer than 5)
        ArrayList<Shipment> lastShipments = new ArrayList<>(shipments.subList(startIndex, size));

        // Reverse the order to make the most recent shipment appear first
        Collections.reverse(lastShipments);

        // Add the last 5 shipments to the model (they will be displayed in the correct order)
        for (Shipment shipment : lastShipments) {
            shipmentListModel.addElement(shipment);  // Add the shipment to the list model
        }
    }

    public Shipment getSelectedShipment() {
        return shipmentListArea.getSelectedValue();
    }

}