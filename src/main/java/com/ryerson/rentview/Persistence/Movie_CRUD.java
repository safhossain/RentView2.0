package com.ryerson.rentview.Persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.IOException;

import com.ryerson.rentview.Helper.MovieInfo;
import com.ryerson.rentview.Helper.DirectorInfo;
import com.ryerson.rentview.Helper.GenreInfo;

import java.util.List;
import java.util.ArrayList;

public class Movie_CRUD extends Base_CRUD{    
    
    /************************************* CRUD OPERATIONS ********************************************/ 
    public static int createMovie(String movieName, int releaseYear, double rentalCost, String movieImagePath, boolean isMovieFeatured) {
        Connection con = getCon();
        int movieID = -1;
        try {
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO MOVIE (movie_name, release_year, rental_cost, movie_image_path, is_movie_featured) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, movieName);
            pstmt.setInt(2, releaseYear);
            pstmt.setDouble(3, rentalCost);
            pstmt.setString(4, movieImagePath);
            pstmt.setBoolean(5, isMovieFeatured);
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                movieID = rs.getInt(1);
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Insert failed: " + e);
        }
        return movieID;
    }
    
    public static MovieInfo readMovie(int movieId) {
        Connection con = getCon();
        try {
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM MOVIE WHERE movie_ID = ?");
            pstmt.setInt(1, movieId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                // Create and return a MovieInfo object
                return new MovieInfo(
                    rs.getInt("movie_ID"),
                    rs.getString("movie_name"),
                    rs.getInt("release_year"),
                    rs.getDouble("rental_cost"),
                    rs.getString("movie_image_path"),
                    rs.getBoolean("is_movie_featured")
                );
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Query failed: " + e);
        }
        return null;
    }
    
    public static void updateMovie(int movieId, String attributeName, Object newValue) {
        Connection con = getCon();
        try {
            PreparedStatement pstmt = con.prepareStatement("UPDATE MOVIE SET " + attributeName + " = ? WHERE movie_ID = ?");
            if (newValue instanceof String) {
                pstmt.setString(1, (String) newValue);
            } else if (newValue instanceof Integer) {
                pstmt.setInt(1, (Integer) newValue);
            } else if (newValue instanceof Double) {
                pstmt.setDouble(1, (Double) newValue);
            } else if (newValue instanceof Boolean) {
                pstmt.setBoolean(1, (Boolean) newValue);
            }
            pstmt.setInt(2, movieId);
            pstmt.executeUpdate();
            con.close();
        } catch (SQLException e) {
            System.out.println("Update failed: " + e);
        }
    }
    
    public static void deleteMovie(int movieId) {
        Connection con = getCon();
        try {
            PreparedStatement pstmt = con.prepareStatement("DELETE FROM MOVIE WHERE movie_ID = ?");
            pstmt.setInt(1, movieId);
            pstmt.executeUpdate();
            con.close();
        } catch (SQLException e) {
            System.out.println("Delete failed: " + e);
        }
    }
    /*************************************************************************************************/
    public static int getLastInsertedMovieID() {
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
    
    public static List<MovieInfo> getMoviesByDirector(int directorID) {
        List<MovieInfo> movies = new ArrayList<>();
        Connection con = getCon();
        try {
            PreparedStatement pstmt = con.prepareStatement("SELECT M.* FROM MOVIE M JOIN MOVIE_DIRECTOR MD ON M.movie_ID = MD.movie_ID WHERE MD.director_ID = ?");
            pstmt.setInt(1, directorID);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                movies.add(new MovieInfo(
                    rs.getInt("movie_ID"),
                    rs.getString("movie_name"),
                    rs.getInt("release_year"),
                    rs.getDouble("rental_cost"),
                    rs.getString("movie_image_path"),
                    rs.getBoolean("is_movie_featured")
                ));
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Query failed: " + e);
        }
        return movies;
    }

    public static List<MovieInfo> getMoviesByGenre(String genreType) {
        List<MovieInfo> movies = new ArrayList<>();
        Connection con = getCon();
        try {
            PreparedStatement pstmt = con.prepareStatement("SELECT M.* FROM MOVIE M JOIN MOVIE_GENRE MG ON M.movie_ID = MG.movie_ID JOIN GENRE G ON MG.genre_ID = G.genre_ID WHERE G.genre_type = ?");
            pstmt.setString(1, genreType);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                movies.add(new MovieInfo(
                    rs.getInt("movie_ID"),
                    rs.getString("movie_name"),
                    rs.getInt("release_year"),
                    rs.getDouble("rental_cost"),
                    rs.getString("movie_image_path"),
                    rs.getBoolean("is_movie_featured")
                ));
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Query failed: " + e);
        }
        return movies;
    }

    public static List<DirectorInfo> getDirectorsFromMovieID(int movieID) {
        List<DirectorInfo> directors = new ArrayList<>();
        Connection con = getCon();
        try {
            PreparedStatement pstmt = con.prepareStatement("SELECT D.* FROM DIRECTOR D JOIN MOVIE_DIRECTOR MD ON D.director_ID = MD.director_ID WHERE MD.movie_ID = ?");
            pstmt.setInt(1, movieID);
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
            System.out.println("Query failed: " + e);
        }
        return directors;
    }

    public static List<GenreInfo> getGenresFromMovieID(int movieID) {
        List<GenreInfo> genres = new ArrayList<>();
        Connection con = getCon();
        try {
            PreparedStatement pstmt = con.prepareStatement("SELECT G.* FROM GENRE G JOIN MOVIE_GENRE MG ON G.genre_ID = MG.genre_ID WHERE MG.movie_ID = ?");
            pstmt.setInt(1, movieID);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                genres.add(new GenreInfo(
                    rs.getInt("genre_ID"),
                    rs.getString("genre_type")
                ));
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Query failed: " + e);
        }
        return genres;
    }
    
    public static List<MovieInfo> getAllMovies() {
        List<MovieInfo> movies = new ArrayList<>();
        Connection con = getCon();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM MOVIE");
            while (rs.next()) {
                movies.add(new MovieInfo(
                    rs.getInt("movie_ID"),
                    rs.getString("movie_name"),
                    rs.getInt("release_year"),
                    rs.getDouble("rental_cost"),
                    rs.getString("movie_image_path"),
                    rs.getBoolean("is_movie_featured")
                ));
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Query failed: " + e);
        }
        return movies;
    }
    
    public static void main(String[] args) {
        // Example usage of getMoviesByDirector
        int directorID = 1; // Assuming a director with ID 1 exists
        List<MovieInfo> moviesByDirector = getMoviesByDirector(directorID);
        System.out.println("Movies by Director (ID: " + directorID + "):");
        for (MovieInfo movie : moviesByDirector) {
            System.out.println(movie);
        }

        // Example usage of getMoviesByGenre
        String genreType = "Fantasy";
        List<MovieInfo> moviesByGenre = getMoviesByGenre(genreType);
        System.out.println("Movies by Genre (" + genreType + "):");
        for (MovieInfo movie : moviesByGenre) {
            System.out.println(movie);
        }

        // Example usage of getDirectorsFromMovieID
        int movieID = 1; // Assuming a movie with ID 1 exists
        List<DirectorInfo> directorsFromMovie = getDirectorsFromMovieID(movieID);
        System.out.println("Directors for Movie (ID: " + movieID + "):");
        for (DirectorInfo director : directorsFromMovie) {
            System.out.println(director);
        }

        // Example usage of getGenresFromMovieID
        List<GenreInfo> genresFromMovie = getGenresFromMovieID(movieID);
        System.out.println("Genres for Movie (ID: " + movieID + "):");
        for (GenreInfo genre : genresFromMovie) {
            System.out.println(genre);
        }
    }
}
