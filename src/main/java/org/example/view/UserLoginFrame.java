package org.example.view;

import org.example.model.User;
import org.example.controller.UserController;
import org.example.model.UserType;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class UserLoginFrame extends JFrame {
    private User user;
    private CustomTextField usernameField;
    private CustomTextField passwordField;
    private JLabel errorLabel;
    private MenuFrame mf;

    public UserLoginFrame(MenuFrame mf) {
        initializeFrame();
        this.mf = mf;
        JPanel mainPanel = createMainPanel();
        add(mainPanel);
        setVisible(true);
    }

    private void initializeFrame() {
        setTitle("Login");
        setSize(new Dimension(420, 430));
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 1, 0, 0));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel usernamePanel = createUsernamePanel();
        JPanel passwordPanel = createPasswordPanel();
        JPanel errorPanel = createErrorPanel();
        JPanel buttonsPanel = createLoginRegisterButton();

        mainPanel.add(usernamePanel);
        mainPanel.add(passwordPanel);
        mainPanel.add(errorPanel);
        mainPanel.add(buttonsPanel);

        return mainPanel;
    }

    private JPanel createUsernamePanel() {
        JPanel userNamePanel = new JPanel();
        userNamePanel.setLayout(new GridLayout(2, 1, 2, 2));
        userNamePanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel usernameLabel = new JLabel("Username: ");
        usernameField = new CustomTextField();

        usernameField.setBorder(BorderFactory.createCompoundBorder(
                usernameField.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        userNamePanel.add(usernameLabel);
        userNamePanel.add(usernameField);
        return userNamePanel;
    }

    private JPanel createPasswordPanel() {
        JPanel passwordPanel = new JPanel();
        passwordPanel.setLayout(new GridLayout(2, 1, 2, 2));
        passwordPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel passwordLabel = new JLabel("Password: ");
        passwordField = new CustomTextField();
        passwordField.setBorder(BorderFactory.createCompoundBorder(
                passwordField.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);
        return passwordPanel;
    }

    private JPanel createErrorPanel() {
        JPanel errorPanel = new JPanel();
        errorPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        errorLabel = new JLabel();
        errorLabel.setVisible(false);
        errorLabel.setForeground(new Color(0xFF0000));
        errorPanel.add(errorLabel);
        return errorPanel;
    }

    private JPanel createLoginRegisterButton() {
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        buttonsPanel.setLayout(new GridBagLayout());

        JButton loginButton = new JButton("LOGIN");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });

        JButton registerButton = new JButton("REGISTER");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleRegister();
            }
        });

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(15, 15, 15, 15);
        buttonsPanel.add(loginButton, gbc);

        gbc.gridx = 1;
        buttonsPanel.add(registerButton, gbc);

        return buttonsPanel;
    }

    private void handleLogin() {
        if (!loginUser()) {
            showError("Wrong Username or Password");
        }
    }

    private void handleRegister() {
        if (registerUser()) {
            showError("Username Already Exists");
        }
    }

    private boolean loginUser() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        for (User user : User.getUsers()) {
            if (Objects.equals(user.getUserName(), username) && Objects.equals(user.getPassword(), password)) {
                mf.setUser(user);
                dispose();
                return true;
            }
        }
        return false;
    }

    private boolean registerUser() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        for (User user : User.getUsers()) {
            if (Objects.equals(user.getUserName(), username)) {
                return true;
            }
        }

        User newUser = new User(username, password, UserType.CLIENT,0);
        UserController.addUserToFile(newUser);
        mf.setUser(newUser);
        return false;
    }

    private void showError(String message) {
        errorLabel.setText(message);
        errorLabel.setVisible(true);
    }
}
