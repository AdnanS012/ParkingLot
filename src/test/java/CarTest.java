import org.example.Car;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class CarTest {
    @Test
    public void testCarConstructor() {
        Car car = new Car("White", "KA-01-HH-1234");
        assertNotNull(car);
    }
    @Test
    public void testCarWithNoColorThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new Car(null, "KA-01-HH-1234"));
    }
    @Test
    public void testCarWithNoRegistrationNumberThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new Car("White", null));
    }
    @Test
    public void testHasColorCaseInsensitive() {
        Car car = new Car("White", "KA-01-HH-1234");
        assertTrue(car.hasColor("white"));
        assertTrue(car.hasColor("WHITE"));
    }
    @Test
    public void testHasRegistrationNumberCaseInsensitive() {
        Car car = new Car("White", "KA-01-HH-1234");
        assertFalse(car.hasRegistrationNumber("ka-01-Mh-9234"));
        assertTrue(car.hasRegistrationNumber("KA-01-HH-1234"));
    }


}
