import org.example.Car;
import org.example.ParkingLot;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {

      @Test
      public void testInvalidArgumentExceptionWhenSlotIsZero(){
          assertThrows(IllegalArgumentException.class, () -> new ParkingLot(0));
      }
        @Test
    public void testCreateParkingLotWithSlots(){
            ParkingLot parkingLot = new ParkingLot(6);
            assertNotNull(parkingLot);
        }

            @Test
    public void testParkCar(){
            ParkingLot parkingLot = new ParkingLot(6);
            Car car = new Car("KA-01-HH-1234", "White");
                assertTrue(parkingLot.parkCar(car));
        }
            @Test
    public void testUnparkCar(){
            ParkingLot parkingLot = new ParkingLot(6);
            Car car = new Car("KA-01-HH-1234", "White");
            parkingLot.parkCar(car);
            assertTrue(parkingLot.unparkCar(1));
        }



//        @Test
//    public void testParkCarInNearestAvailableSlots(){
//            ParkingLot parkingLot = new ParkingLot(6);
//            Car car1 = new Car("KA-01-HH-1234", "White");
//            Car car2 = new Car("KA-01-HH-9999", "White");
//            parkingLot.parkCar(car1);
//            parkingLot.parkCar(car2);
//            assertEquals(2, parkingLot.getSlotNumberByCar(car2));
//
//        }
//        @Test
//    public void testUnparkCar(){
//            ParkingLot parkingLot = new ParkingLot();
//            Car car = new Car("KA-01-HH-1234", "White");
//            parkingLot.parkCar(car);
//            assertTrue(parkingLot.leaveCar(1));
//        }

}
