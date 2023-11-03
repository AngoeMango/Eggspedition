package com.uottawa.SEG2105BC.gcc_app_grp10.Users;

public class Participant extends User{

    public Participant(String username, String password, String email, String bio, String firstName){
        super(Role.participant, username, password, email, bio, firstName);
    }


}
