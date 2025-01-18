package org.example.view;

import org.example.controller.OrderController;

import javax.swing.*;
import java.awt.*;

public class DailyProfitsPanel extends JPanel {
    public DailyProfitsPanel() {
        setLayout(new BorderLayout());

        JLabel profitsLabel = new JLabel("Daily Profits: $" + OrderController.getDailyProfits(), SwingConstants.CENTER);
        profitsLabel.setFont(new Font("Serif", Font.BOLD, 18));

        add(profitsLabel, BorderLayout.CENTER);
    }
}