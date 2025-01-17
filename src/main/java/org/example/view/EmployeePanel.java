package org.example.view;

import org.example.controller.MenuController;
import org.example.controller.OrderController;
import org.example.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

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
            OrderController.updateOrderStatus(order.getID(), String.valueOf(OrderStatus.PENDING));
            for(MenuFrame mf : RestaurantGreetingFrame.getMfs()){
                if(mf.getUser().getUserType() == UserType.EMPLOYEE || Objects.equals(mf.getUser().getUserName(), order.getUser().getUserName()))
                    mf.reload();
            }
        });
        btnBeingPrepared.addActionListener(e -> {
            OrderController.updateOrderStatus(order.getID(), String.valueOf(OrderStatus.BEING_PREPARED));
            for(MenuFrame mf : RestaurantGreetingFrame.getMfs()){
                if(mf.getUser().getUserType() == UserType.EMPLOYEE || Objects.equals(mf.getUser().getUserName(), order.getUser().getUserName()))
                    mf.reload();
            }
        });
        btnOnTheWay.addActionListener(e -> {
            OrderController.updateOrderStatus(order.getID(), String.valueOf(OrderStatus.ON_THE_WAY));
            for(MenuFrame mf : RestaurantGreetingFrame.getMfs()){
                if(mf.getUser().getUserType() == UserType.EMPLOYEE || Objects.equals(mf.getUser().getUserName(), order.getUser().getUserName()))
                    mf.reload();
            }
        });
        btnArrived.addActionListener(e -> {
            OrderController.updateOrderStatus(order.getID(), String.valueOf(OrderStatus.ARRIVED));
            for(MenuFrame mf : RestaurantGreetingFrame.getMfs()){
                if(mf.getUser().getUserType() == UserType.EMPLOYEE || Objects.equals(mf.getUser().getUserName(), order.getUser().getUserName()))
                    mf.reload();
            }
        });
        btnDone.addActionListener(e -> {
            OrderController.updateOrderStatus(order.getID(), String.valueOf(OrderStatus.DONE));
            for(MenuFrame mf : RestaurantGreetingFrame.getMfs()){
                if(mf.getUser().getUserType() == UserType.EMPLOYEE || Objects.equals(mf.getUser().getUserName(), order.getUser().getUserName()))
                    mf.reload();
            }
        });

        btnPanel.add(btnPending);
        btnPanel.add(btnBeingPrepared);

        if (order.getOrderType() == OrderType.delivery) {

            btnPanel.add(btnOnTheWay);
            btnPanel.add(btnArrived);

        } else if (order.getOrderType() == OrderType.dineIn) {

            btnPanel.add(btnDone);
        }
        if(order.getOrderStatus()==OrderStatus.PENDING){
            btnPending.setSelected(true);
        }
        if(order.getOrderStatus()==OrderStatus.DONE){
            btnDone.setSelected(true);
        }
        if(order.getOrderStatus()==OrderStatus.ARRIVED){
            btnArrived.setSelected(true);
        }
        if(order.getOrderStatus()==OrderStatus.ON_THE_WAY){
            btnOnTheWay.setSelected(true);
        }
        if(order.getOrderStatus()==OrderStatus.BEING_PREPARED){
            btnBeingPrepared.setSelected(true);
        }
        orderPanel.add(btnPanel, gbc);

        // Border for better appearance
        orderPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        return orderPanel;
    }
}
