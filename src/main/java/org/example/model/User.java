package org.example.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class User {
    static List<User> users = new ArrayList<>();
    String userName;
    String password;
    UserType userType;
    int NumOfOrders;

    public User(String userName, String password, UserType userType, int numOfOrders) {
        this.userName = userName;
        this.password = password;
        this.userType = userType;
        this.NumOfOrders = numOfOrders;
        this.NumOfOrders = 0;
    }

    public int getNumOfOrders() {
        return NumOfOrders;
    }

    public void setNumOfOrders(int numOfOrders) {
        NumOfOrders = numOfOrders;
    }

    public static List<User> getUsers() {
        return users;
    }

    public static void setUsers(List<User> users) {
        User.users = users;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
    public static List<User> sortedUsersList(){
        List<User> users = User.getUsers();
        users.sort(Comparator.comparingInt(User::getNumOfOrders).reversed());
        return users;
    }
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", userType=" + userType +
                '}';
    }



}
