package com.uottawa.SEG2105BC.gcc_app_grp10.Users;

import android.graphics.drawable.Drawable;

public interface User {

    boolean isPasswordCorrect(String passwordAttempt);
    boolean changePassword(String passwordAttempt, String newPassword);

    String getUsername();
    void setUsername(String username);
    String getEmail();
    void setEmail(String email);
    String getBio();
    void setBio(String bio);
    String getRole();


    /**
     not completely sure what type this should return, using drawable as a placeholder
    */
    Drawable getProfilePic();
    void setProfilePic(Drawable profilePic);


}
