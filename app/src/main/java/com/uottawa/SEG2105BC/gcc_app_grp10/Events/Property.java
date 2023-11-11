package com.uottawa.SEG2105BC.gcc_app_grp10.Events;

import java.io.Serializable;
import java.lang.reflect.Type;

public class Property implements Serializable {
    String type;
    String name;
//    Object value;


    public Property(){

    }

    public Property(String name){
        this.name=name;
    }

    public Property(String name, String type){
        this.name=name;
        this.type=type;
    }

//    public Property(String name, String type, Object value){
//        this.name=name;
//        this.type=type;
//        this.setValue(value);
//    }

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

//    public Object getValue(){
//        return value;
//    }
//
//    public void setValue(Object value) {
//        this.value = value;
//    }
}
