import org.example.Car;
import org.example.ParkingLot;
import org.example.ParkingSlot;
import org.junit.Test;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import static javax.management.Query.times;
import static jdk.internal.org.objectweb.asm.util.CheckClassAdapter.verify;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ParkingSlotTest {
   @Test
    public void testIsCarParked(){
       ParkingSlot slot = new ParkingSlot(1);
       Car car = new Car("KA-01-HH-1234", "White");
         slot.parkCar(car);
            assertTrue(slot.isOccupied());

   }
   @Test
    public void testIsCarUnparked(){
         ParkingSlot slot = new ParkingSlot(1);
         Car car = new Car("KA-01-HH-1234", "White");
            slot.parkCar(car);
            slot.unPark();
                assertFalse(slot.isOccupied());
   }
    @Test
    public void testUnparkCarCallsUnParkOnSlot() throws Exception {
        // Create a mock ParkingSlot
        ParkingSlot mockSlot = mock(ParkingSlot.class);
        // Simulate that the slot is occupied and has a car with registration "KA-01-HH-1234"
        when(mockSlot.isOccupied()).thenReturn(true);
        when(mockSlot.hasCarWithRegistration("KA-01-HH-1234")).thenReturn(true);

        // Create a Map with the mock slot. Let's assume our ParkingLot uses a Map<Integer, ParkingSlot>
        Map<Integer, ParkingSlot> mockMap = new HashMap<>();
        mockMap.put(1, mockSlot);

        // Create a ParkingLot instance (assuming a default constructor that initializes a map)
        // For example, ParkingLot(int numberOfSlots) would create a Map internally.
        ParkingLot parkingLot = new ParkingLot(1);

        // Inject our mocked map into the parkingLot instance using reflection.
        Field parkingSlotsField = ParkingLot.class.getDeclaredField("parkingSlots");
        parkingSlotsField.setAccessible(true);
        parkingSlotsField.set(parkingLot, mockMap);

        // Act: Call unparkCar on the parkingLot
        boolean result = parkingLot.unparkCar("KA-01-HH-1234");

        // Assert: The result should be true and unPark() should have been called once.
        assertTrue(result);
        Mockito.verify(mockSlot, Mockito.times(1)).unPark();
    }
    @Test
    public void testParkCarAssignsNearestSlot() throws Exception {
        // Create a spy ParkingSlot for slot 1
        ParkingSlot spySlot1 = Mockito.spy(new ParkingSlot(1));
        // Create a spy ParkingSlot for slot 2
        ParkingSlot spySlot2 = Mockito.spy(new ParkingSlot(2));

        // Prepare a map with two slots
        Map<Integer, ParkingSlot> mockMap = new HashMap<>();
        mockMap.put(1, spySlot1);
        mockMap.put(2, spySlot2);

        // Create a ParkingLot with 2 slots.
        ParkingLot parkingLot = new ParkingLot(2);

        // Inject our custom map into the parkingLot instance using reflection.
        Field parkingSlotsField = ParkingLot.class.getDeclaredField("parkingSlots");
        parkingSlotsField.setAccessible(true);
        parkingSlotsField.set(parkingLot, mockMap);

        // Create a car and park it.
        Car car = new Car("KA-01-HH-1234", "White");
        boolean parked = parkingLot.parkCar(car);
        assertTrue(parked);

        // Verify that the car is parked in the first available slot.
        Mockito.verify(spySlot1, Mockito.times(1)).parkCar(car);
    }


}
