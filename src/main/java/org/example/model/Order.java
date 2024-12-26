package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    List<OrderItem> orderItems = new ArrayList<>();
    OrderType orderType;
    String address;
    double price;
    double tip;
    OrderStatus orderStatus;
    User user;

    public void addToOrderItems(OrderItem item){
        orderItems.add(item);
    }
    public List<OrderItem> getOrderItems() {
        return orderItems;
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order Details:\n");
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
