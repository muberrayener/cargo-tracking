package cargotrackingsys.Views;

import cargotrackingsys.MainScreen;
import cargotrackingsys.Models.City;
import cargotrackingsys.Models.Customer;
import cargotrackingsys.Models.Shipment;

import java.time.LocalDateTime;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.ZoneId;
import java.util.Date;
import java.util.LinkedList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomerView {
    private JFrame frame;
    private JTextField nameField;
    private JTextField rCityField;
    private JTextField sCityField;
    private JList<Customer> customerListArea;
    private JButton addCustomerButton;
    private JButton backButton;
    private JButton viewDetailsButton;
    private LinkedList<Customer> customerList;
    private DefaultListModel<Customer> customerListModel;

    public CustomerView(LinkedList<Customer> customerList) {
        this.customerList = customerList;
        customerListModel = new DefaultListModel<>();
        updateCustomerList(this.customerList);
        frame = new JFrame("Customer Management");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2));  // 3 rows, 2 columns

        inputPanel.add(new JLabel("Full Name (First Last):"));
        nameField = new JTextField();
        inputPanel.add(nameField);



        addCustomerButton = new JButton("Add Customer");
        inputPanel.add(addCustomerButton);

        backButton = new JButton("Back to Main Screen");
        inputPanel.add(backButton);

        frame.add(inputPanel, BorderLayout.NORTH);

        customerListArea = new JList<>(customerListModel);
        customerListArea.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        customerListArea.setVisibleRowCount(20);
        customerListArea.setCellRenderer(new CustomerRenderer());

        JScrollPane scrollPane = new JScrollPane(customerListArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new GridLayout(1, 2));

        viewDetailsButton = new JButton("View Details");
        detailsPanel.add(viewDetailsButton);

        frame.add(detailsPanel, BorderLayout.SOUTH);

        frame.setVisible(false);

        addButtonFunctionality();
        addListSelectionListener();
    }

    public void updateCustomerList(LinkedList<Customer> customers) {
        customerListModel.clear();
        for (Customer customer : customers) {
            customerListModel.addElement(customer);
        }
    }

    private void addButtonFunctionality() {
        addCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int customerId = customerList.size()+1;
                    String name = nameField.getText();

                    if (name.trim().isEmpty() || customerId <= 0) {
                        JOptionPane.showMessageDialog(frame, "Please enter valid customer details.");
                        return;
                    }

                    Customer newCustomer = new Customer(customerId, name.split(" ")[0], name.split(" ")[1]);

                    customerList.add(newCustomer);

                    updateCustomerList(customerList);

                    nameField.setText("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Customer ID must be a valid number.");
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);

                new MainScreen(customerList);
            }
        });

        viewDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Customer selectedCustomer = getSelectedCustomer();
                if (selectedCustomer != null) {
                    new CustomerDetailsView(selectedCustomer);
                } else {
                    JOptionPane.showMessageDialog(frame, "Please select a customer from the list.");
                }
            }
        });

    }

    private void addListSelectionListener() {
        customerListArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = customerListArea.locationToIndex(e.getPoint());
                if (index >= 0) {
                    Customer selectedCustomer = customerListArea.getModel().getElementAt(index);
                    displayCustomerDetails(selectedCustomer);
                }
            }
        });
    }

    public JButton getAddCustomerButton() {
        return addCustomerButton;
    }


    public void displayCustomerDetails(Customer customer) {
        nameField.setText(customer.getName());
    }

    public JTextField getNameField() {
        return nameField;
    }

    public JList<Customer> getCustomerListArea() {
        return customerListArea;
    }

    public void clearInputFields() {
        nameField.setText("");
    }

    public Customer getSelectedCustomer() {
        return customerListArea.getSelectedValue();
    }

    public int getCustomerId() {
        return customerList.size()+1;
    }

    public String getName() {
        return nameField.getText();
    }

    public void setVisibility (boolean visible) {
        frame.setVisible(visible);
    }

    public JButton getViewDetailsButton() {
        return viewDetailsButton;
    }

    public JFrame getFrame() {
            return frame;

    }
}
