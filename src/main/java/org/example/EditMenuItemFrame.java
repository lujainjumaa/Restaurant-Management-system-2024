package org.example;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditMenuItemFrame extends JFrame {
    private JTextField idField;
    private JTextField nameField;
    private JTextField descriptionField;
    private JTextField priceField;
    private JCheckBox bestsellerCheckBox;
    private int Type_id;
    private MenuItem menuItem;
    private MenuItem editMenuItem;

    public EditMenuItemFrame(MenuItem item, int Type_id) {
        this.menuItem = item;
        this.Type_id = Type_id;
        openEditFrame(menuItem);
        setResizable(false);
    }

    public void setEditMenuItem(MenuItem editMenuItem) {
        this.editMenuItem = editMenuItem;
    }

    public MenuItem geteditMenuItem() {
        return editMenuItem;
    }

    private void openEditFrame(MenuItem item) {
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setLayout(null);

        setTitle("Edit Menu Item");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // ID field (disabled and non-editable)
        JLabel idLabel = new JLabel("ID:");
        idLabel.setBounds(20, 20, 100, 25);
        panel.add(idLabel);
        idField = new JTextField(String.valueOf(item.getID()));
        idField.setBounds(120, 20, 200, 25);
        idField.setEditable(false);
        idField.setEnabled(false);
        idField.setDisabledTextColor(new Color(0x222222));
        idField.setBorder(new EmptyBorder(2, 2, 2, 2));
        panel.add(idField);

        // Name field
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(20, 60, 100, 25);
        panel.add(nameLabel);
        nameField = new JTextField(item.getName());
        nameField.setBounds(120, 60, 200, 25);
        nameField.setBorder(new EmptyBorder(2, 2, 2, 2));
        panel.add(nameField);

        // Description field
        JLabel descriptionLabel = new JLabel("Description:");
        descriptionLabel.setBounds(20, 100, 100, 25);
        panel.add(descriptionLabel);
        descriptionField = new JTextField(item.getDescription());
        descriptionField.setBounds(120, 100, 200, 25);
        descriptionField.setBorder(new EmptyBorder(2, 2, 2, 2));
        panel.add(descriptionField);

        // Price field
        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setBounds(20, 140, 100, 25);
        panel.add(priceLabel);
        priceField = new JTextField(String.valueOf(item.getPrice()));
        priceField.setBounds(120, 140, 200, 25);
        priceField.setBorder(new EmptyBorder(2, 2, 2, 2));
        panel.add(priceField);

        // Bestseller checkbox
        JLabel bestsellerLabel = new JLabel("Is Bestseller:");
        bestsellerLabel.setBounds(20, 180, 100, 25);
        panel.add(bestsellerLabel);
        bestsellerCheckBox = new JCheckBox();
        bestsellerCheckBox.setBounds(120, 180, 20, 25);
        bestsellerCheckBox.setSelected(item.isIs_bestseller());
        panel.add(bestsellerCheckBox);

        // Buttons (Save and Cancel)
        JButton saveButton = new JButton("Save");
        saveButton.setBounds(120, 220, 90, 30);
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(220, 220, 90, 30);

        panel.add(saveButton);
        panel.add(cancelButton);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveChanges();
                Menu.delete_menu_item_from_file(menuItem.getID());
                Menu.add_menu_item_to_file(menuItem, Type_id);
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        requestFocus();
        add(panel);
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
        System.out.println(menuItem + "   save change");
    }
}
