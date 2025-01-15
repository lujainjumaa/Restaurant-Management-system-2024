package org.example.view;

import org.example.controller.MenuController;
import org.example.controller.TypeController;
import org.example.controller.UserController;
import org.example.model.User;
import org.example.model.UserType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RestaurantGreetingFrame extends JFrame {
    public RestaurantGreetingFrame (){
        setTitle("Login");
        setSize(new Dimension(400, 350));
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        JButton seeTheMenu = new JButton("SEE THE MENU");
        JPanel seeTheMenuPanel = new JPanel();
        seeTheMenuPanel.add(seeTheMenu);
        add(seeTheMenuPanel);
        setVisible(true);
        seeTheMenu.addActionListener(e -> {
            Thread t1 = new Thread(()->{
            MenuController.loadMenu();
            TypeController.loadTypes();
            UserController.loadUsers();
            MenuFrame mf = new MenuFrame(new User("","", UserType.GUEST,0));
            });
            t1.start();
        });
    }


}
