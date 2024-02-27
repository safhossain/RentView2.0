package com.ryerson.rentview.Persistence;

import com.ryerson.rentview.Helper.RentalInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

public class Rental_CRUD extends Base_CRUD {

        public static int createRental(int memberId, int movieId, Date rentalDate, Date returnDate) {
        Connection con = Base_CRUD.getCon();
        int rentalId = -1;
        try {
            PreparedStatement pstmt = con.prepareStatement(
                "INSERT INTO RENTAL (member_ID, movie_ID, rental_date, return_date) VALUES (?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
            );
            pstmt.setInt(1, memberId);
            pstmt.setInt(2, movieId);
            pstmt.setDate(3, new java.sql.Date(rentalDate.getTime()));
            pstmt.setDate(4, returnDate != null ? new java.sql.Date(returnDate.getTime()) : null);
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                rentalId = rs.getInt(1);
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Insert failed: " + e);
        }
        return rentalId;
    }

    public static RentalInfo readRental(int rentalId) {
        Connection con = Base_CRUD.getCon();
        try {
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM RENTAL WHERE rental_ID = ?");
            pstmt.setInt(1, rentalId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new RentalInfo(
                    rs.getInt("rental_ID"),
                    rs.getInt("member_ID"),
                    rs.getInt("movie_ID"),
                    rs.getDate("rental_date"),
                    rs.getDate("return_date")
                );
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Query failed: " + e);
        }
        return null;
    }

    public static boolean updateRental(RentalInfo rental) {
        Connection con = Base_CRUD.getCon();
        try {
            PreparedStatement pstmt = con.prepareStatement(
                "UPDATE RENTAL SET rental_date = ?, return_date = ?, member_ID = ?, movie_ID = ? WHERE rental_ID = ?"
            );
            pstmt.setDate(1, new java.sql.Date(rental.getRentalDate().getTime()));
            pstmt.setDate(2, rental.getReturnDate() != null ? new java.sql.Date(rental.getReturnDate().getTime()) : null);
            pstmt.setInt(3, rental.getMemberId());
            pstmt.setInt(4, rental.getMovieId());
            pstmt.setInt(5, rental.getRentalId());
            int rowsAffected = pstmt.executeUpdate();
            con.close();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Update failed: " + e);
            return false;
        }
    }

    public static boolean deleteRental(int rentalId) {
        Connection con = Base_CRUD.getCon();
        try {
            PreparedStatement pstmt = con.prepareStatement("DELETE FROM RENTAL WHERE rental_ID = ?");
            pstmt.setInt(1, rentalId);
            int rowsAffected = pstmt.executeUpdate();
            con.close();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Delete failed: " + e);
            return false;
        }
    }

    public static List<RentalInfo> getAllRentals() {
        Connection con = Base_CRUD.getCon();
        List<RentalInfo> rentals = new ArrayList<>();
        try {
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM RENTAL");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                rentals.add(new RentalInfo(
                    rs.getInt("rental_ID"),
                    rs.getInt("member_ID"),
                    rs.getInt("movie_ID"),
                    rs.getDate("rental_date"),
                    rs.getDate("return_date")
                ));
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Query failed: " + e);
        }
        return rentals;
    }
    
    public static boolean authenticateRentalByMemberID(int memberID, int movieID) {
        Connection con = Base_CRUD.getCon();
        try {
            PreparedStatement pstmt = con.prepareStatement(
                "SELECT * FROM RENTAL WHERE member_ID = ? AND movie_ID = ?"
            );
            pstmt.setInt(1, memberID);
            pstmt.setInt(2, movieID);
            ResultSet rs = pstmt.executeQuery();
            
            boolean hasRented = rs.next();
            con.close();
            return hasRented;
        } catch (SQLException e) {
            System.out.println("Query failed: " + e);
            return false;
        }
    }

    
    public static void main(String[] args) {
        Connection con = getCon();
        if (con != null) {
            try {
//                List<RentalInfo> rentals = getAllRentals();
//                System.out.println("All Rentals:");
//                for (RentalInfo rental : rentals) {
//                    System.out.println("Rental ID: " + rental.getRentalId() +
//                                       ", Member ID: " + rental.getMemberId() +
//                                       ", Movie ID: " + rental.getMovieId() +
//                                       ", Rental Date: " + rental.getRentalDate() +
//                                       ", Return Date: " + rental.getReturnDate());
//                }
                
//                int memberID = 3;
//                int movieID = 2;
//                Date rentalDate = Date.valueOf("2024-02-27");
//                Date returnDate = Date.valueOf("2024-03-11");
//
//                int rentalID = Rental_CRUD.createRental(memberID, movieID, rentalDate, returnDate);
//                System.out.println("Rental created with ID: " + rentalID);

                System.out.println("member 3 with movie 1: " + authenticateRentalByMemberID(3,1));
                System.out.println("member 4 with movie 5: " + authenticateRentalByMemberID(4,5));
                
                con.close();
            } catch (SQLException e) {
                System.out.println("SQL Exception: " + e);
            }
        }
    }
}
