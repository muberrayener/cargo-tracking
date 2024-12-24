
package cargotrackingsys.Views;

import cargotrackingsys.MainScreen;
import cargotrackingsys.Models.City;
import cargotrackingsys.Models.Customer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.ZoneId;
import java.util.Date;
import java.util.LinkedList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RouteView {

    private JTextArea routeArea;
    private JFrame frame;
    private JButton backButton;
    private LinkedList<Customer> customerList ;
    public RouteView(LinkedList<Customer> customerList) {
        this.customerList = customerList;
        frame = new JFrame("Route");
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2));  // 3 rows, 2 columns

        frame.add(inputPanel, BorderLayout.NORTH);
        backButton = new JButton("Back to Main Screen");
        inputPanel.add(backButton);
        routeArea = new JTextArea(City.DrawTree());
        JScrollPane scrollPane = new JScrollPane(routeArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new GridLayout(1, 2));


        frame.add(detailsPanel, BorderLayout.SOUTH);

        frame.setVisible(false);

        addButtonFunctionality();
    }

    public void setVisibility(boolean b) {
        frame.setVisible(b);
    }

    private void addButtonFunctionality() {
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);

                new MainScreen(customerList);
            }
        });
    }
}
