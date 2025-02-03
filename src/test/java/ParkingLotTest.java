import org.example.Car;
import org.example.ParkingLot;
import org.example.ParkingSlot;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import static jdk.internal.org.objectweb.asm.util.CheckClassAdapter.verify;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
    @Test
    public void testUnparkCarCallsUnParkOnSlot() throws Exception {
        // Create a mock ParkingSlot
        ParkingSlot mockSlot = mock(ParkingSlot.class);
        // Stub the behavior: slot is occupied and the slot recognizes the registration.
        when(mockSlot.isOccupied()).thenReturn(true);
        when(mockSlot.hasCarWithRegistration("KA-01-HH-1234")).thenReturn(true);

        // Prepare a Map with the mock slot.
        Map<Integer, ParkingSlot> mockMap = new HashMap<>();
        mockMap.put(1, mockSlot);

        // Create a ParkingLot instance.
        ParkingLot parkingLot = new ParkingLot(1);

        // Inject our mocked map into the ParkingLot instance using reflection.
        Field parkingSlotsField = ParkingLot.class.getDeclaredField("parkingSlots");
        parkingSlotsField.setAccessible(true);
        parkingSlotsField.set(parkingLot, mockMap);

        // Act: Call unparkCar on the parking lot.
        boolean result = parkingLot.unparkCar("KA-01-HH-1234");

        // Assert: The result should be true, and unPark() should have been called once on the mock.
        assertTrue(result);
        Mockito.verify(mockSlot, times(1)).unPark();
    }
    @Test
    public void testParkCarAssignsToNearestSlot() throws Exception {
        // Create spies for two actual ParkingSlot instances.
        ParkingSlot spySlot1 = Mockito.spy(new ParkingSlot(1));
        ParkingSlot spySlot2 = Mockito.spy(new ParkingSlot(2));

        // Prepare a Map with these two slots.
        Map<Integer, ParkingSlot> mockMap = new HashMap<>();
        mockMap.put(1, spySlot1);
        mockMap.put(2, spySlot2);

        // Create a ParkingLot instance with two slots.
        ParkingLot parkingLot = new ParkingLot(2);

        // Inject our custom map into the ParkingLot instance.
        Field parkingSlotsField = ParkingLot.class.getDeclaredField("parkingSlots");
        parkingSlotsField.setAccessible(true);
        parkingSlotsField.set(parkingLot, mockMap);

        // Create a car and park it.
        Car car = new Car("KA-01-HH-1234", "White");
        boolean parked = parkingLot.parkCar(car);
        assertTrue(parked);

        // Verify that the car was parked in the first (nearest) slot.
        Mockito.verify(spySlot1, times(1)).parkCar(car);
        // Optionally, you can also check that slot2 did not receive the car.
        Mockito.verify(spySlot2, never()).parkCar(any());
    }

    @Test
    public void testUnParkClearsTheSlot() {

        ParkingSlot spySlot = spy(new ParkingSlot(1));
        Car car = new Car("KA-01-HH-1234", "White");
        spySlot.parkCar(car);
        assertTrue(spySlot.isOccupied());

        spySlot.unPark();

        Mockito.verify(spySlot, times(1)).unPark();
        assertFalse(spySlot.isOccupied());
    }
}
