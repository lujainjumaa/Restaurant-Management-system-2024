package org.example.view;

import org.example.controller.MenuController;
import org.example.controller.OrderController;
import org.example.controller.TypeController;
import org.example.controller.UserController;
import org.example.model.FilePath;
//import org.example.model.FileWatcher;
import org.example.model.User;
import org.example.model.UserType;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class RestaurantGreetingFrame extends JFrame {

    static ArrayList<MenuFrame> mfs = new ArrayList<MenuFrame>();

    public static ArrayList<MenuFrame> getMfs() {
        return mfs;
    }

    public static void setMfs(ArrayList<MenuFrame> mfs) {
        RestaurantGreetingFrame.mfs = mfs;
    }


    class ImagePanel extends JPanel {
        private BufferedImage image;

        public ImagePanel(String imagePath) {
            try {
                // Load the image using ImageIO
                image = javax.imageio.ImageIO.read(new File(imagePath));
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Image file not found: " + imagePath);
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Check if the image is loaded
            if (image != null) {
                // Draw the image on the panel
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }

    public RestaurantGreetingFrame() {
        setTitle("Login");
        setSize(new Dimension(400, 350));
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        String imagePath = FilePath.getPIC_RESOURCES_PATH() + "burger-and-fries-picjumbo.jpeg";
        ImagePanel panel = new ImagePanel(imagePath);

        JLabel nameLabel = new JLabel("Endless Loop Of Flavors");
        nameLabel.setHorizontalAlignment(SwingConstants.HORIZONTAL);
        Font nameFont = new Font("Segoe UI Semibold", Font.PLAIN, 23);
        nameLabel.setFont(nameFont);
        nameLabel.setForeground(new Color(51, 51, 51));
        nameLabel.setBackground(new Color(255, 250, 255));

        Dimension size = nameLabel.getPreferredSize();
        nameLabel.setBounds(500, 100, size.width, size.height);

        panel.add(nameLabel);
        panel.setVisible(true);
        add(panel);

        setVisible(true);

            // Load the image
//            BufferedImage image = ImageIO.read(new File(imagePath));
//
//            // Create a JLabel to hold the image
//            JLabel imageLabel = new JLabel(new ImageIcon(image));
//
//            // Create a panel to hold the image label
//            JPanel imagePanel = new JPanel();
//            imagePanel.add(imageLabel);

            // Add the image panel to the main panel
//            mainPanel.add(panel); // Center the image panel

//        } catch (IOException e) {
//            e.printStackTrace();
//            // Handle the exception if the image fails to load (e.g., show a default message)
//            System.out.println("Error loading image: " + e.getMessage());
//        }

//        add(mainPanel); // Add the main panel to the frame
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Set to full screen


        JButton seeTheMenu = new JButton("SEE THE MENU");
        JPanel seeTheMenuPanel = new JPanel();
        seeTheMenuPanel.add(seeTheMenu);
        panel.add(seeTheMenuPanel, BorderLayout.SOUTH);  // Add the button panel to the SOUTH
        panel.add(nameLabel);
        panel.setVisible(true);
        setVisible(true);

//        Thread watcherThread = new Thread(new FileWatcher(FilePath.getDailyOrders()));
//        watcherThread.start();
//        setComponentZOrder(panel, 0);
//        setComponentZOrder(nameLabel, 1);
        seeTheMenu.addActionListener(e -> {
            Thread t1 = new Thread(() -> {

                MenuFrame mf = new MenuFrame(new User("", "", UserType.GUEST, 0));
                mf.reload();
                mfs.add(mf);
            });
            t1.start();

        });
    }


}