package com.ryerson.rentview.Business;

import java.util.List;
import com.ryerson.rentview.Helper.ReviewInfo;
import com.ryerson.rentview.Persistence.Review_CRUD;

public class ReviewManager {

    public static void createReview(String review, int rating, int memberID, int movieID) {
        Review_CRUD.createReview(review, rating, memberID, movieID);
    }

    public static List<ReviewInfo> getAllReviews() {
        return Review_CRUD.getAllReviews();
    }

    public static List<ReviewInfo> getReviewsByMovieID(int movieID) {
        return Review_CRUD.getReviewsByMovieID(movieID);
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
