package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Type {
    static int CounterOfTypes = 0;
    static List<Type> typesOfItems = new ArrayList<>();
    public int ID;
    String name;

    public static void increaseCounterOfTypes() {
        CounterOfTypes++;
    }

    public Type(String name) {
        this.name = name;
        ID = ++CounterOfTypes;
//        add_type_to_list(this);
    }

    public Type(int ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    public static List<Type> getTypesOfItems() {
        return typesOfItems;
    }

    public static void setTypesOfItems(List<Type> typesOfItems) {
        Type.typesOfItems = typesOfItems;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public static int getCounterOfTypes() {
        return CounterOfTypes;
    }

    public void setID(int ID) {
        this.ID = ID;
    }


}
