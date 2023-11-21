package com.uottawa.SEG2105BC.gcc_app_grp10.Events;

import java.io.Serializable;
import java.lang.reflect.Type;

public class PropertyType implements Serializable {

    private Type type;
    private String name;

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
        try {
            this.type=Class.forName(type);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * like all the other methods, SpecifiedProperty inherits this
     * and it still overrides it's equal method
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o){
        if(o instanceof PropertyType){
            if(((PropertyType) o).getName().equals(name)){
                return true;
            }
        }
        return false;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) throws PropertyTypeMismatchException {
        try {
            setType(Class.forName(type));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void setType(Type type) throws PropertyTypeMismatchException {
        this.type=type;
    }

    public Type getType() {
        return type;
    }

    public PropertyType clone(){
        return new PropertyType(this);
    }

}
