package org.example.model;

import org.example.view.MenuFrame;

import java.io.IOException;
import java.nio.file.*;

public class FileWatcher implements Runnable {
    private final Path fileToWatch;
    private final Path directoryToWatch;
    private MenuFrame mf;

    public FileWatcher(String filePath, MenuFrame mf) {
        this.mf = mf;
        this.fileToWatch = Paths.get(filePath); // The file to monitor
        this.directoryToWatch = fileToWatch.getParent(); // Get the directory containing the file
    }

    @Override
    public void run() {
        try {
            WatchService watchService = FileSystems.getDefault().newWatchService();

            // Register the directory for events
            directoryToWatch.register(watchService,
                    StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_MODIFY,
                    StandardWatchEventKinds.ENTRY_DELETE);

            System.out.println("Watching file: " + fileToWatch);

            while (true) {
                WatchKey key = watchService.take(); // Block until an event occurs

                for (WatchEvent<?> event : key.pollEvents()) {
                    Path eventFile = (Path) event.context();

                    // Check if the event is for the specific file
                    if (eventFile.equals(fileToWatch.getFileName())) {
                        WatchEvent.Kind<?> kind = event.kind();
                        System.out.println("Event: " + kind + " on file: " + fileToWatch);

                        if (kind == StandardWatchEventKinds.ENTRY_MODIFY) {
                            System.out.println("File modified: " + fileToWatch);
                            // Perform your custom action here
                            mf.reload();
                        }
                    }
                }

                // Reset the key to continue receiving events
                boolean valid = key.reset();
                if (!valid) {
                    System.out.println("WatchKey no longer valid. Exiting.");
                    break;
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
