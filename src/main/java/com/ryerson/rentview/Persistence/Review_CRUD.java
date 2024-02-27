package com.ryerson.rentview.Persistence;

import com.ryerson.rentview.Helper.ReviewInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Review_CRUD extends Base_CRUD{
    
    public static void createReview(String review, int rating, int memberID, int movieID) {
        Connection con = getCon();
        try {
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO REVIEW (review_description, rating, member_ID, movie_ID) VALUES (?, ?, ?, ?)");
            pstmt.setString(1, review);
            pstmt.setInt(2, rating);
            pstmt.setInt(3, memberID);
            pstmt.setInt(4, movieID);
            pstmt.executeUpdate();
            con.close();
        } catch (SQLException e) {
            System.out.println("createReview Insert failed: " + e);
        }
    }
    
    public static List<ReviewInfo> getAllReviews() {
        List<ReviewInfo> reviews = new ArrayList<>();
        Connection con = getCon();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM MOVIE");
            while (rs.next()) {
                reviews.add(new ReviewInfo(
                    rs.getInt("review_ID"),
                    rs.getInt("member_ID"),
                    rs.getInt("movie_ID"),                    
                    rs.getString("review_description"),
                    rs.getInt("rating")
                ));
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Query failed: " + e);
        }
        return reviews;
    }
    
    public static List<ReviewInfo> getReviewsByMovieID(int movieID) {
        List<ReviewInfo> reviews = new ArrayList<>();
        Connection con = getCon();
        try {
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM REVIEW WHERE movie_ID = ?");
            pstmt.setInt(1, movieID);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                reviews.add(new ReviewInfo(
                    rs.getInt("review_ID"),
                    rs.getInt("member_ID"),
                    rs.getInt("movie_ID"),
                    rs.getString("review_description"),
                    rs.getInt("rating")
                ));
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Query failed: " + e);
        }
        return reviews;
    }
    public static void main(String[] args) {
        int movieID = 1;
        List<ReviewInfo> reviews = getReviewsByMovieID(movieID);
        System.out.println("Reviews for movie ID " + movieID + ":");
        for (ReviewInfo review : reviews) {
            System.out.println(review);
        }
    }
}

