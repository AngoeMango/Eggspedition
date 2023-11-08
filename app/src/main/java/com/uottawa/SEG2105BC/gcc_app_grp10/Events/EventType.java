package com.uottawa.SEG2105BC.gcc_app_grp10.Events;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.HashMap;

public class EventType {
    private String name;
    private ArrayList<String> properties;


    public EventType(String name){
        this.name=name;
    }

    public EventType(String name, String[] propertyList){
        this.name=name;
        properties=new ArrayList<String>();
        for (String property:propertyList) {
            properties.add(property);
        }
    }

    public EventType(DataSnapshot dataSnapshot){
        name=dataSnapshot.child("name").getValue(String.class);
        properties =dataSnapshot.child("username").getValue(ArrayList.class);
    }

    /*
    these ones should only be accessible by admin
     */

    public void addPropertyToType(String newProperty){
        properties.add(newProperty);
    }

    public void removePropertyFromType(String property){
        properties.remove(property);
    }

    public void setProperties(ArrayList<String> properties) {
        this.properties = properties;
    }

    public ArrayList<String> getProperties() {
        return properties;
    }

    public String getName() {
        return name;
    }
}
