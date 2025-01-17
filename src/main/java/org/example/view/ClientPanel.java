package org.example.view;

import org.example.controller.MenuController;
import org.example.controller.OrderController;
import org.example.controller.TypeController;
import org.example.model.*;
import org.example.model.MenuItem;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
        // Load the image
        BufferedImage image = null;
        String RESOURCES_PATH = "src/main/resources/Pictures/";
        try {
            image = ImageIO.read(new File(RESOURCES_PATH + item.getPath())); // Replace "hamburger.jpg" with your image file
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Resize the image (adjust width as needed)
        int newWidth = 150; // Example: Resize to 150 pixels wide
        int newHeight = (int) (image.getHeight() * ((double) newWidth / image.getWidth()));
        Image resizedImage = image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(resizedImage);

        // Create a JLabel for the image
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setBackground(new Color(19, 15, 11));
        imageLabel.setForeground(Color.WHITE);
        // Create a JLabel for the description

        JLabel descriptionLabel = new JLabel(item.getDescription());
        descriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        Font font = new Font("Segoe Script", Font.PLAIN, 14); // Change font name, style, and size as desired
        descriptionLabel.setFont(font);

        descriptionLabel.setBackground(new Color(19, 15, 11));
        descriptionLabel.setForeground(Color.WHITE);

        JLabel PriceLabel = new JLabel(String.valueOf(item.getPrice()));
        PriceLabel.setHorizontalAlignment(SwingConstants.CENTER);

        PriceLabel.setFont(font);

        PriceLabel.setBackground(new Color(19, 15, 11));
        PriceLabel.setForeground(Color.WHITE);

        // Create a panel for the image
        JPanel imagePanel = new JPanel(new BorderLayout());
        imagePanel.add(imageLabel, BorderLayout.CENTER);
        imagePanel.add(descriptionLabel, BorderLayout.SOUTH);
        imagePanel.add(PriceLabel, BorderLayout.AFTER_LAST_LINE);
        imagePanel.add(descriptionLabel, BorderLayout.BEFORE_FIRST_LINE);
        imagePanel.setBackground(new Color(19, 15, 11));
        imagePanel.setForeground(Color.WHITE);

        // Create order button
        JButton orderButton = new JButton("Order");
        orderButton.setBackground(new Color(19, 15, 11)); // Set button background to RGB(19, 15, 11)
        orderButton.setForeground(Color.WHITE); // Set text color to white
        orderButton.setBorderPainted(false); // Remove button border
        orderButton.setFont(font);

        // Create main panel with image and button stacked vertically
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(19, 15, 11)); // Set background color to RGB(19, 15, 11)
        mainPanel.add(imagePanel);
        mainPanel.add(orderButton);

        // Set preferred size for the panel (adjust as needed)
        mainPanel.setPreferredSize(new Dimension(1000, 250));
//        orderButton.addActionListener(e -> {
//            try {
//                int quantity = Integer.parseInt(quantityField.getText());
//
//                if (!newOrder) {
//                    order = new Order();
//                    order.setUser(user);
//                    if (cartPanel != null) {
//                        mf.remove(cartPanel);
//                    }
//                    cartPanel = new CartPanel(order,mf, this);
//                    mf.add(cartPanel, BorderLayout.EAST);
//
//                    newOrder = true;
//                }
//                int j=0;
//                for (OrderItem orderItem : order.getOrderItems()) {
//                    if(orderItem.getItemID()==item.getID()){
//                        orderItem.setQuantity(orderItem.getQuantity()+quantity);
//                        j++;
//                    }
//                }
//                if(j==0){
//                    OrderItem orderItem = new OrderItem(item.getID(), quantity);
//                    order.addToOrderItems(orderItem);
//                }
//
//                mf.revalidate();
//                mf.repaint();
//                cartPanel.refreshCart();
//            } catch (NumberFormatException | ItemNotFoundException ex) {
//                JOptionPane.showMessageDialog(panel, "Please enter a valid quantity.", "Error", JOptionPane.ERROR_MESSAGE);
//            }
//        });


//        panel.add(addToOrderPanel, gdb);


        return mainPanel;
    }
    public void setNewOrder(boolean newOrder) {
        this.newOrder = newOrder;
    }

}
