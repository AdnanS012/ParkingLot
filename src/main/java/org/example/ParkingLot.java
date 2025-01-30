package org.example;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private int slotsNumber;
    private Map<Integer, Car> parkingSlots;

    public ParkingLot(int slotsNumber) {
        if (slotsNumber <= 0) {
            throw new IllegalArgumentException("Number of slots cannot be negative or zero");
        }
        this.slotsNumber = slotsNumber;
        this.parkingSlots = new HashMap<>();
    }

    public boolean parkCar(Car car) {
        for (int i = 1; i <= slotsNumber; i++) {
            if (!parkingSlots.containsKey(i)) {
                parkingSlots.put(i, car);
                return true;
            }
        }
        return false;
    }

    public boolean unparkCar(int slot) {
        if (parkingSlots.containsKey(slot)) {
            parkingSlots.remove(slot);
            return true;
        }
        return false;
    }

    public int getCountOfCarsByColor(String color) {
        int count = 0;
        for (Car car : parkingSlots.values()) {
            if (car.getColor().equalsIgnoreCase(color)) {
                count++;
            }
        }
        return count;

    }
    public boolean isCarParked(String registrationNumber){
        for (Car car : parkingSlots.values()) {
            if (car.getRegistrationNumber().equalsIgnoreCase(registrationNumber)) {
                return true;
            }
        }
        return false;
    }
}
