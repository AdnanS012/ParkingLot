package org.example;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private int slots;
    private Map<Integer,Car> parkingSlots;
    public ParkingLot(int slots) {
        if (slots <= 0) {
            throw new IllegalArgumentException("Number of slots cannot be negative or zero");
        }
        this.slots = slots;
        this.parkingSlots = new HashMap<>();
    }
      public boolean parkCar(Car car){
        for(int i=1;i<=slots;i++){
            if(!parkingSlots.containsKey(i)){
                parkingSlots.put(i,car);
                return true;
            }
        }
        return false;
      }
      public boolean unparkCar(int slot){
        if(parkingSlots.containsKey(slot)){
            parkingSlots.remove(slot);
            return true;
        }
        return false;
      }

}
