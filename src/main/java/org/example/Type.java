package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Type {
    static int counter_of_types = 0;
    static List<Type> types_of_items = new ArrayList<>();
    int ID;
    String name;

    public Type(String name) {
        this.name = name;
        ID = counter_of_types++;
//        add_type_to_list(this);
    }

    public Type(int ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    public static List<Type> getTypes_of_items() {
        return types_of_items;
    }

    public static void setTypes_of_items(List<Type> types_of_items) {
        Type.types_of_items = types_of_items;
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

    public void setID(int ID) {
        this.ID = ID;
    }
    public static void add_type_to_list(Type type){
        types_of_items.add(type);
    }
    public static void add_type_to_file(Type type){
        String filePath = FilePath.getTypes_and_IDs_file_path();
        try {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
                String line = String.format(
                        "%d,%s",
                        type.getID(),
                        type.getName()
                );
                writer.write(line);
                writer.newLine();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void load_types(){
        String filePath = FilePath.getTypes_and_IDs_file_path();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while((line = reader.readLine())!=null){
                String[] parts = line.split(",");
                int ID = Integer.parseInt(parts[0]);
                String name = parts[1];
                Type type = new Type(ID, name);
                add_type_to_list(type);
                counter_of_types++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String get_type_name_from_typeID(int typeID) throws TypeNotFoundException{
        for(Type t : types_of_items){
            if(t.getID()==typeID){
                return t.getName();
            }
        }
        throw new TypeNotFoundException();
    }
}
