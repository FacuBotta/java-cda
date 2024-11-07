package com.models;

import java.sql.*;
import java.util.ArrayList;

public class Film extends AbstractModel {
    private int id;
    private String titre;
    private String date;
    private String description;

    private static Connection connexion = Requete.getConnexion();

    public Film() {}
    public Film(String titre, String date, String description) {
        this.titre = titre;
        this.date = date;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void add() {
        try {
            Statement stmt = connexion.createStatement();
            String insertQuery = "INSERT INTO films (titre, date, description) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connexion.prepareStatement(insertQuery);
            preparedStatement.setString(1, this.titre);
            preparedStatement.setString(2, this.date);
            preparedStatement.setString(3, this.description);
            preparedStatement.executeUpdate();

            System.out.println("Film added successfully into DB");
            stmt.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update() {
        try{
            Statement stmt = connexion.createStatement();
            String editQuery = "UPDATE films SET titre = ?, date = ?, description = ? WHERE id = ? LIMIT 1";
            PreparedStatement preparedStatement = connexion.prepareStatement(editQuery);
            preparedStatement.setString(1, this.titre);
            preparedStatement.setString(2, this.date);
            preparedStatement.setString(3, this.description);
            preparedStatement.setInt(4, this.id);
            preparedStatement.executeUpdate();

            stmt.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("Film updated successfully.");
    }

    @Override
    public void delete() {
        try{
            Statement stmt = connexion.createStatement();
            String deleteQuery = "DELETE FROM films WHERE titre = ?";
            PreparedStatement preparedStatement = connexion.prepareStatement(deleteQuery);
            preparedStatement.setString(1, this.titre);
            preparedStatement.executeUpdate();
            stmt.close();
            System.out.println("Film deleted successfully");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean find() {
        boolean filmExist = false;
        try{
            Statement stmt = connexion.createStatement();
            String query = "SELECT id FROM films WHERE titre = ?";
            PreparedStatement preparedStatement = connexion.prepareStatement(query);
            preparedStatement.setString(1, this.titre);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                if(rs.getString(1) != null) {
                    filmExist = true;
                }
            }
            stmt.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return filmExist;
    }
    public static ArrayList<Object> findAll() {
        try{
            ArrayList<Object> films = new ArrayList<>();
            Statement stmt = connexion.createStatement();
            String selectQuery = "SELECT id, titre, date, description FROM films";
            ResultSet rs = stmt.executeQuery(selectQuery);
            while (rs.next()) {
                if(rs.getString(1) != null) {
                    Film currentFilm = new Film(rs.getString("titre"), rs.getString("date"), rs.getString("description"));
                    currentFilm.setId(Integer.parseInt(rs.getString("id")));
                    films.add(currentFilm);
                }
            }
            stmt.close();
            return films;
        }
        catch(SQLException e){
            throw new RuntimeException("Error fetching films : ", e);
        }
    }

    public static Film findBy(String titre) {
        Film selectedFilm = null;
        try{
            Statement stmt = connexion.createStatement();
            String selectQuery = "SELECT id, titre, date, description FROM films WHERE titre = ?";
            PreparedStatement preparedStatement = connexion.prepareStatement(selectQuery);
            preparedStatement.setString(1, titre);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                if(rs.getString(1) != null){
                    selectedFilm = new Film();
                    selectedFilm.setId(rs.getInt("id"));
                    selectedFilm.setTitre(rs.getString("titre"));
                    selectedFilm.setDate(rs.getString("date"));
                    selectedFilm.setDescription(rs.getString("description"));
                } else {
                    throw new RuntimeException("Film not found");
                }
            }
            stmt.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return selectedFilm;
    }

    public String toString() {
        return "Id: " + this.id + " Titre : " + this.titre + " Date : " + this.date + " Description : " + this.description;
    }
}






















