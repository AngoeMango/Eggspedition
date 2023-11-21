package com.uottawa.SEG2105BC.gcc_app_grp10.Events;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.Objects;

public class Event extends EventType {
    private String name;
    private ArrayList<SpecifiedProperty> properties;

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
        properties = dataSnapshot.child("properties").getValue(ArrayList.class);
    }


    /**
     * The idea here is the event is valid if it has a value for every property type
     * it can have other propertyTypes and values if it wants to though
     * @return whether or not the event is valide
     */
    public boolean validate(){
        for (PropertyType requiredType:super.getRequiredPropertyTypes()) {
            if(!properties.contains(requiredType))return false;
        }
        return true;
    }

    public void addProperty(String name, SpecifiedProperty property){
        properties.add(property);
    }

    public void removeProperty(String propertyName){
        for (SpecifiedProperty property:properties) {
            if(property.getName().equals(propertyName))properties.remove(property);
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }


}
