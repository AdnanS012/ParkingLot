import org.example.Car;
import org.example.ParkingLot;
import org.example.Ticket;

import java.util.ArrayList;
import java.util.List;


public class ParkingLotAttendant {
    private final String Name;
    private final List<ParkingLot> assignedParkingLots;

    public ParkingLotAttendant(String name) {
        this.Name = name;
        this.assignedParkingLots = new ArrayList<>();

    }

    public void assignParkingLot(ParkingLot parkingLot) {
        if (parkingLot == null) {
            throw new IllegalArgumentException("Parking lot cannot be null");
        }
        assignedParkingLots.add(parkingLot);
    }

    public Ticket parkCar(Car car){
        if(assignedParkingLots.isEmpty()){
            throw new IllegalArgumentException("No parking lots assigned");
        }
        for(ParkingLot lot : assignedParkingLots){
                return lot.parkCar(car);
        }
        throw new IllegalArgumentException("All parking lots are full");
    }

    public boolean unparkCar(Ticket ticket){
        if(assignedParkingLots.isEmpty()){
            throw new IllegalArgumentException("No parking lots assigned");
        }
        for(ParkingLot lot : assignedParkingLots){
            if(lot.isCarParked(ticket)){
                return lot.unparkCar(ticket);
            }
        }
        return false;
    }

    public boolean isManagingParkingLot(ParkingLot parkingLot) {
        return assignedParkingLots.contains(parkingLot);
    }
}
