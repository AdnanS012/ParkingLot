package org.example;

public class Car {
    private final String color;
    private final String RegistrationNumber;

    public Car(String color, String RegistrationNumber) {
        this.color = color;
        this.RegistrationNumber = RegistrationNumber;
    }

    public boolean hasRegistrationNumber(String registrationNumber) {
            return this.RegistrationNumber.equals(registrationNumber);
    }

    public boolean hasColor(String color){
        return this.color.equalsIgnoreCase(color);
    }


}
