package org.example.view;

import javax.swing.*;
import java.awt.*;

public class CustomTextField extends JTextField {
    String text;
    public CustomTextField() {
        this.text=text;
        this.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
        this.setForeground(new Color(215, 81, 132));
        this.setBackground(Color.WHITE);
        this.setBorder(BorderFactory.createLineBorder(new Color(87, 82, 82), 2));
        this.setSelectionColor(new Color(161, 75, 122));
        this.setSelectedTextColor(Color.WHITE);
        this.setMargin(new Insets(10, 10, 10, 10));
//        this.setHorizontalAlignment(JTextField.CENTER);
        this.setPreferredSize(new Dimension(200, 90));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (getText().isEmpty()) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(new Color(215, 81, 132));
            g2.setFont(new Font("Segoe UI Semibold", Font.ITALIC, 17));
            g2.drawString(text, 5, 20);
            g2.dispose();
        }
    }
}
