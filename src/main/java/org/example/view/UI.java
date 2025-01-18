package org.example.view;

import javax.swing.*;
import java.awt.*;

public class UI {
    public static Font nameFont = new Font("Segoe UI Semibold", Font.PLAIN, 23);


    public static Font getNameFont() {
        return nameFont;
    }

    public static void setNameFont(Font nameFont) {
        UI.nameFont = nameFont;
    }
    public static void makeButtonDefaultUI(JButton button){
        button.setFont(nameFont);
        button.setBackground(new Color(255, 250, 255)); // Default background
        button.setForeground(new Color(215, 81, 132)); // Default text color
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(215, 81, 132), 2, false));
        button.setBounds(440, 200, 100, 40);
    }
    public static void makeButtonDefaultUI(JToggleButton button){
        button.setFont(nameFont);
        button.setBackground(new Color(255, 250, 255)); // Default background
        button.setForeground(new Color(215, 81, 132)); // Default text color
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(215, 81, 132), 2, false));
        button.setBounds(440, 200, 60, 40);
    }
}
