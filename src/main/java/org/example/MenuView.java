package org.example;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

import static org.example.Type.get_type_name_from_typeID;

public class MenuView extends JFrame {
    private static MenuView instance;

    public MenuView() {
        setTitle("Restaurant Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);
        reload();
    }

    public static MenuView getInstance() {
        if (instance == null) instance = new MenuView();
        return instance;
    }

    public void reload() {
        Menu.loadMenu();

        getContentPane().removeAll();
        try {
            setLayout(new BorderLayout());

            JPanel buttonPanel = new JPanel();
            JButton AddNewItem = new JButton("Add New Menu Item");
            buttonPanel.add(AddNewItem);
            buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
            add(buttonPanel, BorderLayout.NORTH);

            JScrollPane scrollPane = new JScrollPane(createMenuPanel());
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            setSize(800, 700);
            add(scrollPane, BorderLayout.CENTER);

            AddNewItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    AddMenuItemFrame newItem = new AddMenuItemFrame();
                }
            });

            setVisible(true);
        } catch (TypeNotFoundException tnfe) {
            System.out.println("Type not found....");
        }
        revalidate();
        repaint();
    }

    private JPanel createMenuPanel() throws TypeNotFoundException {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(Color.WHITE);

        for (Map.Entry<Integer, List<MenuItem>> entry : Menu.getMenuItems().entrySet()) {
            int typeID = entry.getKey();
            List<MenuItem> items = entry.getValue();
            String typeName = get_type_name_from_typeID(typeID);
            JLabel header = new JLabel(typeName + "s", SwingConstants.CENTER);
            header.setFont(new Font("Arial", Font.BOLD, 16));
            header.setForeground(new Color(0x2E3B4E));
            mainPanel.add(header);
            mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

            for (MenuItem item : items) {
                JPanel itemPanel = createItemPanel(item);
                mainPanel.add(itemPanel);
                mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            }
        }
        return mainPanel;
    }

    private JPanel createItemPanel(MenuItem item) {
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
                Menu.edit_menu_item(item);
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu.delete_menu_item_from_file(item.getID());
            }
        });

        return panel;
    }
}
