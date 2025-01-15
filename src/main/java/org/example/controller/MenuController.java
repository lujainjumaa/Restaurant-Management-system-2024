package org.example.controller;

import org.example.model.FilePath;
import org.example.model.ItemNotFoundException;
import org.example.model.MenuItem;
import org.example.model.User;
import org.example.view.MenuFrame;

import java.io.*;
import java.util.*;
import java.util.List;

public class MenuController {
    public static int menuItemsCounter = 0;
    public static int highestId = 0;
    MenuFrame mf;
    static Map<Integer, List<MenuItem>> menuItems = new HashMap<>();
    static List<MenuItem> menuItemsList = new ArrayList<>();

    public MenuController(Map<Integer, List<MenuItem>> menuItems) {
        MenuController.menuItems = menuItems;
    }

    public static Map<Integer, List<MenuItem>> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(Map<Integer, List<MenuItem>> menuItems) {
        MenuController.menuItems = menuItems;
    }

    public static List<MenuItem> getMenuItemsList() {
        return menuItemsList;
    }

    public static void setMenuItemsList(List<MenuItem> menuItemsList) {
        MenuController.menuItemsList = menuItemsList;
    }

    public MenuController() {
    }

    public static void loadMenu() {

        menuItems.clear();
        menuItemsList.clear();

        String filePath = FilePath.getMenuItems();
        try (BufferedReader read = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = read.readLine()) != null) {
                String[] parts = line.split(",");

                int id = Integer.parseInt(parts[0]);
                highestId = Math.max(highestId, id);
                int numOfOrders = Integer.parseInt(parts[1]);
                String name = parts[2];
                int type_ID = Integer.parseInt(parts[3]);
                String description = parts[4];
                double price = Double.parseDouble(parts[5]);
                boolean is_bestseller = Integer.parseInt(parts[6]) != 0;
                MenuItem item = new MenuItem(id ,numOfOrders, type_ID, name, description, price, is_bestseller);

                if (!menuItems.containsKey(type_ID)) {
                    menuItems.put(type_ID, new ArrayList<>());
                }
                menuItems.get(type_ID).add(item);
                menuItemsList.add(item);
                menuItemsCounter++;
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void addMenuItemToFile(MenuItem item, MenuFrame mf) {
        String filePath = FilePath.getMenuItems();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            String line = String.format(
                    "%d,%d,%s,%d,%s,%.2f,%d",
                    item.getID(),
                    0,
                    item.getName(),
                    item.getTypeID(),
                    item.getDescription(),
                    item.getPrice(),
                    item.isIs_bestseller() ? 1 : 0
            );
            writer.write(line);
            writer.newLine();
            writer.close();

//            if (!menu_items.containsKey(typeID)) {
//                menu_items.put(typeID, new ArrayList<>());
//            }
//            menu_items.get(typeID).add(item);
            System.out.println("Menu item added successfully: " + item.getName());

            mf.reload();


        } catch (IOException e) {
            System.out.println("Error With The File....");
            e.printStackTrace();
        }
    }

    public static void deleteMenuItemFromFile(int idToDelete, MenuFrame mf) {
        String filePath = FilePath.getMenuItems();
        File inputFile = new File(filePath);
        File tempFile = new File("tempFile.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.startsWith(String.valueOf(idToDelete))) {
                    continue;
                }
                writer.write(currentLine);
                writer.newLine();
            }
            writer.close();

        } catch (IOException e) {
            System.err.println("An error occurred while processing the file: " + e.getMessage());
        }

        if (inputFile.delete()) {
            if (!tempFile.renameTo(inputFile)) {
                System.err.println("Failed to rename temporary file.");
            }
        } else {
            System.err.println("Failed to delete the original file.");
        }
        mf.reload();
    }

    public int getTypeIDOfMenuItem(MenuItem item) throws ItemNotFoundException {
        for(Map.Entry<Integer,List<MenuItem>> entry: this.getMenuItems().entrySet()){
            int type_ID = entry.getKey();
            List<MenuItem> items = entry.getValue();
            for(MenuItem el_item : items){
                if(el_item.getID()==item.getID())
                    return type_ID;
            }
        }
        throw new ItemNotFoundException();
    }

    public static MenuItem getMenuItemFromID(int id) throws ItemNotFoundException {
        for (List<MenuItem> itemList : menuItems.values()) {
            for (MenuItem menuItem : itemList) {
                if (menuItem.getID() == id) {
                    return menuItem;
                }
            }
        }
        throw new ItemNotFoundException();
    }
    public static List<MenuItem> sortedMenuItemsList(){
        List<MenuItem> menuItems1 = MenuController.getMenuItemsList();
        menuItems1.sort(Comparator.comparingInt(MenuItem::getNumOfOrders).reversed());
        return menuItems1;
    }

}
