package org.example.view;

import org.example.controller.MenuController;
import org.example.controller.TypeController;
import org.example.model.MenuItem;
import org.example.model.TypeNotFoundException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

public class AdminPanel extends JPanel{
    MenuFrame mf;

    public JPanel createAdminMenuPanel(MenuFrame mf) throws TypeNotFoundException {
        this.mf = mf;
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(Color.WHITE);

        for (Map.Entry<Integer, java.util.List<org.example.model.MenuItem>> entry : MenuController.getMenuItems().entrySet()) {
            int typeID = entry.getKey();
            List<org.example.model.MenuItem> items = entry.getValue();
            String typeName = TypeController.getTypeNameFromTypeID(typeID);
            JLabel header = new JLabel(typeName + "s", SwingConstants.CENTER);
            JPanel headerPanel = new JPanel();
            headerPanel.add(header);
            header.setFont(new Font("Arial", Font.BOLD, 16));
            header.setForeground(new Color(0x2E3B4E));
            mainPanel.add(headerPanel);
            mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

            for (org.example.model.MenuItem item : items) {
                JPanel itemPanel = createAdminItemPanel(item);
                mainPanel.add(itemPanel);
                mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            }
        }
        return mainPanel;
    }

    public JPanel createAdminItemPanel(MenuItem item) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.LIGHT_GRAY, 2), new EmptyBorder(5, 5, 5, 5)));
        panel.setBackground(new Color(0xF9F9F9));
        panel.setMaximumSize(new Dimension(500, 100));

        GridBagConstraints gdb = new GridBagConstraints();
        gdb.gridx = 0;
        gdb.gridy = 0;
        gdb.weightx = 1;
        gdb.weighty = 1;
        gdb.fill = GridBagConstraints.BOTH;
        gdb.anchor = GridBagConstraints.LINE_START;
        JPanel detailsPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        detailsPanel.setPreferredSize(new Dimension(370, 100));
        detailsPanel.setMinimumSize(new Dimension(370, 100));
        detailsPanel.setMaximumSize(new Dimension(370, 100));
        detailsPanel.setBackground(new Color(0xF9F9F9));
        detailsPanel.add(new JLabel("Name: " + item.getName()));
        detailsPanel.add(new JLabel("Description: " + item.getDescription()));
        detailsPanel.add(new JLabel("Price: $" + item.getPrice()));
        detailsPanel.add(new JLabel(item.isIs_bestseller() ? "Bestseller" : "Regular Item"));
        panel.add(detailsPanel, gdb);

        gdb.gridx = GridBagConstraints.RELATIVE;
        gdb.gridy = GridBagConstraints.RELATIVE;
        gdb.weightx = 1;
        gdb.weighty = 1;
        gdb.fill = GridBagConstraints.VERTICAL;
        gdb.anchor = GridBagConstraints.LINE_END;

        JPanel buttonsPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        buttonsPanel.setPreferredSize(new Dimension(75, 100));
        buttonsPanel.setMinimumSize(new Dimension(75, 100));
        buttonsPanel.setMaximumSize(new Dimension(75, 100));
        buttonsPanel.setBackground(new Color(0xF9F9F9));
        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");

        buttonsPanel.add(editButton);
        buttonsPanel.add(deleteButton);
        panel.add(buttonsPanel, gdb);

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddMenuItemFrame(item, true, mf);

            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuController.deleteMenuItemFromFile(item.getID(), mf);
            }
        });

        return panel;
    }


}
