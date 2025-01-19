package org.example.view;

import org.example.controller.OrderController;
import org.example.model.*;

import javax.swing.*;
import java.awt.*;

public class MenuPanelToUser extends JPanel {

    public MenuPanelToUser(User user, MenuFrame mf, boolean viewOrdersON)throws TypeNotFoundException {
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
            p.add(customerOrderStatusPanel(user), BorderLayout.EAST);
            add(p);
        } else add(panel);
    }
    public JPanel customerOrderStatusPanel(User user){
        JPanel panel = new JPanel(new FlowLayout());
        panel.setPreferredSize(new Dimension(200, 500));
        panel.setSize(new Dimension(200, 500));
        panel.setMinimumSize(new Dimension(200, 500));
        panel.setMaximumSize(new Dimension(200, 500));

//        JPanel a = new JPanel(new GridLayout());
//        a.add(new JLabel("bRUH"));
//        a.add(new JLabel("Wow"));
//        panel.add(a);

        OrderController.loadDailyOrders();
        int conter = 0;
        for(Order order : OrderController.getDailyOrders()){
//            System.out.println("IN");
            if(order.getUser().getUserName().equals(user.getUserName())){
//                System.out.println("in");

                JPanel p = new JPanel(new GridLayout(1,2));
                p.add(new JLabel(String.valueOf(++conter)));
                p.add(new JLabel(String.valueOf(order.getOrderStatus())));
                panel.add(p);
            }
        }
        return panel;
    }
}
