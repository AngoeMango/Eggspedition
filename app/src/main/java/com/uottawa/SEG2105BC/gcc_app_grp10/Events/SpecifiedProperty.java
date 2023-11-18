package com.uottawa.SEG2105BC.gcc_app_grp10.Events;

public class SpecifiedProperty extends PropertyType {
    Object value;

    public SpecifiedProperty(Object value, PropertyType propertyType){
        super(propertyType);
        this.value=value;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    /**
     * checks if value's type matches the eventProperties type, will return true if value is null
     * @return whether or not the type is invalid
     */
    public boolean validateType(){
        return value==null||super.type.getClass().equals(value.getClass());
    }

}
