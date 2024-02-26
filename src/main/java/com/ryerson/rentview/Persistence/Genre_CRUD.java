package com.ryerson.rentview.Persistence;

import com.ryerson.rentview.Helper.GenreInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Genre_CRUD extends Base_CRUD {

    /************************************* CRUD OPERATIONS ********************************************/ 
    public static void createGenre(String genreType) {
        Connection con = getCon();
        try {
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO GENRE (genre_type) VALUES (?)");
            pstmt.setString(1, genreType);
            pstmt.executeUpdate();
            con.close();
        } catch (SQLException e) {
            System.out.println("createGenre Insert failed: " + e);
        }
    }

    public static GenreInfo readGenre(int genreID) {
        Connection con = getCon();
        try {
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM GENRE WHERE genre_ID = ?");
            pstmt.setInt(1, genreID);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new GenreInfo(
                        rs.getInt("genre_ID"),
                        rs.getString("genre_type")
                );
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Query failed: " + e);
        }
        return null;
    }

    public static void updateGenre(int genreID, String genreType) {
        Connection con = getCon();
        try {
            PreparedStatement pstmt = con.prepareStatement("UPDATE GENRE SET genre_type = ? WHERE genre_ID = ?");
            pstmt.setString(1, genreType);
            pstmt.setInt(2, genreID);
            pstmt.executeUpdate();
            con.close();
        } catch (SQLException e) {
            System.out.println("Update failed: " + e);
        }
    }

    public static void deleteGenre(int genreID) {
        Connection con = getCon();
        try {
            PreparedStatement pstmt = con.prepareStatement("DELETE FROM GENRE WHERE genre_ID = ?");
            pstmt.setInt(1, genreID);
            pstmt.executeUpdate();
            con.close();
        } catch (SQLException e) {
            System.out.println("Delete failed: " + e);
        }
    }
    /*************************************************************************************************/
    public static List<GenreInfo> readAllGenres() {
        List<GenreInfo> genres = new ArrayList<>();
        Connection con = getCon();
        try {
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM GENRE");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                genres.add(new GenreInfo(
                        rs.getInt("genre_ID"),
                        rs.getString("genre_type")
                ));
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Query for getting all genres failed: " + e);
        }
        return genres;
    }
    
    public static int getGenreID(String genreType) {
        Connection con = getCon();
        try {
            PreparedStatement pstmt = con.prepareStatement("SELECT genre_ID FROM GENRE WHERE genre_type = ?");
            pstmt.setString(1, genreType);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("genre_ID");
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Query failed: " + e);
        }
        return -1; // Return -1 if no genre is found or if there's an error
    }

    // Method to get the ID of the last inserted genre
    public static int getLastInsertedGenreID() {
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
                List<GenreInfo> members = readAllGenres();
                for (GenreInfo element : members) {
                    System.out.println(element);
                }                
                con.close();
            } catch (SQLException e) {
                System.out.println("SQL Exception: " + e);
            }
        }
    }
}
