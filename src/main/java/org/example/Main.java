package org.example;

import org.example.controller.MenuController;
import org.example.controller.OrderController;
import org.example.controller.TypeController;
import org.example.controller.UserController;
import org.example.view.RestaurantGreetingFrame;
import org.example.view.UI;

import javax.swing.*;
import java.awt.*;
public class Main {

    public static void main(String[] args) {
        System.setProperty("flatlaf.animation", "true");
        MenuController.loadMenu();
        TypeController.loadTypes();
        UserController.loadUsers();
        OrderController.loadOrders();
        OrderController.loadDailyOrders();
        UIManager.put("Panel.background", new Color(255, 240, 255));
        UI.setGlobalFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
        UI.setGlobalButtonStyle();
        new RestaurantGreetingFrame();

    }
}