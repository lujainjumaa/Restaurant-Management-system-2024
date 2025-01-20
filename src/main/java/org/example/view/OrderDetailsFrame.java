package org.example.view;

import org.example.controller.MenuController;
import org.example.controller.OrderController;
import org.example.controller.UserController;
import org.example.model.*;
import org.example.model.MenuItem;
import org.example.model.Order;
import org.example.model.OrderItem;
import org.example.model.OrderStatus;
import org.example.model.OrderType;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Date;
import java.util.List;


public class OrderDetailsFrame extends JFrame {

    private JTextField addressField;
    private JLabel priceLabel;
    private JTextField tipField;
    private JComboBox<OrderType> typeComboBox;
    private List<OrderItem> orderItems;
    private MenuFrame mf;
    private Order order;
    private ClientPanel cp;

    public OrderDetailsFrame(Order order, MenuFrame mf, ClientPanel cp) {
        this.cp = cp;
        this.order = order;
        this.orderItems=order.getOrderItems();
        this.mf=mf;
        initializeFrame(cp);
        setVisible(true);
    }

    private void initializeFrame(ClientPanel cp) {
        setTitle("Complete Your Order");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JToggleButton btnDelivery = createToggleButton("DELIVERY");
        JToggleButton btnDineIn = createToggleButton("DINE IN");

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(btnDelivery);
        buttonGroup.add(btnDineIn);

        btnPanel.add(btnDelivery);
        btnPanel.add(btnDineIn);
        JPanel addressPanel = createAddressPanel();
        addressPanel.setVisible(false);

        btnDelivery.addActionListener(e -> {
            addressPanel.setVisible(true);
            order.setOrderType(OrderType.delivery);
    });
        btnDineIn.addActionListener(e -> {
            addressPanel.setVisible(false);
            order.setOrderType(OrderType.dineIn);
        });

        JPanel tipPanel = createTipPanel();

        JPanel pricePanel = createPricePanel();

        JPanel buttonPanel = createPlaceOrderButton();

        mainPanel.add(btnPanel);
        mainPanel.add(addressPanel);
        mainPanel.add(tipPanel);
        mainPanel.add(pricePanel);
        mainPanel.add(buttonPanel);

        add(mainPanel);
    }
    private JPanel createAddressPanel() {
        JPanel addressPanel = new JPanel();
        addressPanel.setBorder(new EmptyBorder(5, 0, 5, 0));

        addressField = new JTextField("Enter your address...");
        addressField.setPreferredSize(new Dimension(350, 40));
        addressField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(UI.orangeColor, 1),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));

        addressField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (addressField.getText().equals("Enter your address...")) {
                    addressField.setText("");
                    addressField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (addressField.getText().isEmpty()) {
                    addressField.setForeground(Color.GRAY);
                    addressField.setText("Enter your address...");
                }
            }
        });
        addressField.setForeground(Color.GRAY);

        addressPanel.add(addressField);
        return addressPanel;
    }

    private JPanel createTipPanel() {
        JPanel tipPanel = new JPanel();
        tipPanel.setBorder(new EmptyBorder(5, 0, 5, 0));

        JLabel tipLabel = new JLabel("Tip:");
        tipField = new JTextField();
        tipField.setPreferredSize(new Dimension(270, 25));
        tipField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(UI.orangeColor, 1),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));

        tipPanel.add(tipLabel);
        tipPanel.add(tipField);
        return tipPanel;
    }

    private JPanel createPricePanel() {
        JPanel pricePanel = new JPanel();
        pricePanel.setBorder(new EmptyBorder(5, 0, 5, 0));

        JLabel priceLabel = new JLabel("                Your Total: $" + order.getPrice() + "                      ");
        priceLabel.setFont(new Font("Arial", Font.BOLD, 16));
        priceLabel.setForeground(new Color(51, 51, 51));

        pricePanel.add(priceLabel);
        return pricePanel;
    }

    private JPanel createPlaceOrderButton() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(new EmptyBorder(10, 0, 0, 0));

        JButton placeOrderButton = new JButton("PLACE ORDER");
        placeOrderButton.setFont(new Font("Arial", Font.BOLD, 14));
        placeOrderButton.setBackground(UI.orangeColor);
        placeOrderButton.setForeground(Color.WHITE);
        placeOrderButton.setFocusPainted(false);
        placeOrderButton.setPreferredSize(new Dimension(150, 40));

        placeOrderButton.addActionListener(e -> {
            placeOrder();
            cp.setNewOrder(false);
        });

        buttonPanel.add(placeOrderButton);
        return buttonPanel;
    }

    private static JToggleButton createToggleButton(String text) {
        JToggleButton button = new JToggleButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.setBackground(new Color(245, 245, 245));
        button.setForeground(UI.orangeColor);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(UI.orangeColor, 2));
        button.setPreferredSize(new Dimension(150, 40));
        return button;
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

    private void placeOrder() {
        try {
            if(order.getOrderType().equals(OrderType.delivery)){
                order.setAddress(addressField.getText());
            }
//            order.setPrice(Double.parseDouble(priceField.getText()));
            order.setTip(Double.parseDouble(tipField.getText()));
//            order.setOrderType((OrderType) typeComboBox.getSelectedItem());
            order.setOrderItems(orderItems);
            order.setOrderStatus(OrderStatus.PENDING);
            order.setOrderDate(new Date());
            Order.setHighestID(Order.getHighestID()+1);
            order.setID(Order.getHighestID());
            OrderController.addOrderToFile(order,FilePath.getOrders());
            if(!OrderController.getDateDailyOrder().equals(Order.formatToLocalDate(new Date()))){
                OrderController.clearFileContent(FilePath.getDailyOrders());
                OrderController.addDateToDailyOrder();
            }
            OrderController.addOrderToFile(order,FilePath.getDailyOrders());

            order.getUser().setNumOfOrders(order.getUser().getNumOfOrders()+1);
            for(OrderItem OI : order.getOrderItems()){
                MenuItem MI = MenuController.getMenuItemFromID(OI.getItemID());
                MI.setNumOfOrders(MI.getNumOfOrders()+OI.getQuantity());
                System.out.println("--->"+MI.getNumOfOrders());
                MenuController.deleteMenuItemFromFile(OI.getItemID(), mf,true);
                MenuController.addMenuItemToFile(MenuController.getMenuItemFromID(OI.getItemID()), mf);
            }
            for(MenuFrame mf : RestaurantGreetingFrame.getMfs()){
                if(mf.getUser().getUserType() == UserType.EMPLOYEE)
                    mf.reload();
            }
            order.getOrderItems().clear();
            mf.reload();
            UserController.removeUserFromFile(mf.getUser().getUserName());
            UserController.addUserToFile(mf.getUser());
            JOptionPane.showMessageDialog(this, "Order updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } catch (Exception e) {
            e.getStackTrace();
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(this, "Failed to save changes. Please check your inputs.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
