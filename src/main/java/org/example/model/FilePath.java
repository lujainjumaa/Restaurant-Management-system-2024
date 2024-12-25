package org.example.model;

public abstract class FilePath {
    private static final String menuItemsFilePath = "C:\\Users\\g\\IdeaProjects\\RestaurantCode4\\src\\main\\resources\\MenuItemsFile.txt";
    private static final String typesAndIDs = "C:\\Users\\g\\IdeaProjects\\RestaurantCode4\\src\\main\\resources\\TypesAndIDsOfItems.txt";
    private static final String accounts ="C:\\Users\\g\\IdeaProjects\\RestaurantCode4\\src\\main\\resources\\Accounts.txt";

    public static String getMenuItems() {
        return menuItemsFilePath;
    }

    public static String getTypesAndIDs() {
        return typesAndIDs;
    }

    public static String getAccounts(){
        return accounts;
    }
}
