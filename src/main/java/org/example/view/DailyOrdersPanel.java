package org.example.view;

import org.example.controller.OrderController;

import javax.swing.*;
import java.awt.*;

public class DailyOrdersPanel extends JPanel {
    public DailyOrdersPanel() {
        setLayout(new GridBagLayout());
        setBackground(new Color(240, 240, 240));

        JPanel innerPanel = new JPanel();
        innerPanel.setLayout(new BorderLayout());
        innerPanel.setBackground(new Color(255, 182, 193));
        innerPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(255, 105, 180), 2),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        JLabel ordersLabel = new JLabel("Number of Daily Orders: " + OrderController.getNumOfDailyOrder(), SwingConstants.CENTER);
        ordersLabel.setFont(new Font("Serif", Font.BOLD, 24));
        ordersLabel.setForeground(Color.WHITE);
        innerPanel.add(ordersLabel, BorderLayout.CENTER);
        add(innerPanel);
    }
}