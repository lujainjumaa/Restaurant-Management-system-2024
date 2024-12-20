package org.example;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;
import java.util.Map;

import static org.example.Type.get_type_name_from_typeID;

public class MenuView extends JFrame {
    private final Menu menu; // Reference to the Menu data

    public MenuView(Menu menu) {
        this.menu = menu;

        setTitle("Restaurant Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null); // Center the frame
        try{
        // Main content panel with a scroll pane
        JScrollPane scrollPane = new JScrollPane(createMenuPanel());
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane);

        setVisible(true);
        }catch(TypeNotFoundException tnfe){
            System.out.println("Type not found....");
        }

    }

    private JPanel createMenuPanel() throws TypeNotFoundException{
        // Main panel where all menu items will be displayed
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(Color.WHITE);

        // Iterate over the menu_items map and add each type_ID's list
        for (Map.Entry<Integer, List<MenuItem>> entry : menu.getMenuItems().entrySet()) {
            int typeID = entry.getKey();
            List<MenuItem> items = entry.getValue();
            String typeName = get_type_name_from_typeID(typeID);

            // Add a header for the category (type_ID)
            JLabel header = new JLabel(typeName + "s");
            header.setFont(new Font("Arial", Font.BOLD, 16));
            header.setForeground(new Color(0x2E3B4E));
            mainPanel.add(header);
            mainPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add spacing

            // Add each MenuItem in its own panel
            for (MenuItem item : items) {
                JPanel itemPanel = createItemPanel(item);
                mainPanel.add(itemPanel);
                mainPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Spacing between items
            }
        }

        return mainPanel;
    }

    private JPanel createItemPanel(MenuItem item) {
        // Create a panel for one MenuItem
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        panel.setBackground(new Color(0xF9F9F9));
        panel.setMaximumSize(new Dimension(500, 100)); // Fixed size for uniformity

        // Left Side: Menu Item Details
        JPanel detailsPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        detailsPanel.setBackground(new Color(0xF9F9F9));
        detailsPanel.add(new JLabel("Name: " + item.getName()));
        detailsPanel.add(new JLabel("Description: " + item.getDescription()));
        detailsPanel.add(new JLabel("Price: $" + item.getPrice()));
        detailsPanel.add(new JLabel(item.isIs_bestseller() ? "Bestseller" : "Regular Item"));

        // Add the details to the panel
        panel.add(detailsPanel, BorderLayout.CENTER);

        // Right Side: A placeholder image or icon
        JLabel imageLabel = new JLabel();
        imageLabel.setPreferredSize(new Dimension(80, 80));
        imageLabel.setIcon(new ImageIcon("C:\\Users\\g\\Downloads\\photo_2024-12-18_19-11-10.jpg")); // Replace with your image path
        panel.add(imageLabel, BorderLayout.EAST);

        return panel;
    }
}
