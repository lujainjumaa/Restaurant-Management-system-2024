package org.example;

import org.example.controller.MenuController;
import org.example.controller.OrderController;
import org.example.controller.TypeController;
import org.example.controller.UserController;
//import org.example.model.FileWatcher;
import org.example.view.RestaurantGreetingFrame;
import org.example.view.UI;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        System.setProperty("flatlaf.animation", "true");
        MenuController.loadMenu();
        TypeController.loadTypes();
        UserController.loadUsers();
        OrderController.loadOrders();
        OrderController.loadDailyOrders();
        UIManager.put("Panel.background", UI.beigeColor);
        UI.setGlobalFont(UI.getMainFont(16));
        UI.setGlobalButtonStyle();
        UIManager.put("Label.font", UI.getMainFont(16));
//        UIManager.put("ComboBox.font", globalFont);
//        UIManager.put("ComboBox.foreground", Color.ORANGE);
        UIManager.put("Label.foreground", UI.blueColor);
        new RestaurantGreetingFrame();

    }
}