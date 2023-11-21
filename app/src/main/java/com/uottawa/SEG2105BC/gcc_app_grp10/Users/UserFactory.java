package com.uottawa.SEG2105BC.gcc_app_grp10.Users;

import com.google.firebase.database.DataSnapshot;

import java.security.InvalidParameterException;

public class UserFactory {
    /**
     * we can overload this later if we want
     * for now use this method to create new user accounts
     * eventually we'll hook it up to the database
     * it can be made non static at any point if it's easier for the database
     * it doesn't make admins since we only have one of those, but we will need to initialise it somewhere
     * @param role
     * @param username
     * @param password
     * @param email
     * @param bio
     * @return
     */

    public User makeUser(String role, String username, String password, String email, String bio, String firstName) {
        switch (role){
            case "club":
                return new Club(username, password, email, bio, firstName, "", "");
            case "participant":
                return new Participant(username, password, email, bio, firstName);
            default:
                throw new InvalidParameterException();
        }
    }

    public User makeUser(String username, String password, String email, String bio, String socialMediaLink, String mainContactName, String phoneNumber) {
        return new Club(username, password, email, bio, socialMediaLink, mainContactName, phoneNumber);
    }

    public User makeUser(DataSnapshot dataSnapshot, String role) {
        String username=dataSnapshot.child("username").getValue(String.class);
        String password=dataSnapshot.child("password").getValue(String.class);
        String email=dataSnapshot.child("email").getValue(String.class);
        String bio=dataSnapshot.child("bio").getValue(String.class);
        String firstName=dataSnapshot.child("firstName").getValue(String.class);
        switch (role){
            case "club":
                return new Club(username, password, email, bio, firstName, "", "");
            case "participant":
                return new Participant(username, password, email, bio, firstName);
            case "admin":
                return new Admin(username, password, email, firstName);
            default:
                throw new InvalidParameterException();
        }
    }
}
