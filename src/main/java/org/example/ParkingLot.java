package org.example;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private int slotsNumber;
    private Map<Integer, ParkingSlot> parkingSlots;

    public ParkingLot(int slotsNumber) {
        if (slotsNumber <= 0) {
            throw new IllegalArgumentException("Number of slots cannot be negative or zero");
        }
        this.slotsNumber = slotsNumber;
        this.parkingSlots = new HashMap<>();
        for (int i = 1; i <= slotsNumber; i++) {
            parkingSlots.put(i, new ParkingSlot(i));
        }
    }

    public boolean parkCar(Car car) {
        for (ParkingSlot parkingSlot : parkingSlots.values()) {
            if (!parkingSlot.isOccupied()) {
                parkingSlot.parkCar(car);
                return true;
            }
        }
        return false;
    }


    public boolean unparkCar(Car car) {
        for (ParkingSlot slot : parkingSlots.values()) {
            if (slot.isOccupied()) {
                slot.unPark();
                return true;
            }
        }
     return  false;
    }

    public int CountOfCarsByColor(String color) {
        int count = 0;
        for (ParkingSlot parkingSlot : parkingSlots.values()) {
            if (parkingSlot.isOccupied() && parkingSlot.hasCarWithColor(color)) {
                count++;
            }
        }
        return count;
    }

    public boolean isCarParked(String registrationNumber) {
        for (ParkingSlot parkingSlot : parkingSlots.values()) {
            if (parkingSlot.isOccupied() && parkingSlot.hasCarWithRegistration(registrationNumber)) {
                return true;
            }
        }
        return false;
    }
}
