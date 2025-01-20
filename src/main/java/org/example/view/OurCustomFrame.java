package org.example.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OurCustomFrame extends JFrame {
    private int mouseX, mouseY;
    public JPanel contentPanel;

    public OurCustomFrame(String title) {
        // Set JFrame properties
//        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        setSize(800, 600);
        setUndecorated(true);
//        setLayout(new BorderLayout());

        JPanel titleBar = new JPanel();
        titleBar.setBackground(new Color(168, 160, 168));
        titleBar.setLayout(new BorderLayout());
        titleBar.setPreferredSize(new Dimension(getWidth(), 30));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel controlButtons = new JPanel();
        controlButtons.setBackground(new Color(168, 160, 168));
        controlButtons.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));

        JButton minimizeButton = new JButton("-");
        customizeButton(minimizeButton);
        minimizeButton.addActionListener(e -> setState(JFrame.ICONIFIED));

        JButton closeButton = new JButton("X");
        customizeButton(closeButton);
        closeButton.addActionListener(e -> this.dispose());

        controlButtons.add(minimizeButton);
        controlButtons.add(closeButton);

        titleBar.add(titleLabel, BorderLayout.CENTER);
        titleBar.add(controlButtons, BorderLayout.EAST);

        titleBar.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
            }
        });
        titleBar.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                int x = e.getXOnScreen();
                int y = e.getYOnScreen();
                setLocation(x - mouseX, y - mouseY);
            }
        });

        add(titleBar, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(Color.LIGHT_GRAY);
        add(contentPanel, BorderLayout.CENTER);
        this.contentPanel = contentPanel;

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void customizeButton(JButton button) {
        button.setFocusPainted(false);
        button.setBackground(new Color(168, 160, 168));
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(Color.RED);
            }

            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(168, 160, 168));
            }
        });
    }
}

