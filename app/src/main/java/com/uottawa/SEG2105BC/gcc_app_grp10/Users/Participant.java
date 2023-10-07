package com.uottawa.SEG2105BC.gcc_app_grp10.Users;

import android.graphics.drawable.Drawable;

public class Participant implements User{
    private String username;
    private String password;
    private String bio;
    private String email;
    static final String ROLE="Participant";
    private Drawable profilePic;

    public Participant(){

    }

    @Override
    public boolean isPasswordCorrect(String passwordAttempt) {
        return password.equals(passwordAttempt);
    }
    @Override
    public boolean changePassword(String passwordAttempt, String newPassword){
        if (isPasswordCorrect(passwordAttempt)){password=newPassword;return true;}
        else{return false;}
    }

    @Override
    public String getUsername(){
        return username;
    }
    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getEmail(){
        return email;
    }
    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getBio(){
        return bio;
    }
    @Override
    public void setBio(String bio) {
        this.bio = bio;
    }

    @Override
    public String getRole(){
        return ROLE;
    }

    @Override
    public Drawable getProfilePic(){
        return profilePic;
    }
    @Override
    public void setProfilePic(Drawable profilePic) {
        this.profilePic = profilePic;
    }
}
