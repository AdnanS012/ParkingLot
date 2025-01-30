import org.example.ParkingSlot;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class ParkingSlotTest {
    @Test
    public void testParkingSlotIsEmpty(){
        ParkingSlot parkingSlot = new ParkingSlot();
        assertFalse(parkingSlot.isOccupied());

    }
}
