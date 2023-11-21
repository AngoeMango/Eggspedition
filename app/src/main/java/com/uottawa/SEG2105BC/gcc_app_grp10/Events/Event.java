package com.uottawa.SEG2105BC.gcc_app_grp10.Events;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Event extends EventType {
    private String name;
    private HashMap<PropertyType, SpecifiedProperty> properties;

    /**
     * simplest implementation of an event
     *
     * @param name
     * @param eventType
     */
    public Event(String name, EventType eventType) {
        super(eventType);
        this.name = name;
    }

    /**
     * used when loading an event Type from the database
     * @param dataSnapshot this is the form data takes when loaded from the database
     */
    public Event(DataSnapshot dataSnapshot) {
        super(Objects.requireNonNull(dataSnapshot.child("eventType").getValue(EventType.class)));
        name = dataSnapshot.child("name").getValue(String.class);
        setUpPropertyTypesWhenLoadingFromDatabase(dataSnapshot.child("properties").getValue(HashMap.class));
    }

    /**
     *
     * @param savedMap
     */
    private void setUpPropertyTypesWhenLoadingFromDatabase(HashMap<PropertyType, SpecifiedProperty> savedMap){
        if(savedMap==null){return;}
        for (PropertyType typeFromMap:savedMap.keySet()) {
            for (PropertyType propertyType:super.getRequiredPropertyTypes()) {
                if(propertyType.name.equals(typeFromMap.name)){properties.put(propertyType,savedMap.get(typeFromMap));}
                else{properties.put(typeFromMap,savedMap.get(typeFromMap));}
            }
        }
    }

    /*
    these should be accessible by admin or club
     */


    /**
     * The idea here is the event is valid if it has a value for every property type
     * it can have other propertyTypes and values if it wants to though
     * @return whether or not the event is valide
     */
    public boolean validate(){
        for (PropertyType propertyType:super.getRequiredPropertyTypes()) {

        }
        return true;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public void addProperty(String name,SpecifiedProperty property){

    }
}
