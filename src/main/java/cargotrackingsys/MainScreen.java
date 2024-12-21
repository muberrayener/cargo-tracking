package cargotrackingsys;

import cargotrackingsys.Controllers.MainController;
import cargotrackingsys.Models.City;
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
        this.customerList = customerList;
        mainFrame = new JFrame("Main Screen");
        mainFrame.setSize(400, 300);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(null);

        openCustomerViewButton = new JButton("Open Customer View");
        openCustomerViewButton.setBounds(100, 100, 200, 50);
        mainFrame.add(openCustomerViewButton);

        openCargoViewButton = new JButton("Open Cargo View");
        openCargoViewButton.setBounds(100, 170, 200, 50);
        mainFrame.add(openCargoViewButton);

        openCustomerViewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openCustomerView();
            }
        });

        openCargoViewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openCargoView();
            }
        });

        mainFrame.setVisible(true);

    }
    public static void main(String[] args) {
        LinkedList<Customer> customerList = new LinkedList<>();
        Shipment shipment = new Shipment(101, new Date(),"In Transit",1);
        CargoView cargoView = new CargoView(customerList);
        CustomerView customerView = new CustomerView(customerList);
        MainScreen mainScreen = new MainScreen(customerList);
        MainController controller = new MainController(customerList, shipment, customerView, cargoView);
        City.DrawTree();
    }

    private void openCustomerView() {
        CustomerView customerView = new CustomerView(this.customerList);
        customerView.setVisibility(true);
        mainFrame.setVisible(false);
    }

    private void openCargoView() {
        CargoView cargoView = new CargoView(this.customerList);
        cargoView.setVisibility(true);
    }

}