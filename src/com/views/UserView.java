package com.views;

import com.models.User;

import java.util.AbstractList;
import java.util.Scanner;

public class UserView {
    public static Scanner scanner = new Scanner(System.in);

    public static String userNom() {
        System.out.println("Nom : ");
        return scanner.nextLine();
    }
    public static String userPrenom() {
        System.out.println("Prenom : ");
        return scanner.nextLine();
    }
    public static String userEmail() {
        System.out.println("Email : ");
        return scanner.nextLine();
    }
    public static String userPassword() {
        System.out.println("Password : ");
        return scanner.nextLine();
    }
    public static void listUsers(AbstractList<Object> user) {
        System.out.println("Users :");
        for (Object u : user) {
            System.out.println(" - " + u);
        }
    }
    public static Scanner getScanner() {
        return scanner;
    }
}
