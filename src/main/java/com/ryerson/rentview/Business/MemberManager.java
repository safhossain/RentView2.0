package com.ryerson.rentview.Business;

import java.util.List;
import java.util.ArrayList;

import com.ryerson.rentview.Helper.EncryptionUtil;
import com.ryerson.rentview.Helper.MemberInfo;
import com.ryerson.rentview.Persistence.Member_CRUD;

public class MemberManager {
    public static MemberInfo authenticateMember(String email, String password) {
        String storedPassword = Member_CRUD.getHashedPassword(email);
        String receivedPasswordReHashed = EncryptionUtil.hashPassword(email, password);
        if (storedPassword != null && storedPassword.equals(receivedPasswordReHashed)) {
            return Member_CRUD.readMember(email);
        }
        else{
            System.out.println("storedPassword= " + storedPassword);
            System.out.println("receivedPasswordReHashed= " + receivedPasswordReHashed);
        }
        return null;
    }
    
    public static List<MemberInfo> getAllMembers() {
        return Member_CRUD.readAllMembers();
    }
    
    public static void createMember(String email, String password, String firstName, String lastName, String dob, String memberType){
        Member_CRUD.createMember(email, password, firstName, lastName, dob, memberType);
    }
    
    public static void deleteMember(String emailAddress){
        Member_CRUD.deleteMember(emailAddress);
    }
    
    public static void main(String[] args) {
        MemberInfo testEntity = authenticateMember("safhossain338@gmail.com", "helloWorld!");
        if (testEntity != null) {
            System.out.println(testEntity.toString());
        } else {
            System.out.println("Object is null");
        }
        MemberInfo testEntity2 = authenticateMember("BobJohn@example.com", "thebuilder85");
        if (testEntity2 != null) {
            System.out.println(testEntity2.toString());
        } else {
            System.out.println("Object is null");
        }
        MemberInfo testEntity3 = authenticateMember("safhossain338@gmail.com", "helloWorld!");
        if (testEntity3 != null) {
            System.out.println(testEntity3.toString());
        } else {
            System.out.println("Object is null");
        }
        
        List<MemberInfo> members = getAllMembers();
        for (MemberInfo element : members) {
            System.out.println(element);
        }
    }
}
