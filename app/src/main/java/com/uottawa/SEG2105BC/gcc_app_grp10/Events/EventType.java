package com.uottawa.SEG2105BC.gcc_app_grp10.Events;

import com.google.firebase.database.DataSnapshot;

import java.lang.reflect.Type;
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class EventType {
    private String name;

    private ArrayList<Property> properties;

    public EventType(String name){
        this.name=name;
        properties = new ArrayList<>();
    }

    public EventType(String name, ArrayList<Property> propertyList){
        this.name=name;
        properties= (ArrayList<Property>) propertyList.clone();
    }

    public EventType(DataSnapshot dataSnapshot){
        name=dataSnapshot.child("name").getValue(String.class);
        properties = new ArrayList<>();
        for (DataSnapshot propertySnapshot : dataSnapshot.child("properties").getChildren()) {
            properties.add(new Property(propertySnapshot.child("name").getValue(String.class), propertySnapshot.child("type").getValue(String.class)));
        }
    }

    /*
    these ones should only be accessible by admin
     */

    public void addPropertyToType(String newProperty,String type){
        properties.add(new Property(newProperty, type));
    }

    public void removePropertyFromType(String property){
        properties.remove(property);
    }

    public void setProperties(ArrayList<Property> properties) {
        this.properties = properties;
    }

    public ArrayList<Property> getProperties() {
        return properties;
    }

    public String getName() {
        return name;
    }
}
