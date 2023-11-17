package com.uottawa.SEG2105BC.gcc_app_grp10.Events;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

public class EventType {
    private String name;

    private ArrayList<PropertyType> properties;

    public EventType(String name){
        this.name=name;
        properties = new ArrayList<>();
    }

    public EventType(String name, ArrayList<PropertyType> propertyTypeList){
        this.name=name;
        properties= (ArrayList<PropertyType>) propertyTypeList.clone();
    }

    public EventType(DataSnapshot dataSnapshot){
        name=dataSnapshot.child("name").getValue(String.class);
        properties = new ArrayList<>();
        for (DataSnapshot propertySnapshot : dataSnapshot.child("properties").getChildren()) {
            properties.add(new PropertyType(propertySnapshot.child("name").getValue(String.class), propertySnapshot.child("type").getValue(String.class)));
        }
    }

    /*
    these ones should only be accessible by admin
     */

    public void addPropertyToType(String newProperty,String type){
        properties.add(new PropertyType(newProperty, type));
    }

    public void removePropertyFromType(String property){
        properties.remove(property);
    }

    public void setProperties(ArrayList<PropertyType> properties) {
        this.properties = properties;
    }

    public ArrayList<PropertyType> getProperties() {
        return properties;
    }

    public String getName() {
        return name;
    }
}
