package com.uottawa.SEG2105BC.gcc_app_grp10.Users;

import android.graphics.drawable.Drawable;

public abstract class User {

    private String username;
    private String password;
    private String bio;
    private String email;
    private final String ROLE;
    private Drawable profilePic;

    protected User(String role, String username, String password, String email){
        this.ROLE=role;
        this.username=username;
        this.password=password;
        this.email=email;
    }

    public boolean isPasswordCorrect(String passwordAttempt) {
        return password.equals(passwordAttempt);
    }

    public boolean changePassword(String passwordAttempt, String newPassword){
        if (isPasswordCorrect(passwordAttempt)){password=newPassword;return true;}
        else{return false;}
    }


    public String getUsername(){
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getEmail(){
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getBio(){
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }


    public String getRole(){
        return ROLE;
    }

    /**
     not completely sure what type this should return, using drawable as a placeholder
     */

    public Drawable getProfilePic(){
        return profilePic;
    }

    public void setProfilePic(Drawable profilePic) {
        this.profilePic = profilePic;
    }






}
