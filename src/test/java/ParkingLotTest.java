import org.example.Car;
import org.example.ParkingLot;
import org.example.Ticket;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {

    @Test
    public void testInvalidArgumentExceptionWhenSlotIsZero() {
        assertThrows(IllegalArgumentException.class, () -> new ParkingLot(0));
    }
    @Test
    public void testInvalidArgumentExceptionWhenSlotIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> new ParkingLot(-1));
    }

    @Test
    public void testCreateParkingLotWithSlots() {
        ParkingLot parkingLot = new ParkingLot(6);
        assertNotNull(parkingLot);
    }
    @Test
    public void testCreateParkingLotWithZeroSlots() {
        assertThrows(IllegalArgumentException.class, () -> new ParkingLot(0));
    }
    @Test
    public void testCreateParkingLotWithNegativeSlots() {
        assertThrows(IllegalArgumentException.class, () -> new ParkingLot(-1));
    }
    @Test
    public void testParkCarReturnTicket(){
        ParkingLot parkingLot = new ParkingLot(6);
        Car car = new Car("KA-01-HH-1234", "White");
        Ticket ticket = parkingLot.parkCar(car);
        assertNotNull(ticket,"parking should return a ticket");
    }
    @Test
    public void testUnparkCarWithInvalidTicketFails() {
        ParkingLot parkingLot = new ParkingLot(3);
        Car car = new Car("KA-01-HH-1234", "White");
        parkingLot.parkCar(car);

        Ticket fakeTicket = Ticket.createTicket(new Car("KA-01-HH-5678", "Black"));
        assertFalse(parkingLot.unparkCar(fakeTicket), "Unparking should fail with an invalid ticket");
    }

    @Test
    public void testUnparkSameCarTwiceFails(){
        ParkingLot parkingLot = new ParkingLot(3);
        Car car = new Car("KA-01-HH-1234", "White");
        Ticket ticket = parkingLot.parkCar(car);

        assertTrue(parkingLot.unparkCar(ticket));
        assertFalse(parkingLot.unparkCar(ticket));
    }
    @Test
    public void testParkAndUnparkSameVehicle() {
        ParkingLot parkingLot = new ParkingLot(5);
        Car car = new Car("KA-01-HH-1234", "White");
        Ticket ticket = parkingLot.parkCar(car);
        assertNotNull(ticket, "Ticket should be issued when parking a car");
        assertTrue(parkingLot.isCarParked(ticket), "The car should be parked");
        assertTrue(parkingLot.unparkCar(ticket), "The same car should be successfully unparked");
        assertFalse(parkingLot.isCarParked(ticket), "The car should no longer be parked after being unparked");
    }


    @Test
    public void testCheckIfCarIsParkedWithValidTicket() {
        ParkingLot parkingLot = new ParkingLot(3);
        Car car = new Car("KA-01-HH-1234", "White");
        Ticket ticket = parkingLot.parkCar(car);

        assertTrue(parkingLot.isCarParked(ticket), "Should return true for a valid parked ticket");
    }

}
