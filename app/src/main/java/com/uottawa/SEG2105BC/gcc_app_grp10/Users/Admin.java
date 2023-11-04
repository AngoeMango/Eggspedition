package com.uottawa.SEG2105BC.gcc_app_grp10.Users;

import com.uottawa.SEG2105BC.gcc_app_grp10.Database.DatabaseHandler;

public class Admin extends User{

    DatabaseHandler databaseHandler;

    public Admin(String username, String password, String email, String firstName){
        super(Role.admin, username, password, email, "Admin account", firstName);
        databaseHandler=new DatabaseHandler();
    }

    private void deleteAccount(String userId, String role){
        if(role.equals("admin")){
            return;
        }
        databaseHandler.deleteUserData(this, userId, role);
    }

    private void createEventType(){

    }

    private void deleteEventType(){

    }

    /**
     * Admins can modify events made by clubs for the purposes of moderation
     */
    private void editEvent(){

    }

    /**
     * Admins can delete events made by clubs for the purposes of moderation
     */
    private void deleteEvent(){

    }
}
