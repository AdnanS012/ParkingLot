import org.example.Car;
import org.example.ParkingSlot;
import org.junit.Test;
import static org.junit.Assert.*;

public class ParkingSlotTest {
    @Test
    public void testSlotOccupied(){
        ParkingSlot slot = new ParkingSlot(1);
        assertFalse(slot.isOccupied());
    }
    @Test
    public void testParkCarThrowsExceptionWhenSlotIsOccupied() {
        ParkingSlot slot = new ParkingSlot(1);
        Car car1 = new Car("KA-01-HH-1234", "White");
        Car car2 = new Car("KA-01-HH-9999", "Black");
        slot.parkCar(car1);
        assertThrows(IllegalStateException.class, () -> slot.parkCar(car2));
    }
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
    public void testHasCarWithColor() {
        ParkingSlot slot = new ParkingSlot(1);
        Car car = new Car("White", "KA-01-HH-1234");
        slot.parkCar(car);
        assertTrue(slot.hasCarWithColor("White"));
    }
    @Test
    public void testHasCarWithRegistration() {
        ParkingSlot slot = new ParkingSlot(1);
        Car car = new Car("Black", "KA-01-HH-1234");
        slot.parkCar(car);
        assertTrue(slot.hasCarWithRegistration("KA-01-HH-1234"));
        assertFalse(slot.hasCarWithRegistration("KA-01-HH-9999"));
    }

}
