package org.example.model;

public class OrderItem {
    int itemID;
    int quantity;

    public OrderItem(int itemID, int quantity) {
        this.itemID = itemID;
        this.quantity = quantity;
    }

    public int getItemID() {
        return itemID;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "itemID=" + itemID +
                ", quantity=" + quantity +
                '}';
    }
}
