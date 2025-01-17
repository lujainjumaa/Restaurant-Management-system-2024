package org.example;

import org.example.controller.MenuController;
import org.example.controller.OrderController;
import org.example.controller.TypeController;
import org.example.controller.UserController;
import org.example.model.FilePath;
//import org.example.model.FileWatcher;
import org.example.model.User;
import org.example.model.UserType;
import org.example.view.MenuFrame;
import org.example.view.RestaurantGreetingFrame;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        //System.out.println(date.getTime());


        MenuController.loadMenu();
        TypeController.loadTypes();
        UserController.loadUsers();
        OrderController.loadOrders();
        OrderController.loadDailyOrders();
//        System.out.println(OrderController.getDailyOrders());
        System.out.println(MenuController.getMenuItemsList());
        new RestaurantGreetingFrame();

    }
}