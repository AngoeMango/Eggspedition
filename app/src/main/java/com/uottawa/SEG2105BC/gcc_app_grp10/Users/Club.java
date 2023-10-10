package com.uottawa.SEG2105BC.gcc_app_grp10.Users;

import java.util.ArrayList;



public class Club extends User{
    /**
     * hashmap mutable so we can add more as needed, and easy to search for a particular one
     */
    private ArrayList<Participant> members;

    public Club(String username, String password, String email, String bio, String firstName){
        super(Role.CLUB, username, password, email, bio, firstName);
    }


}
