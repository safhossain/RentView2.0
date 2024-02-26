package com.ryerson.rentview.Helper;

import com.ryerson.rentview.Helper.DirectorInfo;
import com.ryerson.rentview.Helper.GenreInfo;

import java.util.List;
import java.util.ArrayList;

public class MovieInfo {
    private int movieID;
    private String movieName;
    private int releaseYear;
    private double rentalCost;
    private String movieImagePath;
    private boolean isMovieFeatured;
    private List<DirectorInfo> associatedDirectors;
    private List<GenreInfo> associatedGenres;
    
    public MovieInfo(int movieID, String movieName, int releaseYear, double rentalCost, String movieImagePath, boolean isMovieFeatured) {
        this.movieID = movieID;
        this.movieName = movieName;
        this.releaseYear = releaseYear;
        this.rentalCost = rentalCost;
        this.movieImagePath = movieImagePath;
        this.isMovieFeatured = isMovieFeatured;
    }
    
    public MovieInfo(int movieID, String movieName, int releaseYear, double rentalCost, String movieImagePath, boolean isMovieFeatured, List<DirectorInfo> associatedDirectors, List<GenreInfo> associatedGenres) {
        this.movieID = movieID;
        this.movieName = movieName;
        this.releaseYear = releaseYear;
        this.rentalCost = rentalCost;
        this.movieImagePath = movieImagePath;
        this.isMovieFeatured = isMovieFeatured;
        this.associatedDirectors = associatedDirectors;
        this.associatedGenres = associatedGenres;
    }

    // Getters
    public int getMovieID() {
        return movieID;
    }

    public String getMovieName() {
        return movieName;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public double getRentalCost() {
        return rentalCost;
    }

    public String getMovieImagePath() {
        return movieImagePath;
    }

    public boolean isMovieFeatured() {
        return isMovieFeatured;
    }

    public List<DirectorInfo> getAssociatedDirectors() {
        return associatedDirectors;
    }

    public List<GenreInfo> getAssociatedGenres() {
        return associatedGenres;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setRentalCost(double rentalCost) {
        this.rentalCost = rentalCost;
    }

    public void setMovieImagePath(String movieImagePath) {
        this.movieImagePath = movieImagePath;
    }

    public void setMovieFeatured(boolean isMovieFeatured) {
        this.isMovieFeatured = isMovieFeatured;
    }
    
    public void setAssociatedDirectors(List<DirectorInfo> associatedDirectors) {
        this.associatedDirectors = associatedDirectors;
    }

    public void setAssociatedGenres(List<GenreInfo> associatedGenres) {
        this.associatedGenres = associatedGenres;
    }

    @Override
    public String toString() {
        return "MovieInfo{" +
                "movieID=" + movieID +
                ", movieName='" + movieName + '\'' +
                ", releaseYear=" + releaseYear +
                ", rentalCost=" + rentalCost +
                ", movieImagePath='" + movieImagePath + '\'' +
                ", isMovieFeatured=" + isMovieFeatured +
                '}';
    }
}

