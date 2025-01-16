package org.example.view;

import org.example.model.*;

import javax.swing.*;

public class MenuPanelToUser extends JPanel {

    public MenuPanelToUser(User user, MenuFrame mf)throws TypeNotFoundException {
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
        add(panel);
    }
}
