package com.uottawa.SEG2105BC.gcc_app_grp10.Events;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

public class EventType {
    private String name;

    private ArrayList<PropertyType> propertyTypes;

    public EventType(String name){
        this.name=name;
        propertyTypes = new ArrayList<>();
    }

    public EventType(String name, ArrayList<PropertyType> propertyTypeList){
        this.name=name;
        propertyTypes = (ArrayList<PropertyType>) propertyTypeList.clone();
    }

    public EventType(DataSnapshot dataSnapshot){
        name=dataSnapshot.child("name").getValue(String.class);
        propertyTypes = new ArrayList<>();
        for (DataSnapshot propertySnapshot : dataSnapshot.child("properties").getChildren()) {
            propertyTypes.add(new PropertyType(propertySnapshot.child("name").getValue(String.class), propertySnapshot.child("type").getValue(String.class)));
        }
    }

    /*
    these ones should only be accessible by admin
     */

    public void addPropertyToType(String newProperty,String type){
        propertyTypes.add(new PropertyType(newProperty, type));
    }

    public void removePropertyTypeFromEventType(String property){
        propertyTypes.remove(property);
    }

    public void setPropertyTypes(ArrayList<PropertyType> propertyTypes) {
        this.propertyTypes = propertyTypes;
    }

    public ArrayList<PropertyType> getPropertyTypes() {
        return propertyTypes;
    }

    public String getName() {
        return name;
    }
}
