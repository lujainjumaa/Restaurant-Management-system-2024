package org.example.view;

import org.example.controller.MenuController;
import org.example.controller.OrderController;
import org.example.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeePanel extends JPanel {
    public EmployeePanel() throws ItemNotFoundException {
        OrderController.loadDailyOrders();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Arrange orders vertically
        for (Order order : OrderController.getDailyOrders()) {
            add(createOrderPanel(order));
            add(Box.createRigidArea(new Dimension(0, 10))); // Add spacing between orders
        }
        setVisible(true);
    }

    public static JPanel createOrderPanel(Order order) throws ItemNotFoundException {
        JPanel orderPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Padding for elements

        // Username column
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JLabel usernameLabel = new JLabel(order.getUser().getUserName());
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        usernameLabel.setHorizontalAlignment(SwingConstants.LEFT);
        orderPanel.add(usernameLabel, gbc);

        // Order items column
        gbc.gridx = 1;
        gbc.weightx = 0.5;
        JPanel itemsPanel = new JPanel();
        itemsPanel.setLayout(new BoxLayout(itemsPanel, BoxLayout.Y_AXIS));
        for (OrderItem item : order.getOrderItems()) {
            itemsPanel.add(new JLabel(item.getQuantity() + " -> " + MenuController.getMenuItemFromID(item.getItemID()).getName()));
        }
        orderPanel.add(itemsPanel, gbc);

        // Status combo box column
        gbc.gridx = 2;
        gbc.weightx = 0.3;

        JPanel btnPanel = new JPanel();
        JToggleButton btnPending = new JToggleButton("PENDING");
        JToggleButton btnBeingPrepared = new JToggleButton("BEING PREPARED");
        JToggleButton btnOnTheWay = new JToggleButton("On The Way");
        JToggleButton btnArrived = new JToggleButton("Arrived");
        JToggleButton btnDone = new JToggleButton("Done");

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(btnPending);
        buttonGroup.add(btnBeingPrepared);
        buttonGroup.add(btnOnTheWay);
        buttonGroup.add(btnArrived);
        buttonGroup.add(btnDone);

        btnPending.addActionListener(e -> {

        });

        btnBeingPrepared.addActionListener(e -> {

        });
        btnOnTheWay.addActionListener(e -> {

        });

        btnArrived.addActionListener(e -> {

        });
        btnDone.addActionListener(e -> {

        });

        btnPanel.add(btnPending);
        btnPanel.add(btnBeingPrepared);

        if (order.getOrderType() == OrderType.delivery) {

            btnPanel.add(btnOnTheWay);
            btnPanel.add(btnArrived);

        } else if (order.getOrderType() == OrderType.dineIn) {

            btnPanel.add(btnDone);
        }
        btnPending.setSelected(true);
        orderPanel.add(btnPanel, gbc);

        // Border for better appearance
        orderPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        return orderPanel;
    }
}
