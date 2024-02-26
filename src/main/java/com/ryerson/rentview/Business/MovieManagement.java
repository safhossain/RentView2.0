package com.ryerson.rentview.Business;

import com.ryerson.rentview.Persistence.Movie_CRUD;
import com.ryerson.rentview.Persistence.Director_CRUD;
import com.ryerson.rentview.Persistence.Genre_CRUD;
import com.ryerson.rentview.Persistence.Movie_Director_CRUD;
import com.ryerson.rentview.Persistence.Movie_Genre_CRUD;
import com.ryerson.rentview.Helper.DirectorInfo;
import com.ryerson.rentview.Helper.GenreInfo;

import java.util.List;

public class MovieManagement {

    public static void createMovie(String movieName, int releaseYear, double rentalCost, String movieImagePath,
                                   boolean isMovieFeatured, String directorFirstName, String directorLastName,
                                   String genre1, String genre2)
    {
        // Create the movie
        int movieID = Movie_CRUD.createMovie(movieName, releaseYear, rentalCost, movieImagePath, isMovieFeatured);

        // Get the ID of the newly created movie
        System.out.println("movie id = " + movieID);

        // Check if the director exists, if not, create a new director
        int directorID = Director_CRUD.getDirectorID(directorFirstName, directorLastName);
        if (directorID == -1) {
            Director_CRUD.createDirector(directorFirstName, directorLastName);
            directorID = Director_CRUD.getDirectorID(directorFirstName, directorLastName);
        }
        System.out.println("directorID = " + directorID);
        

        // Create the movie-director association
        Movie_Director_CRUD.createMovieDirector(movieID, directorID);

        // Check if the genres exist, if not, create new genres
        int genreID1 = Genre_CRUD.getGenreID(genre1);
        if (genreID1 == -1) {
            Genre_CRUD.createGenre(genre1);
            genreID1 = Genre_CRUD.getGenreID(genre1);
        }
        System.out.println("genre 1 ID = " + genreID1);
        
        Movie_Genre_CRUD.createMovieGenre(movieID, genreID1);

        int genreID2 = Genre_CRUD.getGenreID(genre2);
        if (genreID2 == -1) {
            Genre_CRUD.createGenre(genre2);
            genreID2 = Genre_CRUD.getGenreID(genre2);
        }
        System.out.println("genre 2 ID = " + genreID2);
        
        Movie_Genre_CRUD.createMovieGenre(movieID, genreID2);
    }
    
    public static void main(String[] args) {
        String movieName = "Inception";
        int releaseYear = 2010;
        double rentalCost = 4.99;
        String movieImagePath = "/resources/movie_posters/inception.jpg";
        boolean isMovieFeatured = false;
        String directorFirstName = "Christopher";
        String directorLastName = "Nolan";
        String genre1 = "Sci-Fi";
        String genre2 = "Thriller";
        
        createMovie(movieName, releaseYear, rentalCost, movieImagePath, isMovieFeatured,
                    directorFirstName, directorLastName, genre1, genre2);
    }
}
