import org.example.Car;
import org.example.ParkingLot;
import org.example.Ticket;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {

    @Test
    public void testInvalidArgumentExceptionWhenSlotIsZeroOrNegative() {
        assertThrows(IllegalArgumentException.class, () -> new ParkingLot(0));
        assertThrows(IllegalArgumentException.class, () -> new ParkingLot(-1));
    }

    @Test
    public void testCreateParkingLotWithSlots() {
        ParkingLot parkingLot = new ParkingLot(6);
        assertNotNull(parkingLot);
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
    @Test
    public void testTotalCarsCountByColor() {
        ParkingLot parkingLot = new ParkingLot(6);
        Car car1 = new Car("White", "KA-01-HH-1234");
        Car car2 = new Car("White", "KA-01-HH-9999");
        Car car3 = new Car("Black", "KA-01-HH-1001");
        Car car4 = new Car("Blue", "KA-01-HH-1002");
        parkingLot.parkCar(car1);
        parkingLot.parkCar(car2);
        parkingLot.parkCar(car3);
        parkingLot.parkCar(car4);

        assertEquals(2, parkingLot.CountOfCarsByColor("White"));
    }
    @Test
    public void testIsCarParked() {
        ParkingLot parkingLot = new ParkingLot(6);
        Car car = new Car("White", "KA-01-HH-1234");
        Ticket ticket = parkingLot.parkCar(car);
        assertTrue(parkingLot.isCarParked(ticket));
    }

}
