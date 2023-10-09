package com.uottawa.SEG2105BC.gcc_app_grp10.Users;

import android.graphics.drawable.Drawable;
import android.provider.ContactsContract;

public class Admin extends User{

    static final String ROLE="Admin";

    public Admin(String username, String password, String email){
        super(ROLE, username, password, email, null);
    }

 }
