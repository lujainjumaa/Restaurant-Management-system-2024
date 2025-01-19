package org.example.view;

import javax.swing.*;
import java.awt.*;

public class CustomPasswordField extends JPasswordField {
    String text;

    public CustomPasswordField() {
        this.text = text;
        this.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
        this.setForeground(new Color(215, 81, 132));
        this.setBackground(Color.WHITE);
        this.setBorder(BorderFactory.createLineBorder(new Color(87, 82, 82), 2));
        this.setBorder(BorderFactory.createLineBorder(new Color(215, 81, 132), 2));
        this.setSelectionColor(new Color(161, 75, 122));
        this.setSelectedTextColor(Color.WHITE);
        this.setMargin(new Insets(10, 10, 10, 10));
//        this.setHorizontalAlignment(JTextField.CENTER);
        this.setPreferredSize(new Dimension(200, 90));
    }
}
