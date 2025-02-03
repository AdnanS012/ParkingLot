package org.example;

public class Car {
    private final String color;
    private final String RegistrationNumber;

    public Car(String color, String RegistrationNumber) {
        if (color == null || color.isEmpty()) {
            throw new IllegalArgumentException("Color cannot be null or empty");
        }
        if (RegistrationNumber == null || RegistrationNumber.isEmpty()) {
            throw new IllegalArgumentException("Registration number cannot be null or empty");
        }
        this.color = color;
        this.RegistrationNumber = RegistrationNumber;
    }

    public boolean hasRegistrationNumber(String registrationNumber) {
            return this.RegistrationNumber.equals(registrationNumber);
    }

    public boolean hasColor(String color){
        return this.color.equalsIgnoreCase(color);
    }
    protected String provideRegistrationNumber() {
        return RegistrationNumber;
    }


}
