package com.ryerson.rentview.Helper;

public class GenreInfo {
    private int genreID;
    private String genreType;

    public GenreInfo(int genreID, String genreName) {
        this.genreID = genreID;
        this.genreType = genreName;
    }

    // Getters and setters
    public int getGenreID() {
        return genreID;
    }

    public void setGenreID(int genreID) {
        this.genreID = genreID;
    }

    public String getGenreType() {
        return genreType;
    }

    public void setGenreType(String genreType) {
        this.genreType = genreType;
    }

    @Override
    public String toString() {
        return "GenreInfo{" +
                "genreID=" + genreID +
                ", genreType='" + genreType + '\'' +
                '}';
    }
}