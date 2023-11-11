package com.uottawa.SEG2105BC.gcc_app_grp10.Events;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.HashMap;

public class Event {
    private EventType eventType;
    private String name;
    private ArrayList<Property> properties;


    /**
     * simplest implementation of an event
     * @param name
     * @param eventType
     */
    public Event(String name, EventType eventType){
        this.name=name;
        this.eventType=eventType;
        properties= (ArrayList<Property>) eventType.getProperties().clone();
    }

    /**
     * used when loading an event Type from the database
     * @param dataSnapshot this is the form data takes when loaded from the database
     */
    public Event(DataSnapshot dataSnapshot){
        name=dataSnapshot.child("name").getValue(String.class);
        eventType=dataSnapshot.child("eventType").getValue(EventType.class);
        properties=dataSnapshot.child("properties").getValue(ArrayList.class);
    }

    /*
    these should be accessible by admin or club
     */

    /**
     * this is for a club to setup an event
     * @param properties needs to be reconsidered
     * @return whether or not the setup was successful
     */
    public boolean setupEvent(ArrayList properties){
        //ArrayList<Property> propertiesList= (ArrayList<Property>) properties.clone();
        return true;
    }





    /*possible implementation 1
        private String eventType;
        private ArrayList<String> registrationRequirements;
        private String eventDetails;
        private String location;
        private String dateTime;
        //add whatever other things we might need to store about an abstract event
     */

    /*possible implementation 2
        private String eventType;
        private Hashmap<String, object> informationAboutEvent;
        //the hashmap would hold an abstract amount of information about the event
        //it could have registration details, dates, location, anything
        //we'd have to decide what kind of object though
     */

    /*possible implementation 3
        make this abstract and make a new class for the new type at runtime?
        no clue how to do that though
     */

}
