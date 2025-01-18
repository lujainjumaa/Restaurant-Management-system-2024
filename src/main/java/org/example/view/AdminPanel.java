package org.example.view;

import org.example.controller.MenuController;
import org.example.controller.TypeController;
import org.example.model.MenuItem;
import org.example.model.TypeNotFoundException;

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

public class AdminPanel extends JPanel{
    MenuFrame mf;

    public JPanel createAdminMenuPanel(MenuFrame mf) throws TypeNotFoundException {
        this.mf = mf;
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
                JPanel itemPanel = createAdminItemPanel(item);
                mainPanel.add(itemPanel);
                mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            }
        }
        return mainPanel;
    }

    public JPanel createAdminItemPanel(MenuItem item) {
        BufferedImage image = null;
        String RESOURCES_PATH = "src/main/resources/Pictures/";
        try {
            image = ImageIO.read(new File(RESOURCES_PATH + item.getPath())); // Replace "hamburger.jpg" with your image file
        } catch (IOException e) {
            e.printStackTrace();
        }

        image = ClientPanel.makeRoundedCorner(image,80);


        Font nameFont = new Font("Segoe UI Semibold", Font.PLAIN, 23); // Change font name, style, and size as desired
        // Resize the image (adjust width as needed)
        int newWidth = 200; // Example: Resize to 150 pixels wide
        int newHeight = (int) (image.getHeight() * ((double) newWidth / image.getWidth()));
        Image resizedImage = image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(resizedImage);


        // Create a JLabel for the image
        JLabel imageLabel = new JLabel(imageIcon);
        //imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        Dimension imageLabelsize = imageLabel.getPreferredSize();
        imageLabel.setBounds(390, 20, imageLabelsize.width, imageLabelsize.height);
        imageLabel.setBackground(new Color(255, 250, 255));
        imageLabel.setForeground(Color.WHITE);
        imageLabel.setBorder(new RoundedBorder(20, Color.WHITE));
        // Create a JLabel for the description




        JLabel nameLabel = new JLabel(item.getName());
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);

        nameLabel.setFont(nameFont);
        nameLabel.setForeground(new Color(51, 51, 51));
        nameLabel.setBackground(new Color(255, 250, 255));

        Dimension size = nameLabel.getPreferredSize();
        nameLabel.setBounds(25, 40, size.width, size.height);
        //nameLabel.setHorizontalAlignment(SwingConstants.CENTER);



        JLabel descriptionLabel = new JLabel(item.getDescription());
        descriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        Font font = new Font("Segoe UI Semibold", Font.PLAIN, 19); // Change font name, style, and size as desired
        descriptionLabel.setFont(font);

        descriptionLabel.setBackground(new Color(255, 250, 255));
        descriptionLabel.setForeground(new Color(126, 127, 131));
        Dimension descriptionLabelsize = descriptionLabel.getPreferredSize();
        descriptionLabel.setBounds(25, 70,descriptionLabelsize.width, descriptionLabelsize.height);

        // descriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);





        JLabel PriceLabel = new JLabel(item.getPrice() + "$");
        PriceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        PriceLabel.setFont(nameFont);
        Dimension PriceLabelsize = PriceLabel.getPreferredSize();
        PriceLabel.setBounds(25, 110, PriceLabelsize.width, PriceLabelsize.height);
        PriceLabel.setBackground(new Color(255, 250, 255));
        PriceLabel.setForeground(new Color(51, 51, 51));


        // Create order button
        JButton editButton = new JButton("EDIT");
        editButton.setBackground(new Color(255, 250, 255)); // Set button background to RGB(19, 15, 11)
        editButton.setForeground(new Color(215, 81, 132)); // Set text color to white
//        orderButton.setOpaque(false);
        editButton.setFocusPainted(false);
//        orderButton.setContentAreaFilled(false);
        Dimension orderButtonSize = editButton.getPreferredSize();
        editButton.setBounds(415, 200, 70, 40);
        // orderButton.setBorder(new RoundedBorder(20, new Color(215, 81, 132))); // Remove button border
        editButton.setFont(font);
        //  orderButton.setBorder(BorderFactory.createLineBorder(new Color(215, 81, 132),2));
        editButton.setBorder(BorderFactory.createLineBorder(new Color(215, 81, 132),2, false));
        JButton deleteButton = new JButton("DELETE");
        deleteButton.setBackground(new Color(255, 250, 255)); // Set button background to RGB(19, 15, 11)
        deleteButton.setForeground(new Color(215, 81, 132)); // Set text color to white
//        orderButton.setOpaque(false);
        deleteButton.setFocusPainted(false);
//        orderButton.setContentAreaFilled(false);
        Dimension deleteButtonSize = deleteButton.getPreferredSize();
        deleteButton.setBounds(500, 200, 70, 40);
        // orderButton.setBorder(new RoundedBorder(20, new Color(215, 81, 132))); // Remove button border
        deleteButton.setFont(font);
        //  orderButton.setBorder(BorderFactory.createLineBorder(new Color(215, 81, 132),2));
        deleteButton.setBorder(BorderFactory.createLineBorder(new Color(215, 81, 132),2, false));

//        orderButton.toFront();

        // Create main panel with image and button stacked vertically
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.add(nameLabel);
        mainPanel.setBackground(new Color(255, 250, 255)); // Set background color to RGB(19, 15, 11)
        mainPanel.add(imageLabel);
        mainPanel.add(editButton);
        mainPanel.add(deleteButton);
        // mainPanel.add(descriptionNamePanel, BorderLayout.SOUTH);
        mainPanel.add(PriceLabel);
//        mainPanel.add(nameLabel);
        mainPanel.add(descriptionLabel);
//
        //mainPanel.setLayout(null);
        //mainPanel.add(descriptionLabel, BorderLayout.BEFORE_FIRST_LINE);

        // Set preferred size for the panel (adjust as needed)
        mainPanel.setPreferredSize(new Dimension(600, 250));
        mainPanel.setComponentZOrder(imageLabel, 1);
        mainPanel.setComponentZOrder(editButton, 0);
        mainPanel.setComponentZOrder(deleteButton, 0);

//        mainPanel.add(imageLabel, "FIRST");
//        mainPanel.add(orderButton, "SECOND");

        // Create a panel for the image
//        JPanel imagePanel = new JPanel(new BorderLayout());
//        imagePanel.add(imageLabel, BorderLayout.CENTER);
//
//        imagePanel.setBackground(new Color(255, 250, 255));
//        imagePanel.setForeground(Color.WHITE);

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddMenuItemFrame(item, true, mf);

            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuController.deleteMenuItemFromFile(item.getID(), mf,false);
            }
        });

        return mainPanel;
    }


}
