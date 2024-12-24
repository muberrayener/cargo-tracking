package cargotrackingsys;

import cargotrackingsys.Models.City;
import cargotrackingsys.Models.Customer;
import cargotrackingsys.Models.Shipment;
import cargotrackingsys.Views.CargoView;
import cargotrackingsys.Views.CustomerView;
import cargotrackingsys.Views.RouteView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.LinkedList;

public class MainScreen {

    private JFrame mainFrame;
    private JButton openCustomerViewButton;
    private JButton openRouteViewButton;
    private JButton openCargoViewButton;
    private LinkedList<Customer> customerList;

    public MainScreen(LinkedList<Customer> customerList) {
        this.customerList = customerList;
        mainFrame = new JFrame("Main Screen");
        mainFrame.setSize(600, 400);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(null);

        openCustomerViewButton = new JButton("Open Customer View");
        openCustomerViewButton.setBounds(100, 100, 200, 50);
        mainFrame.add(openCustomerViewButton);

        openCargoViewButton = new JButton("Open Cargo View");
        openCargoViewButton.setBounds(100, 170, 200, 50);
        mainFrame.add(openCargoViewButton);

        openRouteViewButton = new JButton("Open Route View");
        openRouteViewButton.setBounds(100, 240, 200, 50);
        mainFrame.add(openRouteViewButton);

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

        openRouteViewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openRouteView();
            }
        });

        mainFrame.setVisible(true);

    }
    public static void main(String[] args) {
        LinkedList<Customer> customerList = new LinkedList<>();
        CargoView cargoView = new CargoView(customerList);
        CustomerView customerView = new CustomerView(customerList);
        MainScreen mainScreen = new MainScreen(customerList);
        System.out.println(City.DrawTree());

    }

    private void openCustomerView() {
        CustomerView customerView = new CustomerView(this.customerList);
        customerView.setVisibility(true);
        mainFrame.setVisible(false);
    }

    private void openCargoView() {
        CargoView cargoView = new CargoView(this.customerList);
        cargoView.setVisibility(true);
        mainFrame.setVisible(false);
    }

    private void openRouteView() {
        RouteView routeView = new RouteView(this.customerList);
        routeView.setVisibility(true);
        mainFrame.setVisible(false);
    }

}