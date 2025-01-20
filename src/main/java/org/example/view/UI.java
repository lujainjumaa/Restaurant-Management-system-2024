package org.example.view;

import javax.swing.*;
import javax.swing.border.LineBorder;
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
        button.setBackground(new Color(255, 250, 255));
        button.setForeground(new Color(215, 81, 132));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(215, 81, 132), 2, false));
        button.setBounds(440, 200, 100, 40);
    }
    public static void makeButtonDefaultUI(JToggleButton button){
        button.setFont(nameFont);
        button.setBackground(new Color(255, 250, 255));
        button.setForeground(new Color(215, 81, 132));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(215, 81, 132), 2, false));
        button.setBounds(440, 200, 60, 40);
    }
    public static void setGlobalButtonStyle() {
        UIManager.put("Button.background", new Color(255, 250, 255));

        UIManager.put("Button.foreground", new Color(215, 81, 132));

        UIManager.put("Button.font", new Font("Segoe UI", Font.BOLD, 16));

        UIManager.put("Button.border", new LineBorder(new Color(215, 81, 132), 2));

        SwingUtilities.updateComponentTreeUI(new JFrame());
    }
    public static void setGlobalFont(Font font) {
        UIManager.put("Label.font", font);
        UIManager.put("Button.font", font);
        UIManager.put("TextField.font", font);
        UIManager.put("TextArea.font", font);
        UIManager.put("CheckBox.font", font);
        UIManager.put("RadioButton.font", font);
        UIManager.put("ComboBox.font", font);
        UIManager.put("List.font", font);
        UIManager.put("Table.font", font);
        UIManager.put("TitledBorder.font", font);
        UIManager.put("Menu.font", font);
        UIManager.put("MenuItem.font", font);
        UIManager.put("PopupMenu.font", font);
        UIManager.put("MenuBar.font", font);
        UIManager.put("ToolTip.font", font);
    }
}
