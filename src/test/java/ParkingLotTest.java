import org.example.Car;
import org.example.ParkingLot;
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
    public void testParkCar() {
        ParkingLot parkingLot = new ParkingLot(6);
        Car car = new Car("KA-01-HH-1234", "White");
        assertTrue(parkingLot.parkCar(car));
    }

    @Test
    public void testUnparkCar() {
        ParkingLot parkingLot = new ParkingLot(6);
        Car car = new Car("White", "KA-01-HH-1234");
        parkingLot.parkCar(car);
        assertTrue(parkingLot.unparkCar("KA-01-HH-1234"));
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
    public void testParkCarWithNoColorThrowsException() {
        ParkingLot parkingLot = new ParkingLot(6);
        assertThrows(IllegalArgumentException.class, () -> new Car(null, "KA-01-HH-1234"));
    }

    @Test
    public void testIsParkedWithRegistrationNumber(){
        ParkingLot parkingLot = new ParkingLot(6);
        Car car1 = new Car("White", "KA-01-HH-1234");
        Car car2 = new Car("White", "KA-01-HH-9999");
        Car car3 = new Car("Black", "KA-01-HH-1001");
        Car car4 = new Car("Blue", "KA-01-HH-1002");
        parkingLot.parkCar(car1);
        parkingLot.parkCar(car2);
        parkingLot.parkCar(car3);
        parkingLot.parkCar(car4);

        assertTrue(parkingLot.isCarParked("KA-01-HH-1234"));
    }

    @Test
    public void testParkAndUnparkSameCarMultipleTimes(){
        ParkingLot parkingLot = new ParkingLot(6);
        Car car = new Car("White", "KA-01-HH-1234");
        parkingLot.parkCar(car);
        assertTrue(parkingLot.unparkCar("KA-01-HH-1234"));
        assertFalse(parkingLot.unparkCar("KA-01-HH-1234"));
    }
    @Test
    public void testIsCarParkedWithInvalidRegistration(){
        ParkingLot parkingLot = new ParkingLot(6);
        Car car1 = new Car("White", "KA-01-HH-1234");
        Car car2 = new Car("White", "KA-01-HH-9999");
        Car car3 = new Car("Black", "KA-01-HH-1001");
        Car car4 = new Car("Blue", "KA-01-HH-1002");
        parkingLot.parkCar(car1);
        parkingLot.parkCar(car2);
        parkingLot.parkCar(car3);
        parkingLot.parkCar(car4);

        assertFalse(parkingLot.isCarParked("KA-01-HH-1235"));
    }
    @Test
    public void testCountOfCarsByColorWithNoCars(){
        ParkingLot parkingLot = new ParkingLot(6);
        assertEquals(0, parkingLot.CountOfCarsByColor("White"));
    }
}
