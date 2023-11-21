package com.uottawa.SEG2105BC.gcc_app_grp10.Users;

import com.uottawa.SEG2105BC.gcc_app_grp10.Database.DatabaseHandler;
import com.uottawa.SEG2105BC.gcc_app_grp10.Events.Event;

import java.util.ArrayList;
import java.util.HashMap;


public class Club extends User{
    private HashMap<String ,Event> events;

    public Club(String username, String password, String email, String bio, String socialMediaLink, String mainContactName, String phoneNumber){
        super(Role.club, username, password, email, bio, mainContactName);
        events=new HashMap<>();
    }

    public void createEvent(){

    }

    public void deleteEvent(String name){
        DatabaseHandler databaseHandler=new DatabaseHandler();
        databaseHandler.deleteEvent(name);
    }

    public Event getEvent(String name){
        return events.get(name);
    }

    public void addEvent(Event event){
        events.put(event.getName(), event);
    }

    public HashMap<String, Event> getEvents(){
        return events;
    }



}
