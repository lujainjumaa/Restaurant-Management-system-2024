package org.example.view;

import javax.swing.*;
import java.awt.*;

public class CustomPasswordField extends JPasswordField {
    String text;

    public CustomPasswordField() {
        this.text = text;
        this.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
        this.setForeground(UI.orangeColor);
        this.setBackground(UI.whiteColor);
//        this.setBorder(BorderFactory.createLineBorder(new Color(87, 82, 82), 2));
        this.setBorder(BorderFactory.createLineBorder(UI.orangeColor, 2));
        this.setSelectionColor(UI.orangeColor);
        this.setSelectedTextColor(UI.whiteColor);
        this.setMargin(new Insets(10, 10, 10, 10));
        this.setPreferredSize(new Dimension(200, 90));
    }
}
