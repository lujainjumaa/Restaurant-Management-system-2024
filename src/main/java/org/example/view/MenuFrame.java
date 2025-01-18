package org.example.view;

import org.example.controller.MenuController;
import org.example.model.*;
import org.example.model.MenuItem;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MenuFrame extends JFrame {
    public User user;
    public boolean viewOrdersON = false;
    private MenuPanelToUser menuPanelToUser;


    public MenuFrame(User user) {
        this.user = user;
        initializeFrame();
    }

    public User getUser() {
        return user;
    }

    private void initializeFrame() {
        setTitle("Restaurant Menu");
        setSize(1000, 1000);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        reload();
    }


    public void reload() {

        MenuController.loadMenu();
        getContentPane().removeAll();

        try {
            setLayout(new BorderLayout());
            setupHeader();
            setupMenuContent(viewOrdersON);
            setVisible(true);
        } catch (TypeNotFoundException tnfe) {
            System.out.println("Type not found...");
        }

        revalidate();
        repaint();
    }

    private void setupHeader() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        JLabel name = new JLabel(user.getUserName());
        headerPanel.setBorder(new EmptyBorder(10, 10, 10, 10));


        if (user.getUserType() == UserType.ADMIN) {
            setupAdminHeader(headerPanel, name);
        } else if (user.getUserType() == UserType.GUEST) {
            setupGuestHeader(headerPanel);
        } else if(user.getUserType() == UserType.CLIENT){
            setupCustomerHeader(headerPanel, name);
        }else{
            setupEmployeeHeader(headerPanel,name);
        }

        add(headerPanel, BorderLayout.NORTH);
    }

    private void setupEmployeeHeader(JPanel headerPanel, JLabel name) {

        headerPanel.add(name, BorderLayout.WEST);

    }

    private void setupAdminHeader(JPanel Panel, JLabel name) {
        JButton addNewItemButton = new JButton("Add New Menu Item");
        JButton viewFrequentCustomersButton = new JButton("View Frequent Costumers");
        Panel.add(name, BorderLayout.WEST);
        JPanel buttonPanel = new JPanel(new GridLayout(1,2, 100,30));
        buttonPanel.add(addNewItemButton, BorderLayout.EAST);
        buttonPanel.add(viewFrequentCustomersButton,BorderLayout.EAST);
        Panel.add(buttonPanel);
        addNewItemButton.addActionListener(e -> {
            AddMenuItemFrame newItem = new AddMenuItemFrame(new MenuItem(-1, 0, -1, "", "", 0, false,""), false, this);
        });
        viewFrequentCustomersButton.addActionListener(e -> {
            JFrame frame = new JFrame();
            frame.setTitle("Frequent Users");
            frame.setSize(400, 600);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setLocationRelativeTo(null); // Center the frame on the screen
            frame.setLayout(new BorderLayout());
            frame.add(new BestSellerItemsPanel());
            frame.setVisible(true);


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
        JButton viewOrders = new JButton("view orders");
        buttonPanel.add(name, BorderLayout.WEST);
        buttonPanel.add(viewOrders, BorderLayout.EAST);

        viewOrders.addActionListener(e -> {
            viewOrdersON = !viewOrdersON;
            this.reload();
        });
    }

    private void setupMenuContent(boolean viewOrdersON) throws TypeNotFoundException {
        menuPanelToUser = new MenuPanelToUser(user, this, viewOrdersON);
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
