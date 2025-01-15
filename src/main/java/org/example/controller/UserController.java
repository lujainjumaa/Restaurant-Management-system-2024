package org.example.controller;

import org.example.model.FilePath;
import org.example.model.User;
import org.example.model.UserType;

import java.io.*;

public class UserController {

    public static void loadUsers(){
        String filePath = FilePath.getAccounts();
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            String line;
            while((line = reader.readLine())!=null){
                String[] parts = line.split(",");
                UserType userType;
                switch(parts[2]){
                    case "0" :
                        userType = UserType.CLIENT;
                        break;
                    case "1" :
                        userType = UserType.ADMIN;
                        break;
                    case "2" :
                        userType = UserType.EMPLOYEE;
                        break;
                    default:
                        System.out.println("wrong input");
                        userType = UserType.GUEST;
                        break;
                }
                User user = new User(parts[0],parts[1],userType,Integer.parseInt(parts[3]));
                User.getUsers().add(user);
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    public static void addUserToFile(User user){
        String filePath = FilePath.getAccounts();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            String line = String.format(
                    "%s,%s,%d,%d",
                    user.getUserName(),
                    user.getPassword(),
                    0,
                    0
            );
            writer.write(line);
            writer.newLine();
            writer.close();


        } catch (IOException e) {
            System.out.println("Error With The File....");
            e.printStackTrace();
        }
    }
}
