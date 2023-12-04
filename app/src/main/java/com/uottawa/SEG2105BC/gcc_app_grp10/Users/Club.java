package com.uottawa.SEG2105BC.gcc_app_grp10.Users;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.GenericTypeIndicator;
import com.uottawa.SEG2105BC.gcc_app_grp10.Database.DatabaseHandler;
import com.uottawa.SEG2105BC.gcc_app_grp10.Database.Interfaces.CanReceiveAnEvent;
import com.uottawa.SEG2105BC.gcc_app_grp10.Events.Event;

import java.util.ArrayList;
import java.util.HashMap;


public class Club extends User implements CanReceiveAnEvent {
    private HashMap<String ,Event> events;

    //This is what will be stored in the database and used to load the events into the hashmap
    private ArrayList<String> eventNames;
    private String socialMediaLink;
    private String phoneNumber;
    private String mainContactName;

    private ArrayList<Rating> ratings;


    public Club(String username, String password, String email, String bio, String socialMediaLink, String mainContactName, String phoneNumber){
        super(username, password, email, bio);
        events=new HashMap<>();
        eventNames=new ArrayList<>();
        this.mainContactName=mainContactName;
        this.phoneNumber=phoneNumber;
        this.socialMediaLink=socialMediaLink;
        ratings=new ArrayList<>();
    }

    /**
     * Events have to be loaded from their folder, so we load the names then use the databaseHandler to find the events
     * @param dataSnapshot
     */
    public Club(DataSnapshot dataSnapshot){
        super(dataSnapshot);
        mainContactName=dataSnapshot.child("mainContactName").getValue(String.class);
        socialMediaLink=dataSnapshot.child("socialMediaLink").getValue(String.class);
        phoneNumber=dataSnapshot.child("phoneNumber").getValue(String.class);
        GenericTypeIndicator<ArrayList<Rating>> r = new GenericTypeIndicator<ArrayList<Rating>>() {};
        ratings= dataSnapshot.child("ratings").getValue(r);
        if(ratings==null)ratings=new ArrayList<>();
        events=new HashMap<>();
        DatabaseHandler handler=new DatabaseHandler();
        GenericTypeIndicator<ArrayList<String>> t = new GenericTypeIndicator<ArrayList<String>>() {};
        eventNames= dataSnapshot.child("eventNames").getValue(t);
        if(eventNames!=null) {
            for (String name : eventNames) {
                handler.loadEvent(this, name, "");
            }
        }


    }

    @Override
    public void onEventDatabaseFailure(String retrievingFunctionName) {

    }

    @Override
    public void onEventRetrieved(String retrievingFunctionName, Event event) {
        System.out.println("temp");
        events.put(event.getName(), event);
    }

    @Override
    public String getRole(){
        return "club";
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


    public String getSocialMediaLink() {
        return socialMediaLink;
    }

    public void setSocialMediaLink(String socialMediaLink) {
        this.socialMediaLink = socialMediaLink;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMainContactName() {
        return mainContactName;
    }

    public void setMainContactName(String mainContactName) {
        this.mainContactName = mainContactName;
    }

    public ArrayList<Rating> getRatings() {
        return ratings;
    }

    public void addRating(Rating rating){
        ratings.add(rating);
    }
}
