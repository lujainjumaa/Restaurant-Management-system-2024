package org.example.model;

public abstract class FilePath {
    private static final String RESOURCES_PATH = "src/main/resources/";
    private static final String menuItemsFilePath = RESOURCES_PATH + "MenuItemsFile.txt";
    private static final String typesAndIDs = RESOURCES_PATH + "TypesAndIDsOfItems.txt";
    private static final String accounts = RESOURCES_PATH + "Accounts.txt";
    private static final String orders = RESOURCES_PATH + "Orders.txt";
    private static final String dailyOrders = RESOURCES_PATH + "DailyOrders.txt";

    public static String getMenuItems() {
        return menuItemsFilePath;
    }

    public static String getDailyOrders() {
        return dailyOrders;
    }

    public static String getTypesAndIDs() {
        return typesAndIDs;
    }

    public static String getAccounts() {
        return accounts;
    }

    public static String getOrders() {
        return orders;
    }
}
