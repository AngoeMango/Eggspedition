package com.uottawa.SEG2105BC.gcc_app_grp10.Users;

import java.util.ArrayList;



public class Club extends User{
    /**
     * hashmap mutable so we can add more as needed, and easy to search for a particular one,
     * we can potentially use it for future deliverables
     */
    private ArrayList<Participant> members;

    public Club(String username, String password, String email, String bio, String firstName){
        super(Role.club, username, password, email, bio, firstName);
        members=new ArrayList<>();
    }

    /**
     * methods for later
     */
    public void addMember(Participant member){
        members.add(member);
    }

    public void removeMember(Participant member){
       members.remove(member);
    }

    public void createEvent(){

    }

    public void deleteEvent(){

    }

    public void editEvent(){

    }


}
