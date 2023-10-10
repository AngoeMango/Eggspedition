package com.uottawa.SEG2105BC.gcc_app_grp10.Users;

import android.graphics.drawable.Drawable;

public class Participant extends User{

    public Participant(String username, String password, String email, String bio){
        super(Role.PARTICIPANT, username, password, email, bio);
    }


}
