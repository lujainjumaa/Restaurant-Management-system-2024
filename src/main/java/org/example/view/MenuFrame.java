package org.example.view;

import org.example.controller.MenuController;
import org.example.model.MenuItem;
import org.example.model.TypeNotFoundException;
import org.example.model.User;
import org.example.model.UserType;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuFrame extends JFrame {
    private static MenuFrame instance;
    public static User user;

    public MenuFrame() {
        initializeFrame();
    }

    private void initializeFrame() {
        setTitle("Restaurant Menu");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        reload();
    }

    public static MenuFrame getInstance() {
        if (instance == null) instance = new MenuFrame();
        return instance;
    }

    public void reload() {
        MenuController.loadMenu();
        getContentPane().removeAll();

        try {
            setLayout(new BorderLayout());
            setupHeader();
            setupMenuContent();
            setVisible(true);
        } catch (TypeNotFoundException tnfe) {
            System.out.println("Type not found...");
        }

        revalidate();
        repaint();
    }

    private void setupHeader() {
        JPanel buttonPanel = new JPanel(new BorderLayout());
        JLabel name = new JLabel(user.getUserName());
        buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        if (user.getUserType() == UserType.ADMIN) {
            setupAdminHeader(buttonPanel, name);
        } else if (user.getUserType() == UserType.GUEST) {
            setupGuestHeader(buttonPanel);
        } else {
            setupCustomerHeader(buttonPanel, name);
        }

        add(buttonPanel, BorderLayout.NORTH);
    }

    private void setupAdminHeader(JPanel buttonPanel, JLabel name) {
        JButton addNewItemButton = new JButton("Add New Menu Item");
        buttonPanel.add(name, BorderLayout.WEST);
        buttonPanel.add(addNewItemButton, BorderLayout.EAST);

        addNewItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddMenuItemFrame newItem = new AddMenuItemFrame(new MenuItem(-1, -1, "", "", 0, false), false);
            }
        });
    }

    private void setupGuestHeader(JPanel buttonPanel) {
        JButton loginButton = new JButton("Login");
        buttonPanel.add(loginButton, BorderLayout.EAST);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UserLoginFrame();
            }
        });
    }

    private void setupCustomerHeader(JPanel buttonPanel, JLabel name) {
        JButton goToCartButton = new JButton("Go To Cart");
        buttonPanel.add(name, BorderLayout.WEST);
        buttonPanel.add(goToCartButton, BorderLayout.EAST);

        goToCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Placeholder for cart functionality
            }
        });
    }

    private void setupMenuContent() throws TypeNotFoundException {
        JScrollPane scrollPane = new JScrollPane(new MenuViewToUser(user));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        setSize(800, 700);
        add(scrollPane, BorderLayout.CENTER);
    }

    public static void setUser(User user) {
        MenuFrame.user = user;
        if (instance != null) instance.reload();
    }
}
