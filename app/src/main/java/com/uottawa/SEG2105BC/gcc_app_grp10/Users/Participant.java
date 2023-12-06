package com.uottawa.SEG2105BC.gcc_app_grp10.Users;

import android.provider.ContactsContract;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.GenericTypeIndicator;
import com.uottawa.SEG2105BC.gcc_app_grp10.Database.DatabaseHandler;
import com.uottawa.SEG2105BC.gcc_app_grp10.Database.Interfaces.CanReceiveAnEvent;
import com.uottawa.SEG2105BC.gcc_app_grp10.Database.Interfaces.CanReceiveEvents;
import com.uottawa.SEG2105BC.gcc_app_grp10.Events.Event;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Participant extends User implements CanReceiveEvents {
    private String firstName;
    private ArrayList<Event> eventsJoined;


    //This is what will be stored in the database and used to load the events into the hashmap
    private ArrayList<String> eventNames;

    public Participant(String username, String password, String email, String bio){
        super(username, password, email, bio);
        eventsJoined=new ArrayList<>();
        eventNames=new ArrayList<>();
    }

    @Override
    public String getRole(){
        return "participant";
    }

    //To be implemented
    public void joinEvent(Event event) {
        eventsJoined.add(event);
        eventNames.add(event.getName());
        DatabaseHandler handler=new DatabaseHandler();
        handler.addEventToAssociatedUser(event.getEventTypeName(), getUsername(),getRole());
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

        eventsJoined=new ArrayList<>();
        GenericTypeIndicator<ArrayList<String>> t = new GenericTypeIndicator<ArrayList<String>>() {};
        eventNames=dataSnapshot.child("eventNames").getValue(t);
        if(eventNames!=null){
            DatabaseHandler handler=new DatabaseHandler();
            handler.loadMultipleEvents(this, eventNames);
        }


    }

    @Override
    public void onEventsRetrieved(ArrayList<Event> events) {
        eventsJoined.addAll(events);
        System.out.println("WEIRD" + getEvents().size());
    }

    @Override
    public void onEventsDatabaseFailure(String failureDescription) {
        System.out.println("uh oh, participant couldn't load cause events it was part of don't exist");
    }

    public ArrayList<Event> getEvents(){
        return eventsJoined;
    }



    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public ArrayList<String> getEventNames() {
        return eventNames;
    }


}
