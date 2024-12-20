package cargotrackingsys;

import cargotrackingsys.Views.CargoView;
import cargotrackingsys.Views.CustomerView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainScreen {

    private JFrame mainFrame;
    private JButton openCustomerViewButton;
    private JButton openCargoViewButton;

    public MainScreen() {
        // Initialize the main frame
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

    // Method to open the CustomerView screen
    private void openCustomerView() {
        CustomerView customerView = new CustomerView();
        mainFrame.setVisible(false);  // Hide main frame when opening CustomerView
    }

    // Method to open the CargoView screen
    private void openCargoView() {
        CargoView cargoView = new CargoView();
        mainFrame.setVisible(false);  // Hide main frame when opening CargoView
    }

    public static void main(String[] args) {
        new MainScreen();  // Run the main screen when the app starts
    }
}