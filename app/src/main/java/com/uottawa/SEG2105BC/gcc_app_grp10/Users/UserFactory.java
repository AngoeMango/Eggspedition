package com.uottawa.SEG2105BC.gcc_app_grp10.Users;

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
     * @return
     */
    public static User makeUser(String role, String username, String password, String email){
        switch (role){
            case "club":
                return new Club(username, password, email);
            case "participant":
                return new Participant(username, password, email);
            default:
                throw new InvalidParameterException();
        }
    }
}
