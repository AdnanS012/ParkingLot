import org.example.Car;
import org.example.ParkingSlot;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ParkingSlotTest {
   @Test
    public void testParkAndUnparkCar(){
       ParkingSlot slot = new ParkingSlot(1);
       Car car = new Car("KA-01-HH-1234", "White");
         slot.parkCar(car);
            assertTrue(slot.isOccupied());
            slot.unPark();
            assertFalse(slot.isOccupied());
   }
}
