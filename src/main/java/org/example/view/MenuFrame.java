package org.example.view;

import org.example.controller.MenuController;
import org.example.model.*;
import org.example.model.MenuItem;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuFrame extends JFrame {
    public User user;
    private MenuPanelToUser menuPanelToUser;


    public MenuFrame(User user) {
        this.user = user;
        initializeFrame();
    }

    private void initializeFrame() {
        setTitle("Restaurant Menu");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        reload();
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

        addNewItemButton.addActionListener(e -> {
            AddMenuItemFrame newItem = new AddMenuItemFrame(new MenuItem(-1, -1, "", "", 0, false), false, this);
        });
    }

    private void setupGuestHeader(JPanel buttonPanel) {
        JButton loginButton = new JButton("Login");
        buttonPanel.add(loginButton, BorderLayout.EAST);

        loginButton.addActionListener(e -> {
            System.out.println(" => " + (e.getSource() instanceof JButton));
            new UserLoginFrame(this);
        });
    }

    private void setupCustomerHeader(JPanel buttonPanel, JLabel name) {
        JButton goToCartButton = new JButton("Go To Cart");
        buttonPanel.add(name, BorderLayout.WEST);
        buttonPanel.add(goToCartButton, BorderLayout.EAST);
//        goToCartButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (menuFrameToUser != null && MenuFrameToUser.newOrder) {
//                    try {
//                        new CartFrame(menuFrameToUser.getOrder());
//                    } catch (ItemNotFoundException ex) {
//                        throw new RuntimeException(ex);
//                    }
//                } else {
//                    JOptionPane.showMessageDialog(null, "Your cart is empty :(", "Cart", JOptionPane.INFORMATION_MESSAGE);
//                }           }
//        });
    }

    private void setupMenuContent() throws TypeNotFoundException {
        menuPanelToUser = new MenuPanelToUser(user, this);
        JScrollPane scrollPane = new JScrollPane(menuPanelToUser);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        setSize(900, 700);
        add(scrollPane, BorderLayout.CENTER);
        setSize(1100, 700);
    }


    public void setUser(User user) {
        this.user = user;
        this.reload();
    }

}
