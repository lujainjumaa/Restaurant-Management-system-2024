package org.example.view;

import org.example.model.ItemNotFoundException;
import org.example.model.Order;
import org.example.model.OrderItem;
import org.example.model.MenuItem;
import org.example.controller.MenuController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.List;

public class CartPanel extends JPanel {
    private List<OrderItem> orderItems;
    private JPanel itemsPanel;
    private JLabel totalLabel;
    private double totalAmount;
    private Order order;
    private MenuFrame mf;
    private ClientPanel cp;
    public JPanel getItemsPanel() {
        return itemsPanel;
    }

    public CartPanel(Order order,MenuFrame mf, ClientPanel cp) {
        this.cp = cp;
        this.order = order;
        this.orderItems = order.getOrderItems();
        this.mf=mf;
        setupLayout();
        try {
            refreshCart();
        } catch (ItemNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Error loading cart items.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setupLayout() {
        setLayout(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setBackground(Color.WHITE);

        JLabel title = new JLabel("Your Cart", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);

        itemsPanel = new JPanel();
        itemsPanel.setLayout(new BoxLayout(itemsPanel, BoxLayout.Y_AXIS));
        itemsPanel.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(itemsPanel);
        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.setUnitIncrement(14);
        verticalScrollBar.setBlockIncrement(90);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane, BorderLayout.CENTER);

        JPanel footerPanel = createFooterPanel();
        add(footerPanel, BorderLayout.SOUTH);
    }

    void refreshCart() throws ItemNotFoundException {
        itemsPanel.removeAll();
        totalAmount = 0;

        for (OrderItem orderItem : orderItems) {
            JPanel itemPanel = createItemPanel(orderItem);
            itemsPanel.add(itemPanel);
            itemsPanel.add(Box.createRigidArea(new Dimension(0, 10)));

            MenuItem menuItem = MenuController.getMenuItemFromID(orderItem.getItemID());
            totalAmount += menuItem.getPrice() * orderItem.getQuantity();
        }
        order.setPrice(totalAmount);
        totalLabel.setText("Total: $" + String.format("%.2f", totalAmount));
        itemsPanel.revalidate();
        itemsPanel.repaint();
    }

    private JPanel createItemPanel(OrderItem orderItem) throws ItemNotFoundException {
        MenuItem menuItem = MenuController.getMenuItemFromID(orderItem.getItemID());
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.LIGHT_GRAY, 2), new EmptyBorder(5, 5, 5, 5)));
        panel.setBackground(new Color(0xF9F9F9));
        panel.setMaximumSize(new Dimension(500, 100));

        JPanel detailsPanel = new JPanel(new GridLayout(2, 1));
        detailsPanel.setBackground(new Color(0xF9F9F9));
        detailsPanel.add(new JLabel("Name: " + menuItem.getName()));
        detailsPanel.add(new JLabel("Quantity: " + orderItem.getQuantity() + " x $" + menuItem.getPrice()));
        panel.add(detailsPanel, BorderLayout.CENTER);

        JButton removeButton = new JButton("Remove");
        removeButton.setPreferredSize(new Dimension(100, 30));
        removeButton.addActionListener(e -> {
            orderItems.remove(orderItem);
            try {
                refreshCart();
            } catch (ItemNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "Error refreshing cart.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        panel.add(removeButton, BorderLayout.EAST);

        return panel;
    }

    private JPanel createFooterPanel() {
        JPanel footerPanel = new JPanel(new BorderLayout(10, 10));
        footerPanel.setBackground(Color.WHITE);

        totalLabel = new JLabel("Total: $0.00", SwingConstants.RIGHT);
        totalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        footerPanel.add(totalLabel, BorderLayout.CENTER);

        JButton checkoutButton = new JButton("Checkout");
        checkoutButton.setPreferredSize(new Dimension(100, 30));
        checkoutButton.addActionListener(e -> {
            if (orderItems.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Your cart is empty.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                OrderDetailsFrame orderDetails=new OrderDetailsFrame(order,mf,cp);
                orderDetails.setVisible(true);
//                JOptionPane.showMessageDialog(this, "Checkout successful! Total: $" + String.format("%.2f", totalAmount), "Success", JOptionPane.INFORMATION_MESSAGE);
//                orderItems.clear();
                try {
                    refreshCart();
                } catch (ItemNotFoundException ex) {
                    JOptionPane.showMessageDialog(this, "Error refreshing cart after checkout.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        footerPanel.add(checkoutButton, BorderLayout.EAST);

        return footerPanel;
    }
}
