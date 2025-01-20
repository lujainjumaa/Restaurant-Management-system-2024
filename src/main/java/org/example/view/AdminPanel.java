package org.example.view;

import org.example.controller.MenuController;
import org.example.controller.TypeController;
import org.example.model.FilePath;
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
        mainPanel.setBackground(UI.blueColor);

        for (Map.Entry<Integer, java.util.List<org.example.model.MenuItem>> entry : MenuController.getMenuItems().entrySet()) {
            int typeID = entry.getKey();
            List<org.example.model.MenuItem> items = entry.getValue();
            if(items.isEmpty()){
                continue;
            }
            String typeName = TypeController.getTypeNameFromTypeID(typeID);
            JLabel header = new JLabel(typeName + "s", SwingConstants.CENTER);
            JPanel headerPanel = new JPanel();
            headerPanel.add(header);
            headerPanel.setBackground(UI.blueColor);
            header.setFont(new Font("Arial", Font.BOLD, 18));
            header.setForeground(UI.whiteColor);
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
        try {
            image = ImageIO.read(new File(FilePath.getPIC_RESOURCES_PATH() + item.getPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        image = ClientPanel.makeRoundedCorner(image,80);


        Font nameFont = UI.getMainFont(23);
        int newWidth = 200;
        int newHeight = (int) (image.getHeight() * ((double) newWidth / image.getWidth()));
        Image resizedImage = image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(resizedImage);


        JLabel imageLabel = new JLabel(imageIcon);
        //imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        Dimension imageLabelsize = imageLabel.getPreferredSize();
        imageLabel.setBounds(390, 20, imageLabelsize.width, imageLabelsize.height);
        imageLabel.setBackground(UI.whiteColor);
        imageLabel.setForeground(UI.whiteColor);
        imageLabel.setBorder(new RoundedBorder(20, Color.WHITE));

        JLabel nameLabel = new JLabel(item.getName());
//        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);

        nameLabel.setFont(nameFont);
        nameLabel.setForeground(new Color(51, 51, 51));
        nameLabel.setBackground(new Color(255, 250, 255));

        Dimension size = nameLabel.getPreferredSize();
        nameLabel.setBounds(25, 40, 400, 30);

        JLabel bestseller = new JLabel("BESTSELLER");
        bestseller.setHorizontalAlignment(SwingConstants.CENTER);
        bestseller.setFont(UI.getMainFont(18));
        bestseller.setBounds(220, 130, 200,50 );
        bestseller.setBackground(new Color(255, 250, 255));
        bestseller.setForeground(new Color(224, 79, 33));

        JLabel descriptionLabel = new JLabel(item.getDescription());
        descriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        Font font = UI.getMainFont(19);
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

        JButton editButton = new JButton("EDIT");
        editButton.setBackground(new Color(255, 250, 255));
        editButton.setForeground(UI.orangeColor);
//        orderButton.setOpaque(false);
        editButton.setFocusPainted(false);
//        orderButton.setContentAreaFilled(false);
        Dimension orderButtonSize = editButton.getPreferredSize();
        editButton.setBounds(415, 200, 70, 40);
        // orderButton.setBorder(new RoundedBorder(20, new Color(215, 81, 132)));
        editButton.setFont(font);
        //  orderButton.setBorder(BorderFactory.createLineBorder(new Color(215, 81, 132),2));
        editButton.setBorder(BorderFactory.createLineBorder(UI.orangeColor,2, false));
        JButton deleteButton = new JButton("DELETE");
        deleteButton.setBackground(new Color(255, 250, 255));
        deleteButton.setForeground(UI.orangeColor);
//        orderButton.setOpaque(false);
        deleteButton.setFocusPainted(false);
//        orderButton.setContentAreaFilled(false);
        Dimension deleteButtonSize = deleteButton.getPreferredSize();
        deleteButton.setBounds(500, 200, 70, 40);
        // orderButton.setBorder(new RoundedBorder(20, new Color(215, 81, 132)));
        deleteButton.setFont(font);
        //  orderButton.setBorder(BorderFactory.createLineBorder(new Color(215, 81, 132),2));
        deleteButton.setBorder(BorderFactory.createLineBorder(UI.orangeColor,2, false));

//        orderButton.toFront();

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.add(nameLabel);
        mainPanel.setBackground(UI.whiteColor);
        mainPanel.add(imageLabel);
        mainPanel.add(editButton);
        if(item.isIs_bestseller()){
            mainPanel.add(bestseller);
        }
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
                // Create the main frame
                JFrame frame = new JFrame("Confirm Deletion");
                frame.setSize(500, 300);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                frame.setLocationRelativeTo(null);

                JPanel mainPanel = new JPanel();
                mainPanel.setLayout(new GridLayout(3, 1, 0, 0));
                mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

                JLabel sure = new JLabel("Are you sure you want to delete this item?", JLabel.CENTER);
                sure.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 22));
                sure.setBounds(100,100,100,100);
                sure.setForeground(Color.DARK_GRAY);

                JPanel labelPanel = new JPanel(new BorderLayout());
                labelPanel.add(sure, BorderLayout.CENTER);
                labelPanel.setBorder(new EmptyBorder(20, 0, 20, 0));

                JPanel buttonPanel = new JPanel(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(10, 20, 10, 20);

                JButton yes = new JButton("YES");
                yes.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 19));

                yes.setFocusPainted(false);
                yes.setBorder(BorderFactory.createCompoundBorder(new LineBorder(UI.orangeColor, 2),BorderFactory.createEmptyBorder(6, 20, 6, 20)));

                JButton no = new JButton("NO");
                no.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 19));
                no.setFocusPainted(false);
                no.setBorder(BorderFactory.createCompoundBorder(new LineBorder(UI.orangeColor, 2),BorderFactory.createEmptyBorder(6, 20, 6, 20)));

                gbc.gridx = 0;
                gbc.gridy = 0;
                buttonPanel.add(yes, gbc);
                gbc.gridx = 1;
                buttonPanel.add(no, gbc);

                mainPanel.add(labelPanel);
                mainPanel.add(new JPanel());
                mainPanel.add(buttonPanel);

                frame.add(mainPanel, BorderLayout.CENTER);

                yes.addActionListener(e1 -> {
                    MenuController.deleteMenuItemFromFile(item.getID(), mf, false);
                    frame.dispose();
                });

                no.addActionListener(e12 -> frame.dispose());

                frame.setVisible(true);
            }
        });

        return mainPanel;
    }


}
