package com.ryerson.rentview.Persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Movie_Director_CRUD extends Base_CRUD {
   
    public static void createMovieDirector(int movieID, int directorID) {
        Connection con = getCon();
        try {
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO MOVIE_DIRECTOR (movie_ID, director_ID) VALUES (?, ?)");
            pstmt.setInt(1, movieID);
            pstmt.setInt(2, directorID);
            pstmt.executeUpdate();
            con.close();
        } catch (SQLException e) {
            System.out.println("Movie_Director_CRUD Insert failed: " + e);
        }
    }
   
    public static void deleteMovieDirector(int movieID, int directorID) {
        Connection con = getCon();
        try {
            PreparedStatement pstmt = con.prepareStatement("DELETE FROM MOVIE_DIRECTOR WHERE movie_ID = ? AND director_ID = ?");
            pstmt.setInt(1, movieID);
            pstmt.setInt(2, directorID);
            pstmt.executeUpdate();
            con.close();
        } catch (SQLException e) {
            System.out.println("Delete failed: " + e);
        }
    }
}

