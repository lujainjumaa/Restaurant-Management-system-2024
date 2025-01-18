package org.example.view;

import org.example.controller.MenuController;
import org.example.controller.OrderController;
import org.example.model.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Objects;

import static javax.swing.BorderFactory.createLineBorder;

public class EmployeePanel extends JPanel {
    public EmployeePanel() throws ItemNotFoundException {
        OrderController.loadDailyOrders();

        // Use a vertical box layout for orders
        setLayout(new BorderLayout());
        JPanel ordersPanel = new JPanel();
        ordersPanel.setLayout(new BoxLayout(ordersPanel, BoxLayout.Y_AXIS));

        for (Order order : OrderController.getDailyOrders()) {
            if(order.getOrderStatus().equals(OrderStatus.DONE) || order.getOrderStatus().equals(OrderStatus.ARRIVED)){
                continue;
            }
            ordersPanel.add(createOrderPanel(order));
            ordersPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add spacing between orders

        }
        ordersPanel.setBorder(createLineBorder(new Color(126, 127, 131), 4));
        JScrollPane scrollPane = new JScrollPane(ordersPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane, BorderLayout.CENTER);
        setVisible(true);
    }

    public static JPanel createOrderPanel(Order order) throws ItemNotFoundException {
        JPanel orderPanel = new JPanel(new GridBagLayout());
        orderPanel.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(20,40,0,40),createLineBorder(Color.WHITE,5))); // Add border for clarity

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Padding for elements
        gbc.fill = GridBagConstraints.BOTH; // Fill the available space

        // Username column
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.2;
        JLabel usernameLabel = new JLabel(order.getUser().getUserName());
        usernameLabel.setFont(UI.getNameFont());
        usernameLabel.setHorizontalAlignment(SwingConstants.LEFT);
        orderPanel.add(usernameLabel, gbc);

        // Order items column
        gbc.gridx = 1;
        gbc.weightx = 0.5;
        JPanel itemsPanel = new JPanel();
        itemsPanel.setLayout(new BoxLayout(itemsPanel, BoxLayout.Y_AXIS));
        for (OrderItem item : order.getOrderItems()) {
            JLabel orderItemLabel = new JLabel(item.getQuantity() + " -> " + MenuController.getMenuItemFromID(item.getItemID()).getName());
            orderItemLabel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 15));
            itemsPanel.add(orderItemLabel);
        }
        orderPanel.add(itemsPanel, gbc);

        // Buttons column
        gbc.gridx = 2;
        gbc.weightx = 0.3;
        JPanel btnPanel = createButtonsPanel(order);
        orderPanel.add(btnPanel, gbc);

        return orderPanel;
    }

    private static JPanel createButtonsPanel(Order order) {
        JPanel btnPanel = new JPanel(new GridLayout(0, 1, 5, 5)); // Buttons stacked vertically
        ButtonGroup buttonGroup = new ButtonGroup();

        // Create buttons with consistent sizes
        JToggleButton btnPending = createToggleButton("PENDING");
        JToggleButton btnBeingPrepared = createToggleButton("BEING PREPARED");
        JToggleButton btnOnTheWay = createToggleButton("ON THE WAY");
        JToggleButton btnArrived = createToggleButton("ARRIVED");
        JToggleButton btnDone = createToggleButton("DONE");

        // Add buttons to the panel and group
        buttonGroup.add(btnPending);
        btnPanel.add(btnPending);

        buttonGroup.add(btnBeingPrepared);
        btnPanel.add(btnBeingPrepared);

        if (order.getOrderType() == OrderType.delivery) {
            buttonGroup.add(btnOnTheWay);
            btnPanel.add(btnOnTheWay);

            buttonGroup.add(btnArrived);
            btnPanel.add(btnArrived);
        } else if (order.getOrderType() == OrderType.dineIn) {
            buttonGroup.add(btnDone);
            btnPanel.add(btnDone);
        }

        // Set initial selection
        selectInitialStatusButton(order, btnPending, btnBeingPrepared, btnOnTheWay, btnArrived, btnDone);

        // Add action listeners for buttons
        addStatusButtonListeners(order, btnPending, btnBeingPrepared, btnOnTheWay, btnArrived, btnDone);

        return btnPanel;
    }

    private static JToggleButton createToggleButton(String text) {
        JToggleButton button = new JToggleButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 12));
        button.setBackground(new Color(255, 250, 255)); // Default background
        button.setForeground(new Color(215, 81, 132)); // Default text color
        button.setFocusPainted(false);
        button.setBorder(createLineBorder(new Color(215, 81, 132), 2, false));
        button.setPreferredSize(new Dimension(150, 30)); // Uniform size
        return button;
    }

    private static void selectInitialStatusButton(Order order, JToggleButton btnPending, JToggleButton btnBeingPrepared, JToggleButton btnOnTheWay, JToggleButton btnArrived, JToggleButton btnDone) {
        if (order.getOrderStatus() == OrderStatus.PENDING) btnPending.setSelected(true);
        if (order.getOrderStatus() == OrderStatus.BEING_PREPARED) btnBeingPrepared.setSelected(true);
        if (order.getOrderStatus() == OrderStatus.ON_THE_WAY) btnOnTheWay.setSelected(true);
        if (order.getOrderStatus() == OrderStatus.ARRIVED) btnArrived.setSelected(true);
        if (order.getOrderStatus() == OrderStatus.DONE) btnDone.setSelected(true);
    }

    private static void addStatusButtonListeners(Order order, JToggleButton... buttons) {
        for (JToggleButton button : buttons) {
            button.addActionListener(e -> {
                OrderStatus status = OrderStatus.valueOf(button.getText().replace(" ", "_").toUpperCase());
                OrderController.updateOrderStatus(order.getID(), String.valueOf(status));
                for (MenuFrame mf : RestaurantGreetingFrame.getMfs()) {
                    if (mf.getUser().getUserType() == UserType.EMPLOYEE || Objects.equals(mf.getUser().getUserName(), order.getUser().getUserName()))
                        mf.reload();
                }
            });
        }
    }
}
