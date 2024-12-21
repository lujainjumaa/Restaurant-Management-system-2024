package org.example;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddMenuItemFrame extends JFrame {
    private JTextField nameField;
    private JTextField descriptionField;
    private JTextField priceField;
    private JCheckBox bestsellerCheckBox;
    private JComboBox<String> typeComboBox;
    private JButton addTypeButton;

    public AddMenuItemFrame() {
        openAddItemFrame();
    }

    public void openAddItemFrame(){
        setTitle("Add New Menu Item");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Name field
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(20, 20, 100, 25);
        panel.add(nameLabel);
        nameField = new JTextField();
        nameField.setBounds(120, 20, 250, 25);
        nameField.setBorder(new EmptyBorder(2, 2, 2, 2));
        panel.add(nameField);

        // Description field
        JLabel descriptionLabel = new JLabel("Description:");
        descriptionLabel.setBounds(20, 60, 100, 25);
        panel.add(descriptionLabel);
        descriptionField = new JTextField();
        descriptionField.setBounds(120, 60, 250, 25);
        descriptionField.setBorder(new EmptyBorder(2, 2, 2, 2));
        panel.add(descriptionField);

        // Price field
        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setBounds(20, 100, 100, 25);
        panel.add(priceLabel);
        priceField = new JTextField();
        priceField.setBounds(120, 100, 250, 25);
        priceField.setBorder(new EmptyBorder(2, 2, 2, 2));
        panel.add(priceField);

        // Bestseller checkbox
        JLabel bestsellerLabel = new JLabel("Is Bestseller:");
        bestsellerLabel.setBounds(20, 140, 100, 25);
        panel.add(bestsellerLabel);
        bestsellerCheckBox = new JCheckBox();
        bestsellerCheckBox.setBounds(120, 140, 25, 25);
        panel.add(bestsellerCheckBox);

        // Type dropdown
        JLabel typeLabel = new JLabel("Type:");
        typeLabel.setBounds(20, 180, 100, 25);
        panel.add(typeLabel);
        typeComboBox = new JComboBox<>();
        populateTypeComboBox();
        typeComboBox.setBounds(120, 180, 250, 25);
        panel.add(typeComboBox);

        // Add Type button
        addTypeButton = new JButton("Add New Type");
        addTypeButton.setBounds(120, 220, 150, 25);
        addTypeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNewType();
            }
        });
        panel.add(addTypeButton);

        // Buttons
        JButton saveButton = new JButton("Save");
        saveButton.setBounds(120, 260, 100, 30);
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(230, 260, 100, 30);
        panel.add(saveButton);
        panel.add(cancelButton);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveMenuItem();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        add(panel);
        setVisible(true);
    }

    private void populateTypeComboBox() {
        typeComboBox.removeAllItems();
        for (org.example.Type type : org.example.Type.getTypes_of_items()) {
            typeComboBox.addItem(type.getName());
        }
    }

    private void addNewType() {
        String newTypeName = JOptionPane.showInputDialog(this, "Enter new type name:", "Add New Type", JOptionPane.PLAIN_MESSAGE);
        if (newTypeName != null && !newTypeName.trim().isEmpty()) {
            org.example.Type newType = new org.example.Type(newTypeName.trim());
            newType.ID++;
            org.example.Type.add_type_to_list(newType);
            org.example.Type.add_type_to_file(newType);
            populateTypeComboBox();
            typeComboBox.setSelectedItem(newTypeName.trim());
        }
    }

    private void saveMenuItem() {
        try {
            String name = nameField.getText();
            String description = descriptionField.getText();
            double price = Double.parseDouble(priceField.getText());
            boolean isBestseller = bestsellerCheckBox.isSelected();
            String selectedTypeName = (String) typeComboBox.getSelectedItem();

            if (selectedTypeName == null || selectedTypeName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please select a type for the menu item.");
                return;
            }

            int typeId = -1;
            for (org.example.Type type : org.example.Type.getTypes_of_items()) {
                if (type.getName().equals(selectedTypeName)) {
                    typeId = type.getID();
                    break;
                }
            }

            if (typeId == -1) {
                JOptionPane.showMessageDialog(this, "Invalid type selected.");
                return;
            }

            int newID = ++Menu.highest_id;
            MenuItem newItem = new MenuItem(newID, typeId, name, description, price, isBestseller);
            Menu.add_menu_item_to_file(newItem, typeId);
            JOptionPane.showMessageDialog(this, "Menu item added successfully!");
            dispose();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid price entered.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "An error occurred: " + e.getMessage());
        }
    }
}
