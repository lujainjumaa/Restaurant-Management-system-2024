package org.example.controller;

import org.example.model.FilePath;
import org.example.model.Type;
import org.example.model.TypeNotFoundException;

import java.io.*;

public class TypeController {
    public static void addTypeToList(Type type){
        Type.getTypesOfItems().add(type);
    }

    public static void addTypeToFile(Type type){
        String filePath = FilePath.getTypesAndIDs();
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
    public static void loadTypes(){
        String filePath = FilePath.getTypesAndIDs();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while((line = reader.readLine())!=null){
                String[] parts = line.split(",");
                int ID = Integer.parseInt(parts[0]);
                String name = parts[1];
                Type type = new Type(ID, name);
                addTypeToList(type);
                Type.increaseCounterOfTypes();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String getTypeNameFromTypeID(int typeID) throws TypeNotFoundException {
        for(Type t : Type.getTypesOfItems()){
            if(t.getID()==typeID){
                return t.getName();
            }
        }
        throw new TypeNotFoundException();
    }
}
