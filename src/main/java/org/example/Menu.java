package org.example;

import javax.swing.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class Menu {
    static int menu_items_counter = 0;
    static int highest_id = 0;

    static Map<Integer, List<MenuItem>> menu_items = new HashMap<>();

    public Menu(Map<Integer, List<MenuItem>> menuItems) {
        menu_items = menuItems;
    }

    public static Map<Integer, List<MenuItem>> getMenuItems() {
        return menu_items;
    }

    public void setMenuItems(Map<Integer, List<MenuItem>> menuItems) {
        menu_items = menuItems;
    }

    public Menu() {
    }

    static void loadMenu() {

        menu_items.clear();

        String filePath = FilePath.getMenu_items_file_path();
        try (BufferedReader read = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = read.readLine()) != null) {
                String[] parts = line.split(",");

                int id = Integer.parseInt(parts[0]);
                highest_id = Math.max(highest_id, id);

                String name = parts[1];
                int type_ID = Integer.parseInt(parts[2]);
                String description = parts[3];
                double price = Double.parseDouble(parts[4]);
                boolean is_bestseller = Integer.parseInt(parts[5]) != 0;
                MenuItem item = new MenuItem(id,type_ID, name, description, price, is_bestseller);

                if (!menu_items.containsKey(type_ID)) {
                    menu_items.put(type_ID, new ArrayList<>());
                }
                menu_items.get(type_ID).add(item);
                menu_items_counter++;
            }

            read.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void add_menu_item_to_file(MenuItem item, int type_ID) {
        String filePath = FilePath.getMenu_items_file_path();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            String line = String.format(
                    "%d,%s,%d,%s,%.2f,%d",
                    item.getID(),
                    item.getName(),
                    item.typeID,
                    item.getDescription(),
                    item.getPrice(),
                    item.isIs_bestseller() ? 1 : 0
            );
            writer.write(line);
            writer.newLine();
            writer.close();

//            if (!menu_items.containsKey(type_ID)) {
//                menu_items.put(type_ID, new ArrayList<>());
//            }
//            menu_items.get(type_ID).add(item);
            System.out.println("Menu item added successfully: " + item.getName());

            MenuView.getInstance().reload();


        } catch (IOException e) {
            System.out.println("Error With The File....");
            e.printStackTrace();
        }
    }

    public static void edit_menu_item(MenuItem item) {
        EditMenuItemFrame editFrame = new EditMenuItemFrame(item, 1);

        MenuView.getInstance().reload();
    }

    public static void delete_menu_item_from_file(int idToDelete) {
        String filePath = FilePath.getMenu_items_file_path();
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
        MenuView.getInstance().reload();
    }
    public void delete_menu_item_from_file(MenuItem item){

    }
    public void delete_menu_item_from_map(MenuItem item){

    }
    public void delete_menu_item_from_map(int id){

    }

    public int get_type_ID_of_menu_item(MenuItem item) throws ItemNotFoundException {
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

    public MenuItem get_menu_item_from_ID(int id) throws ItemNotFoundException {
        for (List<MenuItem> itemList : menu_items.values()) {
            for (MenuItem menuItem : itemList) {
                if (menuItem.getID() == id) {
                    return menuItem;
                }
            }
        }
        throw new ItemNotFoundException();
    }

}
