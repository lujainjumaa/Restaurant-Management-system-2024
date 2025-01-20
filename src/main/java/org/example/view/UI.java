package org.example.view;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class UI {
    public static Font mainFont = new Font("Segoe UI Semibold", Font.PLAIN, 20);
    public static Color beigeColor = new Color(252, 237, 227);
    public static Color orangeColor = new Color(245, 99, 38);
    public static Color whiteColor = new Color(255, 250, 255);
    public static Color blueColor = new Color(27, 128, 155);

    public static Font getMainFont(int size){
        return new Font("Segoe UI Semibold", Font.PLAIN, size);
    }

    public static Font getMainFont() {
        return mainFont;
    }

    public static void setMainFont(Font mainFont) {
        UI.mainFont = mainFont;
    }
//    public static void makeButtonDefaultUI(JButton button){
//        button.setFont(mainFont);
//        button.setBackground(new Color(255, 250, 255));
//        button.setForeground(new Color(215, 81, 132));
//        button.setFocusPainted(false);
//        button.setBorder(BorderFactory.createLineBorder(new Color(215, 81, 132), 2, false));
//        button.setBounds(440, 200, 100, 40);
//    }
//    public static void makeButtonDefaultUI(JToggleButton button){
//        button.setFont(mainFont);
//        button.setBackground(new Color(255, 250, 255));
//        button.setForeground(new Color(215, 81, 132));
//        button.setFocusPainted(false);
//        button.setBorder(BorderFactory.createLineBorder(new Color(215, 81, 132), 2, false));
//        button.setBounds(440, 200, 60, 40);
//    }
    public static void setGlobalButtonStyle() {
        UIManager.put("Button.background", whiteColor);

        UIManager.put("Button.foreground", orangeColor);

        UIManager.put("Button.font", new Font("Segoe UI", Font.BOLD, 16));

        UIManager.put("Button.border", new LineBorder(orangeColor, 2));

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
