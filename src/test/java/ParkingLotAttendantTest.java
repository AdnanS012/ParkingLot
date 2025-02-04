import org.example.Car;
import org.example.ParkingLot;
import org.example.Ticket;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParkingLotAttendantTest {
    @Test
    public void testParkingLotAttendant() {
        ParkingLotAttendant parkingLotAttendant = new ParkingLotAttendant("John");
        assertNotNull(parkingLotAttendant);
    }
    @Test
    public void testAssignParkinglot(){
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingLotAttendant parkingLotAttendant = new ParkingLotAttendant("John");
        parkingLotAttendant.assignParkingLot(parkingLot);

        assertTrue(parkingLotAttendant.isManagingParkingLot(parkingLot));
    }
    @Test
    public void testAttendantParksCarInAssignedParkingLot(){
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLotAttendant attendant = new ParkingLotAttendant("David");

        attendant.assignParkingLot(parkingLot1);
        Car car = new Car("KA-01-HH-1234", "White");
        Ticket ticket = attendant.parkCar(car);
        assertNotNull(ticket);
        assertTrue(parkingLot1.isCarParked(ticket));
    }

    @Test
    public void testAttendantFailsToParkWithoutAssignedLots(){
        ParkingLotAttendant attendant = new ParkingLotAttendant("David");
        Car car = new Car("KA-01-HH-1234", "White");
        assertThrows(IllegalArgumentException.class, () -> attendant.parkCar(car));
    }
    @Test
    public void testAttendantUnparksCarSuccessfully(){
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLotAttendant attendant = new ParkingLotAttendant("David");

        attendant.assignParkingLot(parkingLot1);
        Car car = new Car("KA-01-HH-1234", "White");
        Ticket ticket = attendant.parkCar(car);
        assertTrue(attendant.unparkCar(ticket));
        assertFalse(parkingLot1.isCarParked(ticket));

    }

    @Test
    public void testAttendantParksCarInMultipleParkingLots(){
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(1);
        ParkingLotAttendant attendant = new ParkingLotAttendant("David");

        attendant.assignParkingLot(parkingLot1);
        attendant.assignParkingLot(parkingLot2);

        Car car = new Car("KA-01-HH-1234", "White");
        Ticket ticket = attendant.parkCar(car);
        assertNotNull(ticket);
        assertTrue(parkingLot1.isCarParked(ticket) || parkingLot2.isCarParked(ticket));
    }
    @Test
    public void testAttendantUnparksCarFromMultipleParkingLots(){
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(1);
        ParkingLotAttendant attendant = new ParkingLotAttendant("David");

        attendant.assignParkingLot(parkingLot1);
        attendant.assignParkingLot(parkingLot2);

        Car car = new Car("KA-01-HH-1234", "White");
        Ticket ticket = attendant.parkCar(car);

        // Ensure the car is parked in one of the parking lots
        assertTrue(parkingLot1.isCarParked(ticket) || parkingLot2.isCarParked(ticket));

        // Unpark the car
        assertTrue(attendant.unparkCar(ticket));

        // Ensure the car is no longer parked in any of the parking lots
        Assertions.assertFalse(parkingLot1.isCarParked(ticket));
        Assertions.assertFalse(parkingLot2.isCarParked(ticket));    }

}
