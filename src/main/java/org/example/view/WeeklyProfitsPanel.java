package org.example.view;

import org.example.controller.OrderController;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class WeeklyProfitsPanel extends JPanel {

    public WeeklyProfitsPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBackground(new Color(255, 255, 255));

        loadWeeklyProfits();
    }

    private void loadWeeklyProfits() {
        List<Double> dailyProfits = OrderController.calculateDailyProfitsForLastWeek();
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (int i = 0; i < dailyProfits.size(); i++) {
            LocalDate targetDate = currentDate.minusDays(i);
            String dateString = targetDate.format(formatter);
            double profit = dailyProfits.get(i);

            JPanel profitPanel = new JPanel(new BorderLayout());
            profitPanel.setBackground(Color.WHITE);
            profitPanel.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(255, 182, 193)),
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)
            ));

            JLabel dateLabel = new JLabel("Date: " + dateString);
            dateLabel.setFont(new Font("Arial", Font.BOLD, 18));
            dateLabel.setForeground(new Color(0, 0, 0));

            JLabel profitLabel = new JLabel("Profit: $" + profit);
            profitLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            profitLabel.setForeground(new Color(0, 0, 0));

            JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            infoPanel.setBackground(Color.WHITE);
            infoPanel.add(dateLabel);
            infoPanel.add(Box.createRigidArea(new Dimension(10, 0)));
            infoPanel.add(profitLabel);

            profitPanel.add(infoPanel, BorderLayout.CENTER);
            add(profitPanel);
            add(Box.createRigidArea(new Dimension(0, 10)));
        }

        revalidate();
        repaint();
    }
}