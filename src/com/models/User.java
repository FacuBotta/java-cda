package com.models;

import org.springframework.security.crypto.bcrypt.BCrypt;
import java.sql.*;
import java.util.ArrayList;

public class User extends AbstractModel {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private static Connection connexion = Requete.getConnexion();

    public User() {}
    public User(String nom, String prenom, String email, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void add() {
        try{
            Statement stmt = connexion.createStatement();
            String sql = "INSERT INTO users (nom, prenom, email, password)" + "VALUES (?, ?, ?, ?)";
            String hashedPass = BCrypt.hashpw(this.password, BCrypt.gensalt(10));
            PreparedStatement preparedStatement = connexion.prepareStatement(sql);

            preparedStatement.setString(1, this.nom);
            preparedStatement.setString(2, this.prenom);
            preparedStatement.setString(3, this.email);
            preparedStatement.setString(4, hashedPass);
            preparedStatement.executeUpdate();

            System.out.println("User added successfully int DB");
            stmt.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void update() {
        try{
            Statement stmt = connexion.createStatement();
            String editQuery = "UPDATE users SET nom = ?, prenom = ? WHERE email = ?";
            PreparedStatement preparedStatement = connexion.prepareStatement(editQuery);
            preparedStatement.setString(1, this.nom);
            preparedStatement.setString(2, this.prenom);
            preparedStatement.setString(3, this.email);
            preparedStatement.executeUpdate();

            stmt.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("User updated successfully.");
    }

    public void delete() {
        try {
            Statement stmt = connexion.createStatement();
            String deleteQuery = "DELETE FROM users WHERE email = ?";
            PreparedStatement preparedDelete = connexion.prepareStatement(deleteQuery);
            preparedDelete.setString(1, this.email);
            preparedDelete.executeUpdate();
            stmt.close();
            System.out.println("User deleted successfully");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean find() {
        boolean userExist = false;
        try{
            Statement stmt = connexion.createStatement();
            String sql = "SELECT id FROM users WHERE email = ?";
            PreparedStatement preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, this.email);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                if (rs.getString(1) != null) {
                    userExist = true;
                }
            }
            stmt.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return userExist;
    }

    public static ArrayList<Object> findAll() {
        try {
            ArrayList<Object> users = new ArrayList<>();
            Statement stmt = connexion.createStatement();
            String query = "SELECT * FROM users";
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()) {
                if (rs.getString(1) != null) {
                    User currentUser = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
                    currentUser.setId(Integer.parseInt(rs.getString("id")));
                    users.add(currentUser);
                }
            }
            stmt.close();
            return users;
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching users : ", e);
        }
    }

    public static User findBy(String email) {
        User selectedUser = null;
        try{
            Statement stmt = connexion.createStatement();
            String query = "SELECT * FROM users WHERE email = ?";
            PreparedStatement preparedSelect = connexion.prepareStatement(query);
            preparedSelect.setString(1, email);
            ResultSet rs = preparedSelect.executeQuery();

            while (rs.next()) {
                if (rs.getString(1) != null) {
                    selectedUser = new User();

                    selectedUser.setId(rs.getInt("id"));
                    selectedUser.setNom(rs.getString("nom"));
                    selectedUser.setPrenom(rs.getString("prenom"));
                    selectedUser.setEmail(rs.getString("email"));
                    selectedUser.setPassword(rs.getString("password"));
                } else {
                    throw new RuntimeException("The user does not exist");
                }
            }
            stmt.close();
        }
        catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
        return selectedUser;
    }

    public void changePassword(String password) {
        try {
            Statement stmt = connexion.createStatement();
            // Check password is equals...
            String selectQuery = "SELECT password FROM users WHERE email = ?";
            PreparedStatement preparedSelect = connexion.prepareStatement(selectQuery);
            preparedSelect.setString(1, this.email);
            ResultSet rs = preparedSelect.executeQuery();

            while (rs.next()) {
                if (rs.getString(1) != null) {
                    String oldPass = rs.getString("password");

                    if (BCrypt.checkpw(password, oldPass)) {
                        throw new RuntimeException("The password is the same!");
                    }
                }
            }
            // Updating pass...
            String newPassHashed = BCrypt.hashpw(password, BCrypt.gensalt(10));

            String updateQuery = "UPDATE users SET password = ? WHERE email = ?";
            PreparedStatement preparedUpdate = connexion.prepareStatement(updateQuery);
            preparedUpdate.setString(1, newPassHashed);
            preparedUpdate.setString(2, this.email);
            preparedUpdate.executeUpdate();

            stmt.close();
            this.password = password;
            System.out.println("Password changed successfully!");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void editUserByEmail(String email, String newNom, String newPrenom) {
        try {
            Statement stmt = connexion.createStatement();
            String updateQuery = "UPDATE users SET nom = ?, prenom = ? WHERE email = ?";
            PreparedStatement preparedUpdate = connexion.prepareStatement(updateQuery);

            preparedUpdate.setString(1, newNom);
            preparedUpdate.setString(2, newPrenom);
            preparedUpdate.setString(3, email);

            preparedUpdate.executeUpdate();
            stmt.close();
            System.out.println("User updated successfully");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        return "Id: " + this.id + " Nom : " + this.nom + " Prenom : " + this.prenom + " Email : " + this.email + " Password : " + this.password;
    }
}
