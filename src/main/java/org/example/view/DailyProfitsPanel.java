package org.example.view;

import org.example.controller.OrderController;

import javax.swing.*;
import java.awt.*;

public class DailyProfitsPanel extends JPanel {
    public DailyProfitsPanel() {
        setLayout(new GridBagLayout());
        setBackground(new Color(240, 240, 240));

        JPanel innerPanel = new JPanel();
        innerPanel.setLayout(new BorderLayout());
        innerPanel.setBackground(new Color(255, 182, 193));
        innerPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(255, 105, 180), 2),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        JLabel profitsLabel = new JLabel("Daily Profits: $" + OrderController.getDailyProfits(), SwingConstants.CENTER);
        profitsLabel.setFont(new Font("Serif", Font.BOLD, 24));
        profitsLabel.setForeground(Color.WHITE);
        innerPanel.add(profitsLabel, BorderLayout.CENTER);
        add(innerPanel);
    }
}