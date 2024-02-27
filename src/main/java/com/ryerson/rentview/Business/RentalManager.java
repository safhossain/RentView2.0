package com.ryerson.rentview.Business;

import java.util.List;

import com.ryerson.rentview.Helper.RentalInfo;
import com.ryerson.rentview.Persistence.Rental_CRUD;
import java.sql.Date;

public class RentalManager {

    public static List<RentalInfo> getAllRentals() {
        return Rental_CRUD.getAllRentals();
    }
    //createRental(int memberId, int movieId, Date rentalDate, Date returnDate)
    public static void createRental(int memberID, int movieID, String rentalDate, String returnDate){
        Date rentalDateObj = Date.valueOf(rentalDate);
        Date returnDateObj = Date.valueOf(returnDate);
        Rental_CRUD.createRental(memberID, movieID, rentalDateObj, returnDateObj);
    }
    
    public static void deleteRental(int rentalID){
        Rental_CRUD.deleteRental(rentalID);
    }
    
    public static void main(String[] args) {
        List<RentalInfo> rentals = getAllRentals();
        for (RentalInfo rental : rentals) {
            System.out.println(rental);
        }
        createRental(4, 9, "2024-03-1", "2024-03-13");
    }
}
