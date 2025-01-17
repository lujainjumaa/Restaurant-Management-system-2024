package org.example.view;

import org.example.controller.MenuController;
import org.example.controller.OrderController;
import org.example.controller.TypeController;
import org.example.controller.UserController;
import org.example.model.FilePath;
//import org.example.model.FileWatcher;
import org.example.model.User;
import org.example.model.UserType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RestaurantGreetingFrame extends JFrame {

    static ArrayList<MenuFrame> mfs = new ArrayList<MenuFrame>();

    public static ArrayList<MenuFrame> getMfs() {
        return mfs;
    }

    public static void setMfs(ArrayList<MenuFrame> mfs) {
        RestaurantGreetingFrame.mfs = mfs;
    }

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

//        Thread watcherThread = new Thread(new FileWatcher(FilePath.getDailyOrders()));
//        watcherThread.start();

        seeTheMenu.addActionListener(e -> {
            Thread t1 = new Thread(()->{

                MenuFrame mf = new MenuFrame(new User("","", UserType.GUEST,0));
                mf.reload();
                mfs.add(mf);
            });
            t1.start();

        });
    }


}
