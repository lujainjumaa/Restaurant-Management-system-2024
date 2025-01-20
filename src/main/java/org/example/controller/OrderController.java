package org.example.controller;

import org.example.model.*;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderController {
    static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    static List<Order> orders = new ArrayList<>();
    static List<Order> dailyOrders = new ArrayList<>();

    public static List<Order> getOrders() {
        return orders;
    }

    public static void setOrders(List<Order> orders) {
        OrderController.orders = orders;
    }

    public static List<Order> getDailyOrders() {
        return dailyOrders;
    }

    public static void setDailyOrders(List<Order> dailyOrders) {
        OrderController.dailyOrders = dailyOrders;
    }

    public static void addOrderToFile(Order order, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(order.toString());
            writer.newLine();
            writer.write("--------------------------------------------------");
            writer.newLine();
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.err.println("Failed to write the order to file: " + e.getMessage());
        }
    }
    public static void loadOrders() {
//        List<Order> orders = new ArrayList<>();
        orders.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(FilePath.getOrders()))) {
            String line;
            Order order = null;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Order ID:")) {
                    if (order != null) {
                        orders.add(order);
                    }
                    order = new Order();
                    int id = Integer.parseInt(line.split(":")[1].trim());
                    Order.setHighestID(Math.max(Order.getHighestID(),id));
                    order.setID(id);
                } else if (order != null) {
                    if (line.startsWith("Order Type:")) {
                        order.setOrderType(OrderType.valueOf(line.split(":")[1].trim()));
                    }
                    else if (line.startsWith("Order Date:")) {
                        String dateStr = line.split(":")[1].trim();
                        order.setOrderDate(dateFormat.parse(dateStr));
                    }
                    else if (line.startsWith("Address:")) {
                        order.setAddress(line.split(":")[1].trim());
                    } else if (line.startsWith("Total Price:")) {
                        order.setPrice(Double.parseDouble(line.split(":")[1].trim().replace("$", "")));
                    } else if (line.startsWith("Tip:")) {
                        order.setTip(Double.parseDouble(line.split(":")[1].trim().replace("$", "")));
                    } else if (line.startsWith("Order Status:")) {
                        String status = line.split(":")[1].trim();
                        order.setOrderStatus(status.equals("null") ? null : OrderStatus.valueOf(status));
                    } else if (line.startsWith("User:")) {
                        String userData = line.substring(line.indexOf('{') + 1, line.lastIndexOf('}'));
                        String[] userFields = userData.split(", ");
                        String userName = userFields[0].split("=")[1].trim().replace("'", "");
                        String userType = userFields[1].split("=")[1].trim();
                        order.setUser(new User(userName, "", UserType.valueOf(userType),0));
                    } else if (line.startsWith("Order Items:")) {
                        order.setOrderItems(new ArrayList<>());
                    } else if (line.startsWith(" - ")) {
                        String itemData = line.substring(line.indexOf('{') + 1, line.lastIndexOf('}'));
                        String[] itemFields = itemData.split(", ");
                        int itemID = Integer.parseInt(itemFields[0].split("=")[1].trim());
                        int quantity = Integer.parseInt(itemFields[1].split("=")[1].trim());
                        order.addToOrderItems(new OrderItem(itemID, quantity));
                    }
                }
            }
            if (order != null) {
                orders.add(order);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
//        return orders;
    }
    public static void loadDailyOrders() {
//        List<Order> orders = new ArrayList<>();
        dailyOrders.clear();
        if(!OrderController.getDateDailyOrder().equals(Order.formatToLocalDate(new Date()))){
            OrderController.clearFileContent(FilePath.getDailyOrders());
            OrderController.addDateToDailyOrder();
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(FilePath.getDailyOrders()))) {
            String line = reader.readLine();
            Order order = null;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Order ID:")) {
                    if (order != null) {
                        dailyOrders.add(order);
                    }
                    order = new Order();
                    int id = Integer.parseInt(line.split(":")[1].trim());
                    order.setHighestID(Math.max(order.getHighestID(),id));
                    order.setID(id);
                } else if (order != null) {
                    if (line.startsWith("Order Type:")) {
                        order.setOrderType(OrderType.valueOf(line.split(":")[1].trim()));
                    }
                    else if (line.startsWith("Order Date:")) {
                        String dateStr = line.split(":")[1].trim();
                        System.out.println("order date : " + dateStr);
                        order.setOrderDate(dateFormat.parse(dateStr));
                    }
                    else if (line.startsWith("Address:")) {
                        order.setAddress(line.split(":")[1].trim());
                    } else if (line.startsWith("Total Price:")) {
                        order.setPrice(Double.parseDouble(line.split(":")[1].trim().replace("$", "")));
                    } else if (line.startsWith("Tip:")) {
                        order.setTip(Double.parseDouble(line.split(":")[1].trim().replace("$", "")));
                    } else if (line.startsWith("Order Status:")) {
                        String status = line.split(":")[1].trim();
                        order.setOrderStatus(status.equals("null") ? null : OrderStatus.valueOf(status));
                    } else if (line.startsWith("User:")) {
                        String userData = line.substring(line.indexOf('{') + 1, line.lastIndexOf('}'));
                        String[] userFields = userData.split(", ");
                        String userName = userFields[0].split("=")[1].trim().replace("'", "");
                        String userType = userFields[1].split("=")[1].trim();
                        order.setUser(new User(userName, "", UserType.valueOf(userType),0));
                    } else if (line.startsWith("Order Items:")) {
                        order.setOrderItems(new ArrayList<>());
                    } else if (line.startsWith(" - ")) {
                        String itemData = line.substring(line.indexOf('{') + 1, line.lastIndexOf('}'));
                        String[] itemFields = itemData.split(", ");
                        int itemID = Integer.parseInt(itemFields[0].split("=")[1].trim());
                        int quantity = Integer.parseInt(itemFields[1].split("=")[1].trim());
                        order.addToOrderItems(new OrderItem(itemID, quantity));
                    }
                }
            }
            if (order != null) {
                dailyOrders.add(order);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
//        return orders;
    }
    public static void addDateToDailyOrder(){
        StringBuilder sb = new StringBuilder();
        Date dayDate=new Date();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FilePath.getDailyOrders(), true))) {
            writer.write(String.valueOf(sb.append(dateFormat.format(dayDate)).append("\n")));
        } catch (IOException e) {
            System.err.println("Failed to write the Date to file: " + e.getMessage());
        }
    }
    public static String getDateDailyOrder(){
        String line = "";
        try(BufferedReader reader=new BufferedReader(new FileReader(FilePath.getDailyOrders()))){
            line=reader.readLine();
        }
        catch (IOException e){
            System.out.println("failed to return daily date");
        }
        return line;
    }
    public static void clearFileContent(String filePath) {
        File file = new File(filePath);
        file.delete();
        try {
            if (file.createNewFile()) {
                System.out.println("File content cleared successfully");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static LocalDate convertToLocalDate(Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
    public static double getDailyProfits(){
        loadDailyOrders();
        double dailyProfits = 0;
        for(Order order:dailyOrders){
            dailyProfits+=order.getPrice();
        }
        return dailyProfits;
    }
    public static double getWeeklyProfits(){
        loadOrders();
        double dailyProfits = 0;
        LocalDate currentDate = convertToLocalDate(new Date());
        for(Order order:orders){
            System.out.println();
            System.out.println(order);
            LocalDate orderDate = convertToLocalDate(order.getDate());
            if (ChronoUnit.DAYS.between(orderDate, currentDate) <= 7) {
                System.out.println(orderDate+"order date");
                dailyProfits += order.getPrice();
            }
        }
        return dailyProfits;
    }
    public static List<Double> calculateDailyProfitsForLastWeek() {
        List<Double> dailyProfitsList = new ArrayList<>();
        LocalDate currentDate = LocalDate.now();
        loadOrders();

        for (int i = 0; i < 7; i++) {
            LocalDate targetDate = currentDate.minusDays(i);
            double dailyProfit = 0;

            for (Order order : orders) {
                LocalDate orderDate = convertToLocalDate(order.getDate());
                if (orderDate.isEqual(targetDate)) {
                    dailyProfit += order.getPrice();
                }
            }
            dailyProfitsList.add(dailyProfit);
        }

        return dailyProfitsList;
    }

    public static List calculateDailyOrdersForLastWeek(){
        List<Double> dailyOrdersList = new ArrayList<>();
        LocalDate currentDate = LocalDate.now();
        loadOrders();

        for (int i = 0; i < 7; i++) {
            LocalDate targetDate = currentDate.minusDays(i);
            double dailyOrder = 0;

            for (Order order : orders) {
                LocalDate orderDate = convertToLocalDate(order.getDate());
                if (orderDate.isEqual(targetDate)) {
                    dailyOrder ++;
                }
            }
            dailyOrdersList.add(dailyOrder);
        }

        return dailyOrdersList;
    }

    public static void updateOrderStatus(int orderId, String newStatus) {
        try {
            loadDailyOrders();
            boolean isOrderFound = false;
            for (Order order : dailyOrders) {
                if (order.getID() == orderId) {
                    isOrderFound = true;
                    try {
                        order.setOrderStatus(OrderStatus.valueOf(newStatus));
                    } catch (IllegalArgumentException e) {
                        System.err.println("Invalid status" + newStatus);
                        return;
                    }
                    break;
                }
            }
            if (!isOrderFound) {
                System.out.println(orderId + " not found");
                return;
            }
            clearFileContent(FilePath.getDailyOrders());
            addDateToDailyOrder();
            for (Order order : dailyOrders) {
                addOrderToFile(order, FilePath.getDailyOrders());
            }
            System.out.println(orderId+newStatus);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed to update order status " + e.getMessage());
        }
    }
    public static boolean cancelOrder(Order order){
        if(order.getOrderStatus().equals(OrderStatus.PENDING)){
            deleteOrderById(order.getID());
            return true;
        }
        return false;
    }
    public static void deleteOrderById(int orderId) {
        try {
            loadDailyOrders();
            boolean isOrderFound = false;
            for (Order order : dailyOrders) {
                if (order.getID() == orderId) {
                    isOrderFound = true;
                    dailyOrders.remove(order);
                    break;
                }
            }
            if (isOrderFound) {
                clearFileContent(FilePath.getDailyOrders());
                addDateToDailyOrder();
                for (Order order : dailyOrders) {
                    addOrderToFile(order, FilePath.getDailyOrders());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed to delete order: " + e.getMessage());
        }
    }
}
