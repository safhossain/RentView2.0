package com.ryerson.rentview.Persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Movie_Genre_CRUD extends Base_CRUD {
    
    public static void createMovieGenre(int movieID, int genreID) {
        Connection con = getCon();
        try {
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO MOVIE_GENRE (movie_ID, genre_ID) VALUES (?, ?)");
            pstmt.setInt(1, movieID);
            pstmt.setInt(2, genreID);
            pstmt.executeUpdate();
            con.close();
        } catch (SQLException e) {
            System.out.println("Movie_Genre_CRUD Insert failed: " + e);
        }
    }
    
    public static void deleteMovieGenre(int movieID, int genreID) {
        Connection con = getCon();
        try {
            PreparedStatement pstmt = con.prepareStatement("DELETE FROM MOVIE_GENRE WHERE movie_ID = ? AND genre_ID = ?");
            pstmt.setInt(1, movieID);
            pstmt.setInt(2, genreID);
            pstmt.executeUpdate();
            con.close();
        } catch (SQLException e) {
            System.out.println("Delete failed: " + e);
        }
    }
}
