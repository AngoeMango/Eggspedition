package com.uottawa.SEG2105BC.gcc_app_grp10.Users;

import android.graphics.drawable.Drawable;
import android.icu.text.MessagePattern;

import java.util.HashMap;


public class Club extends User{
    static final String ROLE="Club";

    /**
     * hashmap mutable so we can add more as needed, and easy to search for a particular one
     */
    private HashMap<String,Participant> members;

    public Club(String username, String password, String email){
        super(ROLE, username, password, email);
    }
    public void addMember(Participant member){
        members.put(member.getUsername(),member);
    }

    /**
     * this method is going to need some protection
     * probably the addmember method above it too
     */
    public void removeMember(String username){
        members.remove(username);
    }


}
