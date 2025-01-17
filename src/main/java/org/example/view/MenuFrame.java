package org.example.view;

import org.example.controller.MenuController;
import org.example.model.*;
import org.example.model.MenuItem;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class MenuFrame extends JFrame {
    public User user;
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
        } else if(user.getUserType() == UserType.CLIENT){
            setupCustomerHeader(buttonPanel, name);
        }else{
            return;
        }

        add(buttonPanel, BorderLayout.NORTH);
    }

    private void setupAdminHeader(JPanel Panel, JLabel name) {
        JButton addNewItemButton = new JButton("Add New Menu Item");
        JButton viewFrequentCostumersButton = new JButton("View Frequent Costumers");
        Panel.add(name, BorderLayout.WEST);
        JPanel buttonPanel = new JPanel(new GridLayout(1,2, 100,30));
        buttonPanel.add(addNewItemButton, BorderLayout.EAST);
        buttonPanel.add(viewFrequentCostumersButton,BorderLayout.EAST);
        Panel.add(buttonPanel);
        addNewItemButton.addActionListener(e -> {
            AddMenuItemFrame newItem = new AddMenuItemFrame(new MenuItem(-1, 0, -1, "", "", 0, false), false, this);
        });
        viewFrequentCostumersButton.addActionListener(e -> {
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
