package org.example.view;

import org.example.controller.MenuController;
import org.example.controller.OrderController;
import org.example.model.MenuItem;
import org.example.model.Order;
import org.example.model.OrderItem;
import org.example.model.OrderStatus;
import org.example.model.OrderType;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class OrderDetailsFrame extends JFrame {

    private JTextField addressField;
    private JTextField priceField;
    private JTextField tipField;
    private JComboBox<OrderType> typeComboBox;
    private List<OrderItem> orderItems;

    private Order order;

    public OrderDetailsFrame(Order order) {
        this.order = order;
        this.orderItems=order.getOrderItems();
        initializeFrame();
    }

    private void initializeFrame() {
        setTitle("Edit Order Details");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(Color.WHITE);

        // Form Panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(6, 2, 10, 10));
        formPanel.setBackground(Color.WHITE);

        // Address Field
        formPanel.add(new JLabel("Address:"));
        addressField = new JTextField(order.getAddress() != null ? order.getAddress() : "");
        formPanel.add(addressField);

        // Price Field
        formPanel.add(new JLabel("Price:"));
        priceField = new JTextField(String.valueOf(order.getPrice()));
        formPanel.add(priceField);

        // Tip Field
        formPanel.add(new JLabel("Tip:"));
        tipField = new JTextField(String.valueOf(order.getTip()));
        formPanel.add(tipField);

        // Order Type
        formPanel.add(new JLabel("Order Type:"));
        typeComboBox = new JComboBox<>(OrderType.values());
        typeComboBox.setSelectedItem(order.getOrderType());
        formPanel.add(typeComboBox);

        // Order Items (Non-Editable Display)
        formPanel.add(new JLabel("Order Items:"));
        JTextArea orderItemsArea = new JTextArea();
        orderItemsArea.setText(formatOrderItems(order.getOrderItems()));
        orderItemsArea.setLineWrap(true);
        orderItemsArea.setWrapStyleWord(true);
        orderItemsArea.setEditable(false);
        JScrollPane itemsScrollPane = new JScrollPane(orderItemsArea);
        itemsScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        itemsScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        formPanel.add(itemsScrollPane);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(Color.WHITE);

        JButton saveButton = new JButton("Save");
        saveButton.setPreferredSize(new Dimension(100, 30));
        saveButton.addActionListener(e -> {
            saveChanges();
            ClientPanel.setNewOrder(false);
        });
        buttonPanel.add(saveButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setPreferredSize(new Dimension(100, 30));
        cancelButton.addActionListener(e -> dispose());
        buttonPanel.add(cancelButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private String formatOrderItems(List<OrderItem> orderItems) {
        StringBuilder sb = new StringBuilder();
        for (OrderItem item : orderItems) {
            sb.append("Item ID: ").append(item.getItemID())
                    .append(", Quantity: ").append(item.getQuantity())
                    .append("\n");
        }
        return sb.toString();
    }

    private void saveChanges() {
        try {
            order.setAddress(addressField.getText());
            order.setPrice(Double.parseDouble(priceField.getText()));
            order.setTip(Double.parseDouble(tipField.getText()));
            order.setOrderType((OrderType) typeComboBox.getSelectedItem());
            order.setOrderItems(orderItems);
            OrderController.addOrderToFile(order);
            order.getUser().setNumOfOrders(order.getUser().getNumOfOrders()+1);
            for(OrderItem OI : order.getOrderItems()){
                MenuItem MI = MenuController.getMenuItemFromID(OI.getItemID());
                MI.setNumOfOrders(MI.getNumOfOrders()+OI.getQuantity());
            }
            order.getOrderItems().clear();
            JOptionPane.showMessageDialog(this, "Order updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Failed to save changes. Please check your inputs.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
