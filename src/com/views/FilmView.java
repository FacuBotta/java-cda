package com.views;

import java.util.AbstractList;
import java.util.Scanner;

public class FilmView {
    public static Scanner scanner = new Scanner(System.in);

    public static String filmTitre() {
        System.out.println("Titre : ");
        return scanner.nextLine();
    }
    public static String filmDate() {
        System.out.println("Date : ");
        return scanner.nextLine();
    }
    public static String filmDescription() {
        System.out.println("Description : ");
        return scanner.nextLine();
    }
    public static void listFilms(AbstractList<Object> films) {
        System.out.println("Films :");
        for (Object u : films) {
            System.out.println(" - " + u);
        }
    }
    public static Scanner getScanner() {
        return scanner;
    }
}
