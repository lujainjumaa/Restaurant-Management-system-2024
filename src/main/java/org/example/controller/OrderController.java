package org.example.controller;

import org.example.model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OrderController {


    public static void addOrderToFile(Order order) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FilePath.getOrders(), true))) {
            writer.write(order.toString());
            writer.newLine();
            writer.write("--------------------------------------------------");
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Failed to write the order to file: " + e.getMessage());
        }
    }
    public static List<Order> loadOrders() {
        List<Order> orders = new ArrayList<>();
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
                    order.setID(id);
                } else if (order != null) {
                    if (line.startsWith("Order Type:")) {
                        order.setOrderType(OrderType.valueOf(line.split(":")[1].trim()));
                    } else if (line.startsWith("Address:")) {
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
                        order.setUser(new User(userName, "", UserType.valueOf(userType)));
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
        }
        return orders;
    }

}
