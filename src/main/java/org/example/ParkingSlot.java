package org.example;

public class ParkingSlot {
    private Car car;
    private final int slotNumber;

    public ParkingSlot(int slotNumber) {
        this.slotNumber = slotNumber;
    }

    public boolean isOccupied() {
        return car != null;
    }

    public void parkCar(Car car) {
        if (isOccupied()) {
            throw new IllegalStateException("Slot is already occupied");
        }
        this.car = car;
    }

    public void unPark() {
        if (!isOccupied()) {
            throw new IllegalStateException("Slot is already empty");
        }
        this.car = null;
    }

    public boolean hasCarWithRegistration(String registrationNumber) {
        if (registrationNumber == null || registrationNumber.isEmpty()) {
            throw new IllegalArgumentException("Registration number cannot be null or empty");
        }
        return isOccupied() && car.hasRegistrationNumber(registrationNumber);
    }

    public boolean hasCarWithColor(String color) {
        if (color == null || color.isEmpty()) {
            throw new IllegalArgumentException("Color cannot be null or empty");
        }
        return isOccupied() && car.hasColor(color);
    }
}

