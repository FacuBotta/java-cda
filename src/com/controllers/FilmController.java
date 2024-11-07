package com.controllers;

import com.models.Film;
import com.views.FilmView;

public class FilmController {
    public static void addFilm() {
        String filmTitre = FilmView.filmTitre();
        String filmDate = FilmView.filmDate();
        String filmDescription = FilmView.filmDescription();

    if (filmTitre.equals("") || filmDate.equals("") || filmDescription.equals("")) {
        System.out.println("All inputs are required");
        return;
    }

    Film newFilm = new Film(filmTitre, filmDate, filmDescription);
    if (!newFilm.find()) {
        newFilm.add();
    } else {
        System.out.println("Film already exists");
    }


    }

    public static void updateFilm() {
        String filmTitre = FilmView.filmTitre();
        Film selectedFilm = Film.findBy(filmTitre);
        if (selectedFilm != null) {
            String newFilmTitre = FilmView.filmTitre();
            String NewFilmDate = FilmView.filmDate();
            String newFilmDescription = FilmView.filmDescription();

            selectedFilm.setTitre(newFilmTitre);
            selectedFilm.setDate(NewFilmDate);
            selectedFilm.setDescription(newFilmDescription);

            selectedFilm.update();
        } else {
            System.out.println("Film not found");
        }
    }
    public static void showFilm() {
        String filmTitre = FilmView.filmTitre();
        Film selectedFilm = Film.findBy(filmTitre);
        if (selectedFilm != null) {
            System.out.println(selectedFilm.toString());
        } else {
            System.out.println("Film not found");
        }
    }
    public static void deleteFilm() {
        String filmTitre = FilmView.filmTitre();
        Film selectedFilm = Film.findBy(filmTitre);
        if (selectedFilm != null) {
            selectedFilm.delete();
        } else {
            System.out.println("Film not found");
        }
    }
    public static void showAllFilms() {
        FilmView.listFilms(Film.findAll());
    }
}
