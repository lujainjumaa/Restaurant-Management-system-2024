package org.example;

import org.example.controller.MenuController;
import org.example.controller.TypeController;
import org.example.controller.UserController;
import org.example.model.User;
import org.example.model.UserType;
import org.example.view.MenuFrame;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        MenuController.loadMenu();
        TypeController.loadTypes();
        UserController.loadUsers();
        MenuFrame.setUser(new User("","", UserType.GUEST));
        MenuFrame.getInstance();
    }
}