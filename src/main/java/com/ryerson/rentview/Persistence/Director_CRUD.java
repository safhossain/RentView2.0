package com.ryerson.rentview.Persistence;

import com.ryerson.rentview.Helper.DirectorInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

public class Director_CRUD extends Base_CRUD {

    /************************************* CRUD OPERATIONS ********************************************/ 
    public static void createDirector(String firstName, String lastName) {
        Connection con = getCon();
        try {
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO DIRECTOR (first_name, last_name) VALUES (?, ?)");
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.executeUpdate();
            con.close();
        } catch (SQLException e) {
            System.out.println("createDirector Insert failed: " + e);
        }
    }

    public static DirectorInfo readDirector(int directorID) {
        Connection con = getCon();
        try {
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM DIRECTOR WHERE director_ID = ?");
            pstmt.setInt(1, directorID);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new DirectorInfo(
                        rs.getInt("director_ID"),
                        rs.getString("first_name"),
                        rs.getString("last_name")
                );
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Query failed: " + e);
        }
        return null;
    }

    public static void updateDirector(int directorID, String attributeName, String newValue) {
        Connection con = getCon();
        try {
            PreparedStatement pstmt = con.prepareStatement("UPDATE DIRECTOR SET " + attributeName + " = ? WHERE director_ID = ?");
            pstmt.setString(1, newValue);
            pstmt.setInt(2, directorID);
            pstmt.executeUpdate();
            con.close();
        } catch (SQLException e) {
            System.out.println("Update failed: " + e);
        }
    }

    public static void deleteDirector(int directorID) {
        Connection con = getCon();
        try {
            PreparedStatement pstmt = con.prepareStatement("DELETE FROM DIRECTOR WHERE director_ID = ?");
            pstmt.setInt(1, directorID);
            pstmt.executeUpdate();
            con.close();
        } catch (SQLException e) {
            System.out.println("Delete failed: " + e);
        }
    }
    /*************************************************************************************************/

    public static List<DirectorInfo> readAllDirectors() {
        List<DirectorInfo> directors = new ArrayList<>();
        Connection con = getCon();
        try {
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM DIRECTOR");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                directors.add(new DirectorInfo(
                        rs.getInt("director_ID"),
                        rs.getString("first_name"),
                        rs.getString("last_name")
                ));
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Query for getting all directors failed: " + e);
        }
        return directors;
    }
    
    public static int getDirectorID(String firstName, String lastName) {
        Connection con = getCon();
        try {
            PreparedStatement pstmt = con.prepareStatement("SELECT director_ID FROM DIRECTOR WHERE first_name = ? AND last_name = ?");
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("director_ID");
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Query failed: " + e);
        }
        return -1; // Return -1 if no director is found or if there's an error
    }

    // Method to get the ID of the last inserted director
    public static int getLastInsertedDirectorID() {
        Connection con = getCon();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT LAST_INSERT_ID() AS last_id");
            if (rs.next()) {
                return rs.getInt("last_id");
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Query failed: " + e);
        }
        return -1; // Return -1 if no ID is found or if there's an error
    }
    
    public static void main(String[] args) {
        Connection con = getCon();
        if (con != null) {
            try {                
                //System.out.println(readMember("s2hossain@torontomu.ca").toString());
                List<DirectorInfo> members = readAllDirectors();
                for (DirectorInfo element : members) {
                    System.out.println(element);
                }                
                con.close();
            } catch (SQLException e) {
                System.out.println("SQL Exception: " + e);
            }
        }
    }
}

