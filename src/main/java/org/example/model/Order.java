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

}
