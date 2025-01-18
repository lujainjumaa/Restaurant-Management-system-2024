package org.example.model;//package org.example.model;
//
//import org.example.view.MenuFrame;
//import org.example.view.RestaurantGreetingFrame;
//
//import java.io.IOException;
//import java.nio.file.*;
//
//public class FileWatcher implements Runnable {
//    private final Path fileToWatch;
//    private final Path directoryToWatch;
//    private MenuFrame mf;
//
//    public FileWatcher(String filePath) {
//        this.fileToWatch = Paths.get(filePath); // The file to monitor
//        this.directoryToWatch = fileToWatch.getParent(); // Get the directory containing the file
//    }
//
//    @Override
//    public void run() {
//        try {
//            WatchService watchService = FileSystems.getDefault().newWatchService();
//
//            // Register the directory for events
//            directoryToWatch.register(watchService,
//                    StandardWatchEventKinds.ENTRY_CREATE,
//                    StandardWatchEventKinds.ENTRY_MODIFY,
//                    StandardWatchEventKinds.ENTRY_DELETE);
//
//            System.out.println("Watching file: " + fileToWatch);
//
//            while (true) {
//                WatchKey key = watchService.take(); // Block until an event occurs
//
//                for (WatchEvent<?> event : key.pollEvents()) {
//                    Path eventFile = (Path) event.context();
//
//                    // Check if the event is for the specific file
//                    if (eventFile.equals(fileToWatch.getFileName())) {
//                        WatchEvent.Kind<?> kind = event.kind();
//                        System.out.println("Event: " + kind + " on file: " + fileToWatch);
//
//                        if (kind == StandardWatchEventKinds.ENTRY_MODIFY) {
//                            System.out.println("File modified: " + fileToWatch);
//                            // Perform your custom action here
//                            for(MenuFrame mf : RestaurantGreetingFrame.getMfs()){
//                                mf.reload();
//                            }
//                        }
//                    }
//                }
//
//                // Reset the key to continue receiving events
//                boolean valid = key.reset();
//                if (!valid) {
//                    System.out.println("WatchKey no longer valid. Exiting.");
//                    break;
//                }
//            }
//        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//}
import javax.swing.*;
import java.awt.*;

public class sss extends JFrame {
    private JPanel cardPanel;
    private CardLayout cardLayout;

    public sss() {
        setTitle("Panel Switcher Example");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create the CardLayout and the panel that will hold the cards
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Create the first panel
        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.LIGHT_GRAY);
        JButton button1 = new JButton("Go to Panel 2");
        button1.addActionListener(e -> cardLayout.show(cardPanel, "Panel2")); // Switch to Panel 2
        panel1.add(button1);

        // Create the second panel
        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.ORANGE);
        JButton button2 = new JButton("Go to Panel 1");
        button2.addActionListener(e -> cardLayout.show(cardPanel, "Panel1")); // Switch to Panel 1
        panel2.add(button2);

        // Add panels to the cardPanel
        cardPanel.add(panel1, "Panel1");
        cardPanel.add(panel2, "Panel2");

        // Add the cardPanel to the frame
        add(cardPanel);

        // Show the first panel by default
        cardLayout.show(cardPanel, "Panel1");
    }

}