package org.example.view;

import org.example.controller.OrderController;
import org.example.model.*;

import javax.swing.*;
import java.awt.*;

import static org.example.controller.OrderController.loadOrders;

public class MenuPanelToUser extends JPanel {
MenuFrame mf;
    public MenuPanelToUser(User user, MenuFrame mf, boolean viewOrdersON)throws TypeNotFoundException {
        this.mf=mf;
        JPanel panel = new JPanel();

        if (user.getUserType() == UserType.ADMIN) {
            panel = new AdminPanel().createAdminMenuPanel(mf);
        }
        else if(user.getUserType()==UserType.CLIENT){
            panel = new ClientPanel(mf).createClientMenuPanel(user);
        }
        else if(user.getUserType()==UserType.GUEST){
            panel = new GuestPanel().createGuestMenuPanel();
        }else{
            try {
                panel = new EmployeePanel();
            } catch (ItemNotFoundException e) {
                System.out.println(e.getMessage());
            }
//            هون ممكن يا نشيل التوابع اللي جوا الكلاسات ونضل عال  constructors او العكس
        }
        System.out.println(" = >>>> " + viewOrdersON);
        if(viewOrdersON && user.getUserType()==UserType.CLIENT){
//            JFrame frame = new JFrame();
//            frame.add(customerOrderStatusPanel());
//            frame.setVisible(true);

            JPanel p = new JPanel(new BorderLayout());
            p.add(panel, BorderLayout.WEST);
            p.add(customerOrderStatusPanel(user), BorderLayout.WEST);
            add(p);
        } else add(panel);
    }
    public JPanel customerOrderStatusPanel(User user) {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(255, 255, 255));
        OrderController.loadDailyOrders();
        int counter = 0;

        for (Order order : OrderController.getDailyOrders()) {
            if (order.getUser().getUserName().equals(user.getUserName())) {
                counter++;

                JPanel orderPanel = new JPanel(new BorderLayout());
                orderPanel.setBackground(Color.WHITE);
                orderPanel.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(255, 182, 193)),
                        BorderFactory.createEmptyBorder(10, 10, 10, 10)
                ));

                orderPanel.setPreferredSize(new Dimension(600, 80));
                orderPanel.setMaximumSize(new Dimension(600, 80));

                JLabel orderNumberLabel = new JLabel("Order: " + counter);
                orderNumberLabel.setFont(new Font("Arial", Font.BOLD, 18));

                JLabel statusLabel = new JLabel("Status: " + order.getOrderStatus());
                statusLabel.setFont(new Font("Arial", Font.PLAIN, 16));

                if (order.getOrderStatus().equals(OrderStatus.DONE) || order.getOrderStatus().equals(OrderStatus.ARRIVED)) {
                    statusLabel.setForeground(Color.GREEN);
                } else {
                    statusLabel.setForeground(Color.BLACK);
                }

                JButton cancelButton = new JButton("Cancel");
                cancelButton.setFont(new Font("Arial", Font.PLAIN, 14));
                cancelButton.setBackground(new Color(255, 99, 71));
                cancelButton.setForeground(Color.WHITE);
                cancelButton.addActionListener(e -> {
                    boolean orderCanceled = OrderController.cancelOrder(order);
                    if (orderCanceled) {
                        UIManager.put("OptionPane.messageForeground", Color.GREEN);
                        JOptionPane.showMessageDialog(this, "Order: " + " has been canceled.");
                    } else {
                        UIManager.put("OptionPane.messageForeground", Color.RED);
                        JOptionPane.showMessageDialog(this, "You can't cancel the order. It is " + order.getOrderStatus() + ".");
                    }

                    UIManager.put("OptionPane.messageForeground", Color.BLACK);
                    mf.reload();
                    for (MenuFrame mf : RestaurantGreetingFrame.getMfs()) {
                        mf.reload();
                    }
                    mainPanel.revalidate();
                    mainPanel.repaint();
                });

                JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                infoPanel.setBackground(Color.WHITE);
                infoPanel.add(orderNumberLabel);
                infoPanel.add(Box.createRigidArea(new Dimension(10, 0)));
                infoPanel.add(statusLabel);

                JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                buttonPanel.setBackground(Color.WHITE);
                buttonPanel.add(cancelButton);

                orderPanel.add(infoPanel, BorderLayout.CENTER);
                orderPanel.add(buttonPanel, BorderLayout.EAST);

                mainPanel.add(orderPanel);
                mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            }
        }
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(800, 500));
        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.setUnitIncrement(14);
        verticalScrollBar.setBlockIncrement(90);

        JPanel containerPanel = new JPanel(new BorderLayout());
        containerPanel.add(scrollPane, BorderLayout.CENTER);
        return containerPanel;
    }
}
