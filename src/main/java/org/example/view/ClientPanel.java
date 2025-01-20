package org.example.view;

import org.example.controller.MenuController;
import org.example.controller.TypeController;
import org.example.model.*;
import org.example.model.MenuItem;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
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
                JPanel itemPanel = createClientItemPanel(item);
                mainPanel.add(itemPanel);
                mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            }

        }
        return mainPanel;
    }

    public JPanel createClientItemPanel(MenuItem item) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(FilePath.getPIC_RESOURCES_PATH() + item.getPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        image = makeRoundedCorner(image,80);


        Font nameFont = UI.getMainFont(23);
        int newWidth = 200;
        int newHeight = (int) (image.getHeight() * ((double) newWidth / image.getWidth()));
        Image resizedImage = image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(resizedImage);


        JLabel imageLabel = new JLabel(imageIcon);
        Dimension imageLabelsize = imageLabel.getPreferredSize();
        imageLabel.setBounds(390, 20, imageLabelsize.width, imageLabelsize.height);
        imageLabel.setBackground(UI.whiteColor);
        imageLabel.setForeground(UI.whiteColor);
        imageLabel.setBorder(new RoundedBorder(20, UI.whiteColor));

        JLabel nameLabel = new JLabel(item.getName());
//        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);

        nameLabel.setFont(nameFont);
        nameLabel.setForeground(new Color(51, 51, 51));
        nameLabel.setBackground(new Color(255, 250, 255));

        Dimension size = nameLabel.getPreferredSize();
        nameLabel.setBounds(25, 40, 400, 30);
        //nameLabel.setHorizontalAlignment(SwingConstants.CENTER);


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


        JButton orderButton = new JButton("ADD");
        orderButton.setBackground(new Color(255, 250, 255));
        orderButton.setForeground(UI.orangeColor);
//        orderButton.setOpaque(false);
        orderButton.setFocusPainted(false);
//        orderButton.setContentAreaFilled(false);
        Dimension orderButtonsize = orderButton.getPreferredSize();
        orderButton.setBounds(440, 200, 100, 40);
        // orderButton.setBorder(new RoundedBorder(20, new Color(215, 81, 132)));
        orderButton.setFont(font);
        //  orderButton.setBorder(BorderFactory.createLineBorder(new Color(215, 81, 132),2));
        orderButton.setBorder(BorderFactory.createLineBorder(UI.orangeColor,2, false));

//        orderButton.toFront();

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.add(nameLabel);
        mainPanel.setBackground(UI.whiteColor);
        mainPanel.add(imageLabel);
        mainPanel.add(orderButton);
        // mainPanel.add(descriptionNamePanel, BorderLayout.SOUTH);
        mainPanel.add(PriceLabel);
//        mainPanel.add(nameLabel);
        if(item.isIs_bestseller()){
            mainPanel.add(bestseller);
        }
        mainPanel.add(descriptionLabel);
//
        //mainPanel.setLayout(null);
        //mainPanel.add(descriptionLabel, BorderLayout.BEFORE_FIRST_LINE);

        // Set preferred size for the panel (adjust as needed)
        mainPanel.setPreferredSize(new Dimension(600, 250));
        mainPanel.setComponentZOrder(imageLabel, 1);
        mainPanel.setComponentZOrder(orderButton, 0);
//        mainPanel.add(imageLabel, "FIRST");
//        mainPanel.add(orderButton, "SECOND");

        // Create a panel for the image
//        JPanel imagePanel = new JPanel(new BorderLayout());
//        imagePanel.add(imageLabel, BorderLayout.CENTER);
//
//        imagePanel.setBackground(new Color(255, 250, 255));
//        imagePanel.setForeground(Color.WHITE);
        orderButton.addActionListener(e -> {
            try {

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
                        orderItem.setQuantity(orderItem.getQuantity()+1);
                        j++;
                    }
                }
                if(j==0){
                    OrderItem orderItem = new OrderItem(item.getID(), 1);
                    order.addToOrderItems(orderItem);
                }

                mf.revalidate();
                mf.repaint();
                cartPanel.refreshCart();
            } catch (ItemNotFoundException ex) {
                System.out.println(ex.getMessage());
            }
        });


//        panel.add(addToOrderPanel, gdb);


        return mainPanel;
    }
    public void setNewOrder(boolean newOrder) {
        this.newOrder = newOrder;
    }
    public static BufferedImage makeRoundedCorner(BufferedImage image, int cornerRadius) {
        int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2 = output.createGraphics();

        g2.setComposite(AlphaComposite.Src);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);
        g2.fill(new RoundRectangle2D.Float(0, 0, w, h, cornerRadius, cornerRadius));


        g2.setComposite(AlphaComposite.SrcAtop);
        g2.drawImage(image, 0, 0, null);

        g2.dispose();

        return output;
    }

}
