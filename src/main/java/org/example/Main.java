package org.example;

import org.example.controller.MenuController;
import org.example.controller.TypeController;
import org.example.controller.UserController;
import org.example.model.User;
import org.example.model.UserType;
import org.example.view.MenuFrame;
import org.example.view.RestaurantGreetingFrame;

import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
//        Thread t1 = new Thread(()->{
//            MenuController.loadMenu();
//            TypeController.loadTypes();
//            UserController.loadUsers();
//            MenuFrame mf = new MenuFrame(new User("","", UserType.GUEST));
//        });
//        Thread t2 = new Thread(()->{
//            MenuController.loadMenu();
//            TypeController.loadTypes();
//            UserController.loadUsers();
//            MenuFrame mf = new MenuFrame(new User("","", UserType.GUEST));
//        });
//        t1.start();
//        t2.start();
        new RestaurantGreetingFrame();
    }
}