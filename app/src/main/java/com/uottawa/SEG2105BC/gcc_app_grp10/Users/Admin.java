package com.uottawa.SEG2105BC.gcc_app_grp10.Users;

import com.uottawa.SEG2105BC.gcc_app_grp10.Database.DatabaseHandler;
import com.uottawa.SEG2105BC.gcc_app_grp10.Events.EventType;

import java.util.ArrayList;
import java.util.HashMap;

public class Admin extends User{

    DatabaseHandler databaseHandler;

    public Admin(String username, String password, String email, String firstName){
        super(Role.admin, username, password, email, "Admin account", firstName);
        databaseHandler=new DatabaseHandler();
    }

    public void deleteAccount(String userId, String role){
        if(role.equals("admin")){
            return;
        }
        databaseHandler.deleteUserData(this, userId, role);
    }

    public EventType createEventType(String name, ArrayList<String> properties){
        EventType eventType=new EventType(name, properties.toArray(new String[0]));
        databaseHandler.addEventType(eventType);
        return eventType;
    }

    public void deleteEventType(String name){
        databaseHandler.deleteEventType(name);
    }

    public void deleteEvent(String name){
        databaseHandler.deleteEvent(name);
    }

    /**
     * Admins can modify events made by clubs for the purposes of moderation
     */
    public void editEvent(){

    }

    /**
     * Admins can delete events made by clubs for the purposes of moderation
     */
    public void deleteEvent(){

    }
}
