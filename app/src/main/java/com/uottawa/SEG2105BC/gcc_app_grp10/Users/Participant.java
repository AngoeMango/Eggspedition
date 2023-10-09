package com.uottawa.SEG2105BC.gcc_app_grp10.Users;

import android.graphics.drawable.Drawable;

public class Participant extends User{

    static final String ROLE="Participant";

    public Participant(String username, String password, String email){
        super(ROLE, username, password, email);
    }


}
