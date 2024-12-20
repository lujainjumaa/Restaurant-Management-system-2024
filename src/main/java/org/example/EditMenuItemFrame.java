package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditMenuItemFrame extends JFrame {
    private JTextField idField;
    private JTextField nameField;
    private JTextField descriptionField;
    private JTextField priceField;
    private JCheckBox bestsellerCheckBox;

    private MenuItem menuItem;
    private MenuItem editMenuItem;
    public EditMenuItemFrame(MenuItem item) {
        this.menuItem = item;
        openEditFrame(menuItem);
        System.out.println(editMenuItem);

    }

    public void setEditMenuItem(MenuItem editMenuItem) {
        this.editMenuItem = editMenuItem;
    }

    public MenuItem geteditMenuItem() {
        return editMenuItem;
    }

    private void openEditFrame(MenuItem item){
        setTitle("Edit Menu Item");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(6, 2));

        // ID field
        add(new JLabel("ID:"));
        idField = new JTextField(String.valueOf(item.getID()));
        idField.setEditable(false); // ID is typically not editable
        add(idField);

        // Name field
        add(new JLabel("Name:"));
        nameField = new JTextField(item.getName());
        add(nameField);

        // Description field
        add(new JLabel("Description:"));
        descriptionField = new JTextField(item.getDescription());
        add(descriptionField);

        // Price field
        add(new JLabel("Price:"));
        priceField = new JTextField(String.valueOf(item.getPrice()));
        add(priceField);

        // Bestseller checkbox
        add(new JLabel("Is Bestseller:"));
        bestsellerCheckBox = new JCheckBox();
        bestsellerCheckBox.setSelected(item.isIs_bestseller());
        add(bestsellerCheckBox);

        JButton saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");
        add(saveButton);
        add(cancelButton);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveChanges();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    private void saveChanges() {
        try {
            menuItem.setName(nameField.getText());
            menuItem.setDescription(descriptionField.getText());
            menuItem.setPrice((float) Double.parseDouble(priceField.getText()));
            menuItem.setIs_bestseller(bestsellerCheckBox.isSelected());

            JOptionPane.showMessageDialog(this, "Menu item updated successfully!");
            dispose();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid price entered");
        }
        System.out.println(menuItem+"   save change");
    }
}
