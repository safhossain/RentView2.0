package com.ryerson.rentview.Persistence;

import java.util.List;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ryerson.rentview.Helper.MemberInfo;

public class Member_CRUD extends Base_CRUD {    
    //sql connection stuff inherited from Base_CRUD
    
    /************************************* CRUD OPERATIONS ********************************************/ 
    //overloading create methods
    public static void createMember(String email, String password, String firstName, String lastName, String dob, String memberType) {
        createMember(email, password, firstName, lastName, dob, memberType, null, null, null);
    }    
    public static void createMember(String email, String password, String firstName, String lastName, String dob, String memberType, String lastFourDigits, String cardType, String expirationDate) {
        Connection con = getCon();
        try {
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO MEMBER (email_address, hashed_password, first_name, last_name, date_of_birth, member_type, last_four_digits, card_type, expiration_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            pstmt.setString(3, firstName);
            pstmt.setString(4, lastName);
            pstmt.setString(5, dob);
            pstmt.setString(6, memberType);
            pstmt.setString(7, lastFourDigits);
            pstmt.setString(8, cardType);
            pstmt.setString(9, expirationDate);            
            pstmt.executeUpdate();
            con.close();
        } catch (SQLException e) {
            System.out.println("Insert failed: " + e);
        }
    }
    
    public static MemberInfo readMember(String email) {
        Connection con = getCon();
        try {
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM MEMBER WHERE email_address = ?");
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                // Create and return a MemberInfo object
                return new MemberInfo(
                    rs.getInt("member_ID"),
                    rs.getString("email_address"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("date_of_birth"),
                    rs.getString("member_type")
                );
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Query failed: " + e);
        }
        return null;
    }
    public static String getHashedPassword(String email) { //specifically for reading only the hashedPassword
        Connection con = getCon();
        try {
            PreparedStatement pstmt = con.prepareStatement("SELECT hashed_password FROM MEMBER WHERE email_address = ?");
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("hashed_password");
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Query failed: " + e);
        }
        return null; // Return null if no member is found or if there's an error
    }
    
    public static void updateMember(String emailAddress, String attributeName, String newValue) {
        Connection con = getCon();
        try {
            PreparedStatement pstmt = con.prepareStatement("UPDATE MEMBER SET " + attributeName + " = ? WHERE email_address = ?");
            pstmt.setString(1, newValue);
            pstmt.setString(2, emailAddress);
            pstmt.executeUpdate();
            con.close();
        } catch (SQLException e) {
            System.out.println("Update failed: " + e);
        }
    }
    
    public static void deleteMember(int memberId) {
        Connection con = getCon();
        try {
            PreparedStatement pstmt = con.prepareStatement("DELETE FROM MEMBER WHERE member_ID = ?");
            pstmt.setInt(1, memberId);
            pstmt.executeUpdate();
            con.close();
        } catch (SQLException e) {
            System.out.println("Delete failed: " + e);
        }
    }    
    public static void deleteMember(String emailAddress) {
        Connection con = getCon();
        try {
            PreparedStatement pstmt = con.prepareStatement("DELETE FROM MEMBER WHERE email_address = ?");
            pstmt.setString(1, emailAddress);
            pstmt.executeUpdate();
            con.close();
        } catch (SQLException e) {
            System.out.println("Delete failed: " + e);
        }
    }
    /*************************************************************************************************************/
    public static List<MemberInfo> readAllMembers() {
        List<MemberInfo> members = new ArrayList<>();
        Connection con = getCon();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM MEMBER");
            while (rs.next()) {
                members.add(new MemberInfo(
                    rs.getInt("member_ID"),
                    rs.getString("email_address"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("date_of_birth"),
                    rs.getString("member_type"),
                    rs.getString("last_four_digits"),
                    rs.getString("card_type"),
                    rs.getString("expiration_date")
                ));
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Query for getting all Members failed: " + e);
        }
        return members;
    }
    
    public static void main(String[] args) {
        Connection con = getCon();
        if (con != null) {
            try {                
                //System.out.println(readMember("s2hossain@torontomu.ca").toString());
                List<MemberInfo> members = readAllMembers();
                for (MemberInfo element : members) {
                    System.out.println(element);
                }
                
                con.close();
            } catch (SQLException e) {
                System.out.println("SQL Exception: " + e);
            }
        }
    }
}