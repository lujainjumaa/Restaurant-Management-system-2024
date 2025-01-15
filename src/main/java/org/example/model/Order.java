package org.example.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Order {
    static int highestID;
    int ID;
    List<OrderItem> orderItems = new ArrayList<>();
    OrderType orderType;
    String address;
    double price;
    double tip;
    OrderStatus orderStatus;
    Date orderDate;

public Order(){
    ID=highestID;
    orderDate=new Date();
}
    public void setHighestID(int highestID) {
        this.highestID = highestID;
    }

    User user;

    public int getHighestID() {
        return highestID;
    }

    public void setID(int ID) {
        this.ID = ++highestID;
    }
    public int getID() {
        return ID;
    }

    public void addToOrderItems(OrderItem item){
        orderItems.add(item);
    }
    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public String getOrderDate() {
        return formatToLocalDate(orderDate);
    }


    public static String formatToLocalDate(Date date) {
        SimpleDateFormat localDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return localDateFormat.format(date);
    }
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        sb.append("Order Details:\n");
        sb.append("Order ID: ").append(getID()).append("\n");
        sb.append("Order Date: ").append(orderDate != null ? dateFormat.format(orderDate) : "N/A").append("\n");
        sb.append("Order Type: ").append(orderType).append("\n");
        sb.append("Address: ").append(address == null ? "N/A" : address).append("\n");
        sb.append("Total Price: $").append(price).append("\n");
        sb.append("Tip: $").append(tip).append("\n");
        sb.append("Order Status: ").append(orderStatus).append("\n");
        sb.append("User: ").append(user == null ? "Guest" : user).append("\n");
        sb.append("Order Items:\n");
        for (OrderItem item : orderItems) {
            sb.append(" - ").append(item).append("\n");
        }
        return sb.toString();
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setTip(double tip) {
        this.tip = tip;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public String getAddress() {
        return address;
    }

    public double getPrice() {
        return price;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public double getTip() {
        return tip;
    }

    public User getUser() {
        return user;
    }
}
