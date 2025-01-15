package org.example.model;

import org.example.controller.MenuController;

public class MenuItem {
    int ID;
    int numOfOrders;
    int typeID;
    String name;
    String description;

    public int getTypeID() {
        return typeID;
    }
    double price;
    boolean is_bestseller;

    public MenuItem(int ID, int numOfOrders, int typeID, String name, String description, double price, boolean is_bestseller) {
        this.ID = ID;
        this.numOfOrders = numOfOrders;
        this.typeID = typeID;
        this.name = name;
        this.description = description;
        this.price = price;
        this.is_bestseller = is_bestseller;
    }

    public int getNumOfOrders() {
        return numOfOrders;
    }

    public void setNumOfOrders(int numOfOrders) {
        this.numOfOrders = numOfOrders;
    }

    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isIs_bestseller() {
        return is_bestseller;
    }

    public void setIs_bestseller(boolean is_bestseller) {
        this.is_bestseller = is_bestseller;
    }

    public String toString() {
        return "MenuItem{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", is_bestseller=" + is_bestseller +
                '}';
    }
}

