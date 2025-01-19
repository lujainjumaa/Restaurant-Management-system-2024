package org.example.view;

import org.example.controller.OrderController;

import javax.swing.*;
import java.awt.*;

public class DailyAndWeeklyProfitsPanel extends JPanel {
    public DailyAndWeeklyProfitsPanel() {
        setLayout(new GridBagLayout());
        setBackground(new Color(240, 240, 240));

        JPanel innerPanel = new JPanel();
        innerPanel.setLayout(new BorderLayout());
        innerPanel.setBackground(new Color(255, 182, 193));
        innerPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(255, 105, 180), 2),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        JLabel dailyProfitsLabel = new JLabel("Daily Profits: $" + OrderController.getDailyProfits(), SwingConstants.CENTER);
        dailyProfitsLabel.setFont(new Font("Serif", Font.BOLD, 24));
        dailyProfitsLabel.setForeground(Color.WHITE);
        innerPanel.add(dailyProfitsLabel, BorderLayout.NORTH);

        JLabel weeklyProfitsLabel = new JLabel("Weekly Profits: $" + OrderController.getWeeklyProfits(), SwingConstants.CENTER);
        weeklyProfitsLabel.setFont(new Font("Serif", Font.BOLD, 24));
        weeklyProfitsLabel.setForeground(Color.WHITE);
        innerPanel.add(weeklyProfitsLabel, BorderLayout.SOUTH);

        add(innerPanel);
    }
}