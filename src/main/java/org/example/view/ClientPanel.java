package org.example.view;

import org.example.controller.MenuController;
import org.example.controller.TypeController;
import org.example.model.*;
import org.example.model.MenuItem;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

public class ClientPanel {

    boolean newOrder=false;
    MenuFrame mf;
    Order order;
    CartPanel cartPanel;
    User user;

    ClientPanel(MenuFrame mf) {
        this.mf = mf;
    }

    public JPanel createClientMenuPanel(User user) throws TypeNotFoundException {
        this.user=user;
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(Color.WHITE);

        for (Map.Entry<Integer, java.util.List<org.example.model.MenuItem>> entry : MenuController.getMenuItems().entrySet()) {
            int typeID = entry.getKey();
            List<org.example.model.MenuItem> items = entry.getValue();
            String typeName = TypeController.getTypeNameFromTypeID(typeID);
            JLabel header = new JLabel(typeName + "s", SwingConstants.CENTER);
            JPanel headerPanel = new JPanel();
            headerPanel.add(header);
            header.setFont(new Font("Arial", Font.BOLD, 16));
            header.setForeground(new Color(0x2E3B4E));
            mainPanel.add(headerPanel);
            mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

            for (org.example.model.MenuItem item : items) {
                JPanel itemPanel = createClientItemPanel(item);
                mainPanel.add(itemPanel);
                mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            }
        }
        return mainPanel;
    }

    public JPanel createClientItemPanel(MenuItem item) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.LIGHT_GRAY, 2), new EmptyBorder(5, 5, 5, 5)));
        panel.setBackground(new Color(0xF9F9F9));
        panel.setMaximumSize(new Dimension(500, 100));

        GridBagConstraints gdb = new GridBagConstraints();
        gdb.gridx = 0;
        gdb.gridy = 0;
        gdb.weightx = 1;
        gdb.weighty = 1;
        gdb.fill = GridBagConstraints.BOTH;
        gdb.anchor = GridBagConstraints.LINE_START;

        JPanel detailsPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        detailsPanel.setPreferredSize(new Dimension(370, 100));
        detailsPanel.setMinimumSize(new Dimension(370, 100));
        detailsPanel.setMaximumSize(new Dimension(370, 100));
        detailsPanel.setBackground(new Color(0xF9F9F9));
        detailsPanel.add(new JLabel("Name: " + item.getName()));
        detailsPanel.add(new JLabel("Description: " + item.getDescription()));
        detailsPanel.add(new JLabel("Price: $" + item.getPrice()));
        detailsPanel.add(new JLabel(item.isIs_bestseller() ? "Bestseller" : "Regular Item"));
        panel.add(detailsPanel, gdb);

        gdb.gridx = 1;
        gdb.gridy = 0;
        gdb.weightx = 0;
        gdb.weighty = 0;
        gdb.fill = GridBagConstraints.NONE;
        gdb.anchor = GridBagConstraints.CENTER;

        JPanel addToOrderPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        addToOrderPanel.setBackground(new Color(0xF9F9F9));

        JTextField quantityField = new JTextField("1");
        quantityField.setPreferredSize(new Dimension(100, 30));
        addToOrderPanel.add(new JLabel("Quantity:"));
        addToOrderPanel.add(quantityField);
        JButton addButton = new JButton("Add to Order");
        addButton.setPreferredSize(new Dimension(120, 30));
        addToOrderPanel.add(addButton);
        addButton.addActionListener(e -> {
            try {
                int quantity = Integer.parseInt(quantityField.getText());

                if (!newOrder) {
                    order = new Order();
                    order.setUser(user);
                    if (cartPanel != null) {
                        mf.remove(cartPanel);
                    }
                    cartPanel = new CartPanel(order,mf, this);
                    mf.add(cartPanel, BorderLayout.EAST);

                    newOrder = true;
                }
                int j=0;
                for (OrderItem orderItem : order.getOrderItems()) {
                    if(orderItem.getItemID()==item.getID()){
                        orderItem.setQuantity(orderItem.getQuantity()+quantity);
                        j++;
                    }
                }
                if(j==0){
                    OrderItem orderItem = new OrderItem(item.getID(), quantity);
                    order.addToOrderItems(orderItem);
                }

                mf.revalidate();
                mf.repaint();
                cartPanel.refreshCart();
            } catch (NumberFormatException | ItemNotFoundException ex) {
                JOptionPane.showMessageDialog(panel, "Please enter a valid quantity.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });


        panel.add(addToOrderPanel, gdb);


        return panel;
    }
    public void setNewOrder(boolean newOrder) {
        this.newOrder = newOrder;
    }

}
