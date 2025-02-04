package org.example;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private final Map<Ticket,Integer> ticketSlotMap;
    private final Map<Integer, ParkingSlot> parkingSlots;

    public ParkingLot(int slotsNumber) {
        if (slotsNumber <= 0) {
            throw new IllegalArgumentException("Number of slots cannot be negative or zero");
        }

        this.parkingSlots = new HashMap<>();
        this.ticketSlotMap = new HashMap<>();
        for (int i = 1; i <= slotsNumber; i++) {
            parkingSlots.put(i, new ParkingSlot(i));
        }
    }
   //parks the car and issues a ticket
    public Ticket parkCar(Car car) {
        for (Map.Entry<Integer, ParkingSlot> entry : parkingSlots.entrySet()) {
            ParkingSlot parkingSlot = entry.getValue();
            if (!parkingSlot.isOccupied()) {
                parkingSlot.parkCar(car);
                Ticket ticket = Ticket.createTicket(car);
                ticketSlotMap.put(ticket, entry.getKey());
                return ticket;
            }
        }
        throw new IllegalArgumentException("Parking lot is full");
    }

     //Un-parks the car associated with the given ticket
    public boolean unparkCar(Ticket ticket) {
        Integer slotNumber = ticketSlotMap.get(ticket);
        if (slotNumber == null) {
            return false;
        }
        ParkingSlot parkingSlot = parkingSlots.get(slotNumber);
        if (parkingSlot.isOccupied()) {
            parkingSlot.unPark();
            ticketSlotMap.remove(ticket);
            return true;
        }
        return false;
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

    public boolean isCarParked(Ticket ticket) {
       return ticketSlotMap.containsKey(ticket);
    }
}
