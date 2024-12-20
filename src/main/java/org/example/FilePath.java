package org.example;

public abstract class FilePath {
    private static final String menu_items_file_path = "C:\\Users\\g\\IdeaProjects\\RestaurantCode4\\src\\main\\resources\\MenuItemsFile.txt";
    private static final String types_and_IDs_file_path = "C:\\Users\\g\\IdeaProjects\\RestaurantCode4\\src\\main\\resources\\TypesAndIDsOfItems.txt";

    public static String getMenu_items_file_path() {
        return menu_items_file_path;
    }

    public static String getTypes_and_IDs_file_path() {
        return types_and_IDs_file_path;
    }
}
