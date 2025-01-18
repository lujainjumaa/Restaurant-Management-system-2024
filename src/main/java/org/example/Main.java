package org.example;

import org.example.controller.MenuController;
import org.example.controller.OrderController;
import org.example.controller.TypeController;
import org.example.controller.UserController;
import org.example.model.*;
//import org.example.model.FileWatcher;
import org.example.view.MenuFrame;
import org.example.view.RestaurantGreetingFrame;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
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
    public static void setGlobalButtonStyle() {
        // Set the background color
        UIManager.put("Button.background", new Color(255, 250, 255));

        // Set the foreground color
        UIManager.put("Button.foreground", new Color(215, 81, 132));

        // Set the font (optional)
        UIManager.put("Button.font", new Font("Segoe UI", Font.BOLD, 16));

        // Set the border (2-pixel thick LineBorder with the specified color)
        UIManager.put("Button.border", new LineBorder(new Color(215, 81, 132), 2));

        // Ensure the changes apply immediately
        SwingUtilities.updateComponentTreeUI(new JFrame());
    }

    public static void main(String[] args) {


        System.setProperty("flatlaf.animation", "true");
        MenuController.loadMenu();
        TypeController.loadTypes();
        UserController.loadUsers();
        OrderController.loadOrders();
        OrderController.loadDailyOrders();
        UIManager.put("Panel.background", new Color(255, 240, 255));
        setGlobalFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
        setGlobalButtonStyle();


//        System.out.println(OrderController.getDailyOrders());
        System.out.println(MenuController.getMenuItemsList());
        new RestaurantGreetingFrame();

    }
}