package org.example.view;

import org.example.controller.OrderController;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DailyOrdersAndProfitsPanel extends JPanel {

    public DailyOrdersAndProfitsPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBackground(new Color(255, 255, 255));

        loadDailyData();
    }

    private void loadDailyData() {
        List<Double> dailyOrders = OrderController.calculateDailyOrdersForLastWeek();
        List<Double> dailyProfits = OrderController.calculateDailyProfitsForLastWeek();
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (int i = 0; i < 7; i++) {
            LocalDate targetDate = currentDate.minusDays(i);
            String dateString = targetDate.format(formatter);
            double orders = dailyOrders.get(i);
            double profits = dailyProfits.get(i);
            JPanel dayPanel = createDayPanel(dateString, orders, profits);
            add(dayPanel);
            add(Box.createRigidArea(new Dimension(0, 10)));
        }

        revalidate();
        repaint();
    }
    private JPanel createDayPanel(String date, double orders, double profits) {
        JPanel dayPanel = new JPanel(new BorderLayout());
        dayPanel.setBackground(Color.WHITE);
        dayPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(255, 182, 193)),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        JLabel dateLabel = new JLabel("Date: " + date);
        dateLabel.setFont(new Font("Arial", Font.BOLD, 18));
        dateLabel.setForeground(new Color(0, 0, 0));

        JLabel detailsLabel = new JLabel("Orders: " + (int) orders + "  Profits: $" + String.format("%.2f", profits));
        detailsLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        detailsLabel.setForeground(new Color(0, 0, 0));

        JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        infoPanel.setBackground(Color.WHITE);
        infoPanel.add(dateLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        infoPanel.add(detailsLabel);

        dayPanel.add(infoPanel, BorderLayout.CENTER);
        return dayPanel;
    }
}