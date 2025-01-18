package org.example.controller;

import org.example.model.FilePath;
import org.example.model.User;
import org.example.model.UserType;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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
            int userTypeValue = -1;
            if (user.getUserType() == UserType.ADMIN) {
                userTypeValue = 1;
            } else if (user.getUserType() == UserType.EMPLOYEE) {
                userTypeValue = 2;
            }else if(user.getUserType() == UserType.CLIENT){
                userTypeValue=0;
            }
            String line = String.format(
                    "%s,%s,%d,%d",
                    user.getUserName(),
                    user.getPassword(),
                    userTypeValue,
                    user.getNumOfOrders()
            );
            writer.write(line);
            writer.newLine();
            writer.close();


        } catch (IOException e) {
            System.out.println("Error With The File....");
            e.printStackTrace();
        }
    }
    public static void removeUserFromFile(String username) {
        List<User> updatedUsers = new ArrayList<>();
        for (User user : User.getUsers()) {
            if (!user.getUserName().equals(username)) {
                updatedUsers.add(user);
            }
        }
        String filePath = FilePath.getAccounts();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (User user : updatedUsers) {
                addUserToFile(user);
            }
        } catch (IOException e) {
            System.out.println("filed to write remove user");
            e.printStackTrace();
        }
    }
}
