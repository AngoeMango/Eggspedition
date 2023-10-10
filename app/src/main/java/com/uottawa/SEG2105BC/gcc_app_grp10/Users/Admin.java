package com.uottawa.SEG2105BC.gcc_app_grp10.Users;

import android.graphics.drawable.Drawable;
import android.provider.ContactsContract;

public class Admin extends User{

    public Admin(String username, String password, String email, String firstName){
        super(Role.ADMIN, username, password, email, null, firstName);
    }

 }
