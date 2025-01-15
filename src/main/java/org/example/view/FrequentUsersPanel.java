package org.example.view;

import org.example.model.User;

import javax.swing.*;
import java.awt.*;

public class FrequentUsersPanel extends JPanel {

    public FrequentUsersPanel() {

        JPanel userPanel = new JPanel();
        userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.Y_AXIS));
        userPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


//        ImageIcon smileyIcon = new ImageIcon(getClass().getResource("/smiley.png"));

        for (User user : User.sortedUsersList()) {
            JPanel userRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
//            JLabel iconLabel = new JLabel(smileyIcon);
            JLabel userInfo = new JLabel(user.getUserName() + " - " + user.getNumOfOrders() + " orders");

            userInfo.setFont(new Font("Arial", Font.PLAIN, 16));

//            userRow.add(iconLabel);
            userRow.add(userInfo);
            userPanel.add(userRow);
        }
        add(userPanel);
        setVisible(true);
    }
}
