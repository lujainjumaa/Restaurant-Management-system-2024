package org.example.view;

import org.example.model.User;

import javax.swing.*;
import java.awt.*;

public class FrequentUsersPanel extends JPanel {

    public FrequentUsersPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBackground(new Color(255, 255, 255));

        for (User user : User.sortedUsersList()) {
            JPanel userPanel = new JPanel(new BorderLayout());
            userPanel.setBackground(Color.WHITE);
            userPanel.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(255, 182, 193)),
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)
            ));

            JLabel nameLabel = new JLabel(user.getUserName());
            nameLabel.setFont(new Font("Arial", Font.BOLD, 18));

            JLabel ordersLabel = new JLabel(user.getNumOfOrders() + " orders");
            ordersLabel.setFont(new Font("Arial", Font.PLAIN, 16));

            JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            infoPanel.setBackground(Color.WHITE);
            infoPanel.add(nameLabel);
            infoPanel.add(Box.createRigidArea(new Dimension(10, 0)));
            infoPanel.add(ordersLabel);

            userPanel.add(infoPanel, BorderLayout.CENTER);

            add(userPanel);
            add(Box.createRigidArea(new Dimension(0, 10)));
        }

        setVisible(true);
    }
}