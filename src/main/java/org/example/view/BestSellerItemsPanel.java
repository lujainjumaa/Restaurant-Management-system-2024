package org.example.view;

import org.example.controller.MenuController;
import org.example.model.MenuItem;
import org.example.model.User;

import javax.swing.*;
import java.awt.*;

public class BestSellerItemsPanel extends JPanel {
    public BestSellerItemsPanel(){
        JPanel userPanel = new JPanel();
        userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.Y_AXIS));
        userPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


//        ImageIcon smileyIcon = new ImageIcon(getClass().getResource("/smiley.png"));

        for (MenuItem menuItem : MenuController.sortedMenuItemsList()) {
            JPanel userRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
//            JLabel iconLabel = new JLabel(smileyIcon);
            JLabel menuItemInfo = new JLabel(menuItem.getName() + " - " + menuItem.getPrice() + " $");

            menuItemInfo.setFont(new Font("Arial", Font.PLAIN, 16));

//            userRow.add(iconLabel);
            userRow.add(menuItemInfo);
            userPanel.add(userRow);
        }
        add(userPanel);
        setVisible(true);
    }
}
