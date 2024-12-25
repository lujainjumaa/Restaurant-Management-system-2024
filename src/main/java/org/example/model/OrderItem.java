package org.example.model;

public class OrderItem {
    int itemID;
    int quantity;

    public OrderItem(int itemID, int quantity) {
        this.itemID = itemID;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "itemID=" + itemID +
                ", quantity=" + quantity +
                '}';
    }
}
