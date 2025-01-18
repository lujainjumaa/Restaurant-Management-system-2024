package org.example.view;

import org.example.controller.OrderController;

import javax.swing.*;
import java.awt.*;

public class DailyOrdersPanel extends JPanel {
    public DailyOrdersPanel() {
        setLayout(new BorderLayout());

        JLabel ordersLabel = new JLabel("Number of Daily Orders: " + OrderController.getNumOfDailyOrder(), SwingConstants.CENTER);
        ordersLabel.setFont(new Font("Serif", Font.BOLD, 18));

        add(ordersLabel, BorderLayout.CENTER);
    }
}