import org.example.Car;
import org.example.ParkingSlot;
import org.junit.Test;

import static org.junit.Assert.*;

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
    public void testIsCarWithRegistrationNumber(){
         ParkingSlot slot = new ParkingSlot(1);
         Car car = new Car("White", "KA-01-HH-1234");
         Car car1 = new Car("Blue",null);
            slot.parkCar(car);
                assertTrue(slot.hasCarWithRegistration("KA-01-HH-1234"));
                assertThrows(IllegalArgumentException.class, () -> slot.hasCarWithRegistration(null));
   }


}
