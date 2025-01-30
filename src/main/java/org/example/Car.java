package org.example;

public class Car {
    private final String color;
    private final String getRegistrationNumber;

    public Car(String color,String getRegistrationNumber) {
        this.color = color;
        this.getRegistrationNumber = getRegistrationNumber;;
    }

    public String getColor() {
        return color;
    }
    public String getRegistrationNumber() {
        return getRegistrationNumber;
    }



}
