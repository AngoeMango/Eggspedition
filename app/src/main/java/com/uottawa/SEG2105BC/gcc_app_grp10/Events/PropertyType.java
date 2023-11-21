package com.uottawa.SEG2105BC.gcc_app_grp10.Events;

import java.io.Serializable;

public class PropertyType implements Serializable {
    String type;
    String name;

    public PropertyType(){

    }

    public PropertyType(String name){
        this.name=name;
    }

    public PropertyType(PropertyType propertyType){
        this.type=propertyType.getType();
        this.name= propertyType.getName();
    }

    public PropertyType(String name, String type){
        this.name=name;
        this.type=type;
    }


    //override
    public boolean equals(){
return true;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type){
        this.type=type;
    }

    public String getType() {
        return type;
    }

    public PropertyType clone(){
        return new PropertyType(this);
    }

}
