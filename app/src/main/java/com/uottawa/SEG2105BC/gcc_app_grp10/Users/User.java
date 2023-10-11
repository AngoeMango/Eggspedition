package com.uottawa.SEG2105BC.gcc_app_grp10.Users;

public abstract class User {

    private String username;
    private String password;
    private String bio;
    private String email;
    private String firstName;
    private final Role ROLE;

    protected User(Role role, String username, String password, String email, String bio, String firstName){
        this.ROLE=role;
        this.username=username;
        this.password=password;
        this.email=email;
        this.bio=bio;
        this.firstName = firstName;
    }

    public boolean isPasswordCorrect(String passwordAttempt) {
        return password.equals(passwordAttempt);
    }

    public boolean setPassword(String passwordAttempt, String newPassword){
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

    public String getFirstName(){
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Role getRole(){
        return ROLE;
    }

}
