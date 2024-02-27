package com.ryerson.rentview.Helper;

import java.util.Date;

public class RentalInfo {
    private int rentalId;
    private int memberId;
    private int movieId;
    private Date rentalDate;
    private Date returnDate;

    public RentalInfo(int rentalId, int memberId, int movieId, Date rentalDate, Date returnDate) {
        this.rentalId = rentalId;
        this.memberId = memberId;
        this.movieId = movieId;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
    }
    
    public int getRentalId() {
        return rentalId;
    }

    public void setRentalId(int rentalId) {
        this.rentalId = rentalId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public Date getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(Date rentalDate) {
        this.rentalDate = rentalDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "RentalInfo{" +
                "rentalId=" + rentalId +
                ", memberId=" + memberId +
                ", movieId=" + movieId +
                ", rentalDate=" + rentalDate +
                ", returnDate=" + returnDate +
                '}';
    }    
}

