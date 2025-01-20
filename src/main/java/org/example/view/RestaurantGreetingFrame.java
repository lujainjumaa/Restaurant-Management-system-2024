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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
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
                image = javax.imageio.ImageIO.read(new File(imagePath));
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Image file not found: " + imagePath);
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            if (image != null) {
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
    public class riri extends JPanel {
        private Image backgroundImage;
        private boolean isRotating = false;
        private int angle = 0;

        public riri(Image backgroundImage) {
            this.backgroundImage = backgroundImage;
            setLayout(null);

            JLabel label = new JLabel("Loop Of Flavors");
            label.setForeground(Color.WHITE);
            label.setFont(new Font("Vivaldi", Font.BOLD, 95));
            label.setBounds(50, 330, 700, 150);

            JButton button = new JButton("Iterate Through The Loop") {
                @Override
                protected void paintComponent(Graphics g) {
                    if (isRotating) {
                        Graphics2D g2d = (Graphics2D) g.create();
                        int cx = getWidth() / 2;
                        int cy = getHeight() / 2;

                        AffineTransform old = g2d.getTransform();
                        g2d.rotate(Math.toRadians(angle), cx, cy);
                        super.paintComponent(g2d);
                        g2d.setTransform(old);
                        g2d.dispose();
                    } else {
                        super.paintComponent(g);
                    }
                }
            };

            button.setBounds(130, 500, 500, 120);
            button.setFocusPainted(false);
            button.setContentAreaFilled(false);
            button.setBorder(BorderFactory.createLineBorder(Color.WHITE));
            button.setForeground(Color.WHITE);
            button.setFont(new Font("Bookman Old Style", Font.BOLD, 25));

            Timer timer = new Timer(10, e -> {
                angle -= 5;
                if (angle <= -360) {
                    angle = 0;
                }
                button.repaint();
            });

            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    isRotating = true;
                    timer.start();
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    isRotating = false;
                    timer.stop();
                    angle = 0; // Reset angle
                    button.repaint();
                }
            });
            button.addActionListener(e -> {
            Thread t1 = new Thread(() -> {

                MenuFrame mf = new MenuFrame(new User("", "", UserType.GUEST, 0));
                mf.reload();
                mfs.add(mf);
            });
            t1.start();

        });
            add(label);
            add(button);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }

        public RestaurantGreetingFrame() {
            Image image = Toolkit.getDefaultToolkit().getImage(FilePath.getPIC_RESOURCES_PATH() + "burger-and-fries-picjumbo.jpeg");


            JPanel panel = new riri(image);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            add(panel);
            setExtendedState(JFrame.MAXIMIZED_BOTH);
            setVisible(true);


//        setTitle("Login");
//        setSize(new Dimension(400, 350));
//        setLayout(new BorderLayout());
//        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        setLocationRelativeTo(null);
//        setResizable(false);

//        setExtendedState(JFrame.MAXIMIZED_BOTH);

//        String imagePath = FilePath.getPIC_RESOURCES_PATH() + "burger-and-fries-picjumbo.jpeg";
//        ImagePanel panel = new ImagePanel(imagePath);
//
//        JLabel nameLabel = new JLabel("Endless Loop Of Flavors");
//        nameLabel.setHorizontalAlignment(SwingConstants.HORIZONTAL);
//        Font nameFont = new Font("Segoe UI Semibold", Font.PLAIN, 23);
//        nameLabel.setFont(nameFont);
//        nameLabel.setForeground(new Color(51, 51, 51));
//        nameLabel.setBackground(new Color(255, 250, 255));
//
//        Dimension size = nameLabel.getPreferredSize();
//        nameLabel.setBounds(500, 100, size.width, size.height);
//
//        panel.add(nameLabel);
//        panel.setVisible(true);
//        add(panel);
//
//        setVisible(true);
//
//            // Load the image
////            BufferedImage image = ImageIO.read(new File(imagePath));
////
////            // Create a JLabel to hold the image
////            JLabel imageLabel = new JLabel(new ImageIcon(image));
////
////            // Create a panel to hold the image label
////            JPanel imagePanel = new JPanel();
////            imagePanel.add(imageLabel);
//
//            // Add the image panel to the main panel
////            mainPanel.add(panel); // Center the image panel
//
////        } catch (IOException e) {
////            e.printStackTrace();
////            // Handle the exception if the image fails to load (e.g., show a default message)
////            System.out.println("Error loading image: " + e.getMessage());
////        }
//
////        add(mainPanel); // Add the main panel to the frame
//        setExtendedState(JFrame.MAXIMIZED_BOTH); // Set to full screen
//
//
//        JButton seeTheMenu = new JButton("SEE THE MENU");
//        JPanel seeTheMenuPanel = new JPanel();
//        seeTheMenuPanel.add(seeTheMenu);
//        panel.add(seeTheMenuPanel, BorderLayout.SOUTH);  // Add the button panel to the SOUTH
//        panel.add(nameLabel);
//        panel.setVisible(true);
//        setVisible(true);
//
////        Thread watcherThread = new Thread(new FileWatcher(FilePath.getDailyOrders()));
////        watcherThread.start();
////        setComponentZOrder(panel, 0);
////        setComponentZOrder(nameLabel, 1);
//        seeTheMenu.addActionListener(e -> {
//            Thread t1 = new Thread(() -> {
//
//                MenuFrame mf = new MenuFrame(new User("", "", UserType.GUEST, 0));
//                mf.reload();
//                mfs.add(mf);
//            });
//            t1.start();
//
//        });
    }
}