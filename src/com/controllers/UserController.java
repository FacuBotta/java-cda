package com.controllers;

import com.models.User;
import com.views.UserView;

public class UserController {
    public static void addUser() {

        String userNom = UserView.userNom();
        String userPrenom = UserView.userPrenom();
        String userEmail = UserView.userEmail();
        String userPassword = UserView.userPassword();

        if (userNom.equals("") || userPrenom.equals("") || userEmail.equals("") || userPassword.equals("")) {
            System.out.println("All input are required");
            return;
        }

        User newUser = new User(userNom, userPrenom, userEmail, userPassword);
        if (!newUser.find()) {
            newUser.add();
        } else {
            System.out.println("The email is already in use");
        }
    }
    public static void updateUser() {
        String userEmail = UserView.userEmail();
        User selectedUser = User.findBy(userEmail);
        if (selectedUser != null) {
            String userNom = UserView.userNom();
            String userPrenom = UserView.userPrenom();
            if (selectedUser.getNom().equals(userNom) && selectedUser.getPrenom().equals(userPrenom)) {
                System.out.println("Noting to update");
                return;
            }
            selectedUser.setNom(userNom);
            selectedUser.setPrenom(userPrenom);

            selectedUser.update();
        } else {
            System.out.println("User not found");
        }

    }
    public static void showUser() {
        String userEmail = UserView.userEmail();
        Object selectedUser = User.findBy(userEmail);
        if (selectedUser != null) {
            System.out.println(selectedUser.toString());
        } else {
            System.out.println("User not found");
        }
    }
    public static void deleteUser() {
        String userEmail = UserView.userEmail();
        User selectedUser = User.findBy(userEmail);
        if (selectedUser != null) {
            selectedUser.delete();
        } else {
            System.out.println("User not found");
        }
    }
    public static void showAllUsers() {
        UserView.listUsers(User.findAll());
    }
}