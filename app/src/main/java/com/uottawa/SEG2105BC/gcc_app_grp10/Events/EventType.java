package com.uottawa.SEG2105BC.gcc_app_grp10.Events;

import com.google.firebase.database.DataSnapshot;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class EventType {
    private String name;

    private HashMap<String, Type> properties;



    public EventType(String name){
        this.name=name;
        properties=new HashMap<>();
    }

    public EventType(String name, HashMap<String, Type> propertyList){
        this.name=name;
        properties= (HashMap<String, Type>) propertyList.clone();
    }

    public EventType(DataSnapshot dataSnapshot){
        name=dataSnapshot.child("name").getValue(String.class);
        properties =dataSnapshot.child("username").getValue(HashMap.class);
    }

    /*
    these ones should only be accessible by admin
     */

    public void addPropertyToType(String newProperty,Type type){
        properties.put(newProperty, type);
    }
    public void addPropertyToType(String newProperty, String type) throws ClassNotFoundException {

        addPropertyToType(newProperty,Class.forName(type));
    }

    public void removePropertyFromType(String property){
        properties.remove(property);
    }

    public void setProperties(HashMap<String, Type> properties) {
        this.properties = properties;
    }

    public HashMap<String, Type> getProperties() {
        return properties;
    }

    public String getName() {
        return name;
    }
}
