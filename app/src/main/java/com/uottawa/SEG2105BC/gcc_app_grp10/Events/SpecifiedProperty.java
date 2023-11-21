package com.uottawa.SEG2105BC.gcc_app_grp10.Events;

import java.lang.reflect.Type;

public class SpecifiedProperty extends PropertyType {

    Object value;


    public SpecifiedProperty(Object value, PropertyType propertyType) throws PropertyTypeMismatchException {
        super(propertyType);
        setValue(value);
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) throws PropertyTypeMismatchException {
        if(validateType(value))this.value = value;
        else throw new PropertyTypeMismatchException();
    }

    /**
     * checks if value's type matches the eventProperties type, will return true if value is null
     * @return whether or not the type is invalid
     */
    public boolean validateType(){
        return value==null||super.getType().equals(value.getClass());
    }

    /**
     * checks if value's type matches the eventProperties type, will return true if value is null
     * @return whether or not the type is invalid
     */
    public boolean validateType(Object value){
        return value==null||super.getType().equals(value.getClass());
    }

    public boolean validateType(Type type){
        return value==null||type.equals(value.getClass());
    }

    @Override
    public void setType(Type type) throws PropertyTypeMismatchException {
        if(type==null||!validateType(type)){
            throw new PropertyTypeMismatchException();
        }
        super.setType(type);
    }

    @Override
    public void setType(String type) throws PropertyTypeMismatchException {
        try {
            setType(Class.forName(type));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }



}
