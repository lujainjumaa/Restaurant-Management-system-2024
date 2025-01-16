package org.example.view;

import org.example.controller.MenuController;
import org.example.controller.OrderController;
import org.example.model.*;

import javax.swing.*;
import java.awt.*;

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
        String[] statuses = getStatusesForOrderType(order.getOrderType());
        JComboBox<String> statusComboBox = new JComboBox<>(statuses);
        statusComboBox.setSelectedItem(order.getOrderStatus().toString());
        orderPanel.add(statusComboBox, gbc);

        // Border for better appearance
        orderPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        return orderPanel;
    }

    private static String[] getStatusesForOrderType(OrderType type) {
        if (type == OrderType.delivery) {
            return new String[]{"BEING_PREPARED", "ON_THE_WAY", "ARRIVED"};
        } else if (type == OrderType.dineIn) {
            return new String[]{"BEING_PREPARED", "DONE"};
        }
        return new String[]{};
    }
}
