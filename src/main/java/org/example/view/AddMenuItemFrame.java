package org.example.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import org.example.controller.MenuController;
import org.example.controller.TypeController;
import org.example.model.MenuItem;
import org.example.model.TypeNotFoundException;

public class AddMenuItemFrame extends JFrame {
    private MenuItem item;
    private boolean edit;
    private JTextField nameField;
    private JTextField descriptionField;
    private JTextField priceField;
    private JCheckBox bestsellerCheckBox;
    private JComboBox<String> typeComboBox;

    public AddMenuItemFrame(MenuItem item, Boolean edit) {
        this.item = item;
        this.edit = edit;
        initializeFrame();
        JPanel mainPanel = createMainPanel();
        add(mainPanel);
        setVisible(true);
    }

    private void initializeFrame() {
        setTitle(edit ? "Edit Menu Item" : "Add New Menu Item");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private JPanel createMainPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        addNameField(panel);
        addDescriptionField(panel);
        addPriceField(panel);
        addBestsellerCheckbox(panel);
        addTypeComboBox(panel);
        addButtons(panel);

        return panel;
    }

    private void addNameField(JPanel panel) {
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(20, 20, 100, 25);
        panel.add(nameLabel);

        nameField = new JTextField(edit ? item.getName() : "");
        nameField.setBounds(120, 20, 250, 25);
        nameField.setBorder(new EmptyBorder(2, 2, 2, 2));
        panel.add(nameField);
    }

    private void addDescriptionField(JPanel panel) {
        JLabel descriptionLabel = new JLabel("Description:");
        descriptionLabel.setBounds(20, 60, 100, 25);
        panel.add(descriptionLabel);

        descriptionField = new JTextField(edit ? item.getDescription() : "");
        descriptionField.setBounds(120, 60, 250, 25);
        descriptionField.setBorder(new EmptyBorder(2, 2, 2, 2));
        panel.add(descriptionField);
    }

    private void addPriceField(JPanel panel) {
        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setBounds(20, 100, 100, 25);
        panel.add(priceLabel);

        priceField = new JTextField(edit ? String.valueOf(item.getPrice()) : "");
        priceField.setBounds(120, 100, 250, 25);
        priceField.setBorder(new EmptyBorder(2, 2, 2, 2));
        panel.add(priceField);
    }

    private void addBestsellerCheckbox(JPanel panel) {
        JLabel bestsellerLabel = new JLabel("Is Bestseller:");
        bestsellerLabel.setBounds(20, 140, 100, 25);
        panel.add(bestsellerLabel);

        bestsellerCheckBox = new JCheckBox();
        bestsellerCheckBox.setBounds(120, 140, 25, 25);
        bestsellerCheckBox.setSelected(edit && item.isIs_bestseller());
        panel.add(bestsellerCheckBox);
    }

    private void addTypeComboBox(JPanel panel) {
        JLabel typeLabel = new JLabel("Type:");
        typeLabel.setBounds(20, 180, 100, 25);
        panel.add(typeLabel);

        typeComboBox = new JComboBox<>();
        populateTypeComboBox();
        typeComboBox.setBounds(120, 180, 250, 25);

        if (edit) {
            try {
                typeComboBox.setSelectedItem(TypeController.getTypeNameFromTypeID(item.getTypeID()));
            } catch (TypeNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        panel.add(typeComboBox);
    }

    private void addButtons(JPanel panel) {
        JButton addTypeButton = new JButton("Add New Type");
        addTypeButton.setBounds(120, 220, 150, 25);
        addTypeButton.addActionListener(e -> addNewType());
        panel.add(addTypeButton);

        JButton saveButton = new JButton("Save");
        saveButton.setBounds(120, 260, 100, 30);
        saveButton.addActionListener(e -> handleSave());
        panel.add(saveButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(230, 260, 100, 30);
        cancelButton.addActionListener(e -> dispose());
        panel.add(cancelButton);
    }

    private void populateTypeComboBox() {
        typeComboBox.removeAllItems();
        for (org.example.model.Type type : org.example.model.Type.getTypesOfItems()) {
            typeComboBox.addItem(type.getName());
        }
    }

    private void addNewType() {
        String newTypeName = JOptionPane.showInputDialog(this, "Enter new type name:", "Add New Type", JOptionPane.PLAIN_MESSAGE);
        if (newTypeName != null && !newTypeName.trim().isEmpty()) {
            org.example.model.Type newType = new org.example.model.Type(newTypeName.trim());
            newType.ID++;
            TypeController.addTypeToList(newType);
            TypeController.addTypeToFile(newType);
            populateTypeComboBox();
            typeComboBox.setSelectedItem(newTypeName.trim());
        }
    }

    private void handleSave() {
        try {
            if (edit) {
                MenuController.deleteMenuItemFromFile(item.getID());
            }
            saveMenuItem();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "An error occurred: " + e.getMessage());
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
            for (org.example.model.Type type : org.example.model.Type.getTypesOfItems()) {
                if (type.getName().equals(selectedTypeName)) {
                    typeId = type.getID();
                    break;
                }
            }

            if (typeId == -1) {
                JOptionPane.showMessageDialog(this, "Invalid type selected.");
                return;
            }

            int newID = edit ? item.getID() : ++MenuController.highestId;
            MenuItem newItem = new MenuItem(newID, typeId, name, description, price, isBestseller);
            MenuController.addMenuItemToFile(newItem);
            JOptionPane.showMessageDialog(this, "Menu item added successfully!");
            dispose();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid price entered.");
        }
    }
}