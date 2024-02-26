package com.ryerson.rentview.Helper;

public class EncryptionUtil {
    public static String hashPassword(String email, String password) {
        int hash = 7;
        String saltedPassword = password + email; // Use email as salt
        for (int i = 0; i < saltedPassword.length(); i++) {
            hash = hash * 31 + saltedPassword.charAt(i);
        }
        return Integer.toHexString(hash);
    }
    public static void main(String[] args) {
        System.out.println("('safhossain338@gmail.com', '" + hashPassword("safhossain338@gmail.com", "helloWorld!") + "', 'Safwan', 'Hossain', '2002-10-22', 'manager'),");
        System.out.println("('s2hossain@torontomu.ca', '" + hashPassword("s2hossain@torontomu.ca", "epicPassword123") + "', 'admin', 'admin', '2002-10-22', 'manager'),");
        System.out.println("('alice@example.com', '" + hashPassword("alice@example.com", "wonderland") + "', 'Alice', 'Wonder', '1990-05-15', 'user'),");
        System.out.println("('BobJohn@example.com', '" + hashPassword("BobJohn@example.com", "thebuilder85") + "', 'Bob', 'John', '1985-01-30', 'user'),");
        System.out.println("('CBrown@example.com', '" + hashPassword("CBrown@example.com", "rihanna") + "', 'Chris', 'Brown', '1989-05-05', 'user'),");
        System.out.println("('D1Wilson@example.com', '" + hashPassword("D1Wilson@example.com", "nfl69") + "', 'Derek', 'Wilson', '1992-08-24', 'user'),");
        System.out.println("('ethan69@example.com', '" + hashPassword("ethan69@example.com", "pen15") + "', 'Ethan', 'Smith', '2000-03-12', 'user'),");
    }
}
