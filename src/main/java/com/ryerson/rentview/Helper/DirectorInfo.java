package com.ryerson.rentview.Helper;

public class DirectorInfo {
    private int directorID;
    private String firstName;
    private String lastName;

    public DirectorInfo(int directorID, String firstName, String lastName) {
        this.directorID = directorID;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Getters and setters
    public int getDirectorID() {
        return directorID;
    }

    public void setDirectorID(int directorID) {
        this.directorID = directorID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "DirectorInfo{" +
                "directorID=" + directorID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}