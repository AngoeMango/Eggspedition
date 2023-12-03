package com.uottawa.SEG2105BC.gcc_app_grp10.Users;

import android.provider.ContactsContract;

import com.google.firebase.database.DataSnapshot;
import com.uottawa.SEG2105BC.gcc_app_grp10.Database.DatabaseHandler;
import com.uottawa.SEG2105BC.gcc_app_grp10.Database.Interfaces.CanReceiveAnEvent;
import com.uottawa.SEG2105BC.gcc_app_grp10.Database.Interfaces.CanReceiveEvents;
import com.uottawa.SEG2105BC.gcc_app_grp10.Events.Event;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;

public class Participant extends User implements CanReceiveEvents {
    private String firstName;
    private ArrayList<Event> eventsJoined;

    //This is what will be stored in the database and used to load the events into the hashmap
    private ArrayList<String> eventNames;

    public Participant(String username, String password, String email, String bio){
        super(username, password, email, bio);
        eventsJoined=new ArrayList<>();
    }

    @Override
    public String getRole(){
        return "participant";
    }

    //To be implemented
    public void joinEvent(Event event) {
        eventsJoined.add(event);
        eventNames.add(event.getName());
    }

    public void leaveEvent(Event event) {
        eventsJoined.remove(event);
        eventNames.remove(event.getName());
    }

    /**
     * Events have to be loaded from their folder, so we load the names then use the databaseHandler to find the events
     * @param dataSnapshot
     */
    public Participant(DataSnapshot dataSnapshot) {
        super(dataSnapshot);
        firstName=dataSnapshot.child("firstname").getValue(String.class);
        eventsJoined=dataSnapshot.child("eventsJoined").getValue(ArrayList.class);
        if(eventNames!=null){
            DatabaseHandler handler=new DatabaseHandler();
            handler.loadMultipleEvents(this, eventNames);
        }
    }

    @Override
    public void onEventsRetrieved(ArrayList<Event> events) {
        this.eventsJoined.addAll(events);
    }

    @Override
    public void onEventsDatabaseFailure() {
        System.out.println("uh oh, participant couldn't load cause events it was part of don't exist");
    }



    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

}
