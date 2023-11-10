package com.uottawa.SEG2105BC.gcc_app_grp10.Events;

import com.google.firebase.database.DataSnapshot;

import java.util.HashMap;

public class Event {
    private EventType eventType;
    private String name;
    private HashMap<String, Object> properties;


    public Event(String name, EventType eventType){
        this.name=name;
        properties=new HashMap<>();
        for (String property:eventType.getProperties().keySet()) {
            properties.put(property, null);
        }
    }

    public Event(DataSnapshot dataSnapshot){
        name=dataSnapshot.child("name").getValue(String.class);
        eventType=dataSnapshot.child("eventType").getValue(EventType.class);
        properties=dataSnapshot.child("properties").getValue(HashMap.class);
    }

    /*
    these should be accessible by admin or club
     */

    /**
     * this is for a club to setup an event
     * @param properties the values to be stored in the event
     * @return whether or not the setup was successful
     */
    public boolean setupEvent(HashMap<String, String> properties){
        HashMap<String, String> propertiesList= (HashMap<String, String>) properties.clone();
        for (String key: properties.keySet()) {
            if(propertiesList.containsKey(key)){
                propertiesList.put(key, "yes");
                this.properties.put(key, properties.get(key));
            }
            else{return false;}
        }
        for (String key:propertiesList.keySet()) {
            if(!propertiesList.get(key).equals("yes")){
                return false;
            }
        }
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
