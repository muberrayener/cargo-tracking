package cargotrackingsys;

import cargotrackingsys.Controllers.MainController;
import cargotrackingsys.Models.Customer;
import cargotrackingsys.Models.Shipment;
import cargotrackingsys.Views.CargoView;
import cargotrackingsys.Views.CustomerView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.LinkedList;

public class MainScreen {

    private JFrame mainFrame;
    private JButton openCustomerViewButton;
    private JButton openCargoViewButton;
    private LinkedList<Customer> customerList;

    public MainScreen(LinkedList<Customer> customerList) {
        // Initialize the main frame
        this.customerList = customerList;
        mainFrame = new JFrame("Main Screen");
        mainFrame.setSize(400, 300);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(null);  // No layout manager for custom placement

        // Create and add the buttons to open CustomerView and CargoView
        openCustomerViewButton = new JButton("Open Customer View");
        openCustomerViewButton.setBounds(100, 100, 200, 50);  // Position and size of button
        mainFrame.add(openCustomerViewButton);

        openCargoViewButton = new JButton("Open Cargo View");
        openCargoViewButton.setBounds(100, 170, 200, 50);  // Position and size of button
        mainFrame.add(openCargoViewButton);

        // Action listener for the button to open CustomerView
        openCustomerViewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openCustomerView();
            }
        });

        // Action listener for the button to open CargoView
        openCargoViewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openCargoView();
            }
        });

        // Make the frame visible
        mainFrame.setVisible(true);

    }
    public static void main(String[] args) {
        // Initialize the customer list
        LinkedList<Customer> customerList = new LinkedList<>();
        Shipment shipment = new Shipment(101, new Date(),"In Transit",1);  // Example shipment
        CargoView cargoView = new CargoView(customerList);
        CustomerView customerView = new CustomerView(customerList);
        // Initialize views
        MainScreen mainScreen = new MainScreen(customerList);
        MainController controller = new MainController(customerList, shipment, customerView, cargoView);

    }

    // Method to open the CustomerView screen
    private void openCustomerView() {
        CustomerView customerView = new CustomerView(this.customerList);
        customerView.setVisibility(true);
        mainFrame.setVisible(false);  // Hide main frame when opening CustomerView
    }

    // Method to open the CargoView screen
    private void openCargoView() {
        CargoView cargoView = new CargoView(this.customerList);
        cargoView.setVisibility(true);
        mainFrame.setVisible(false);  // Hide main frame when opening CargoView
    }
    // Run the main screen when the app starts

}