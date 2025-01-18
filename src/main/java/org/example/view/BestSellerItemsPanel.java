package org.example.view;

import org.example.controller.MenuController;
import org.example.model.MenuItem;

import javax.swing.*;
import java.awt.*;

public class BestSellerItemsPanel extends JPanel {
    public BestSellerItemsPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // تخطيط عام عمودي
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // هوامش خارجية

        // لون خلفية اللوحة الرئيسية
        setBackground(new Color(249, 249, 249)); // لون خلفية فاتح

        for (MenuItem menuItem : MenuController.sortedMenuItemsList()) {
            // إنشاء لوحة لكل عنصر
            JPanel itemPanel = new JPanel(new BorderLayout());
            itemPanel.setBackground(Color.WHITE); // لون خلفية أبيض
            itemPanel.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(255, 182, 193)), // حد سفلي
                    BorderFactory.createEmptyBorder(10, 10, 10, 10) // هوامش داخلية
            ));

            // إضافة اسم العنصر وسعره
            JLabel nameLabel = new JLabel(menuItem.getName());
            nameLabel.setFont(new Font("Arial", Font.BOLD, 18)); // خط كبير وعريض

            JLabel priceLabel = new JLabel(menuItem.getPrice() + " $");
            priceLabel.setFont(new Font("Arial", Font.PLAIN, 16)); // خط أصغر

            // لوحة لتجميع الاسم والسعر
            JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            infoPanel.setBackground(Color.WHITE); // لون خلفية أبيض
            infoPanel.add(nameLabel);
            infoPanel.add(Box.createRigidArea(new Dimension(10, 0))); // مسافة بين الاسم والسعر
            infoPanel.add(priceLabel);

            // إضافة لوحة المعلومات إلى لوحة العنصر
            itemPanel.add(infoPanel, BorderLayout.CENTER);

            // إضافة لوحة العنصر إلى اللوحة الرئيسية
            add(itemPanel);
            add(Box.createRigidArea(new Dimension(0, 10))); // مسافة بين العناصر
        }

        setVisible(true);
    }
}