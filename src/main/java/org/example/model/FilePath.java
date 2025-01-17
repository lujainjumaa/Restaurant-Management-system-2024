package org.example.model;

public abstract class FilePath {
    private static final String menuItemsFilePath = "C:\\Users\\ajouk\\OneDrive\\Documents\\ideaProjects\\Restaurant_Management_System_With_Lujain\\Restaurant-Management-system-2024-4\\Restaurant-Management-system-2024\\src\\main\\resources\\MenuItemsFile.txt";
    private static final String typesAndIDs = "C:\\Users\\ajouk\\OneDrive\\Documents\\ideaProjects\\Restaurant_Management_System_With_Lujain\\Restaurant-Management-system-2024-4\\Restaurant-Management-system-2024\\src\\main\\resources\\TypesAndIDsOfItems.txt";
    private static final String accounts ="C:\\Users\\ajouk\\OneDrive\\Documents\\ideaProjects\\Restaurant_Management_System_With_Lujain\\Restaurant-Management-system-2024-4\\Restaurant-Management-system-2024\\src\\main\\resources\\Accounts.txt";
    private static final String orders="C:\\Users\\ajouk\\OneDrive\\Documents\\ideaProjects\\Restaurant_Management_System_With_Lujain\\Restaurant-Management-system-2024-4\\Restaurant-Management-system-2024\\src\\main\\resources\\Orders.txt";
    private static final String dailyOrders="C:\\Users\\ajouk\\OneDrive\\Documents\\ideaProjects\\Restaurant_Management_System_With_Lujain\\Restaurant-Management-system-2024-4\\Restaurant-Management-system-2024\\src\\main\\resources\\DailyOrders.txt";
    public static String getMenuItems() {
        return menuItemsFilePath;
    }
    public static String getDailyOrders() {
        return dailyOrders;
    }
    public static String getTypesAndIDs() {
        return typesAndIDs;
    }

    public static String getAccounts(){
        return accounts;
    }
    public static String getOrders(){
        return orders;
    }
}
