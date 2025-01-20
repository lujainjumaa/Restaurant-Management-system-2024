package org.example.view;

import org.example.controller.MenuController;
import org.example.model.MenuItem;

import javax.swing.*;
import java.awt.*;

public class BestSellerItemsPanel extends JPanel {
    public BestSellerItemsPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        setBackground(new Color(249, 249, 249));

        for (MenuItem menuItem : MenuController.sortedMenuItemsList()) {
            JPanel itemPanel = new JPanel(new BorderLayout());
            itemPanel.setBackground(Color.WHITE);
            itemPanel.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(255, 182, 193)),
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)
            ));

            JLabel nameLabel = new JLabel(menuItem.getName());
            nameLabel.setFont(new Font("Arial", Font.BOLD, 18));

            JLabel priceLabel = new JLabel(menuItem.getPrice() + " $");
            priceLabel.setFont(new Font("Arial", Font.PLAIN, 16));

            JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            infoPanel.setBackground(Color.WHITE);
            infoPanel.add(nameLabel);
            infoPanel.add(Box.createRigidArea(new Dimension(10, 0)));
            infoPanel.add(priceLabel);

            itemPanel.add(infoPanel, BorderLayout.CENTER);

            add(itemPanel);
            add(Box.createRigidArea(new Dimension(0, 10)));
        }

        setVisible(true);
    }
}