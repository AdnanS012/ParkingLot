import org.example.Car;
import org.example.Ticket;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TicketTest {
    @Test
    public void testTicketCannotBeCreatedWithNullValues() {
        assertThrows(NullPointerException.class, () -> Ticket.createTicket(null));
        assertThrows(IllegalArgumentException.class, () -> Ticket.createTicket(new Car(null, "White")));
        assertThrows(IllegalArgumentException.class, () -> Ticket.createTicket(new Car("KA-01-HH-1234", null)));
    }

    @Test
    public void testTicketShouldNotBeNullWhenCreatedForACar() {
        Car car = new Car("White", "KA-01-HH-1234");
        Ticket ticket = Ticket.createTicket(car);
        Assertions.assertNotNull(ticket, "the issued ticket should not be null");
    }

    @Test
    public void testTicketsAreUnique() {
        Car car1 = new Car("White", "KA-01-HH-1234");
        Car car2 = new Car("Black", "KA-01-HH-9999");
        Ticket ticket1 = Ticket.createTicket(car1);
        Ticket ticket2 = Ticket.createTicket(car2);
        Assertions.assertNotEquals(ticket1, ticket2, "the ticket numbers should not be the same");
    }

    @Test
    public void testTicketsWithSameCarAreNotEqual() {
        Car car = new Car("White", "KA-01-HH-1234");
        Ticket ticket1 = Ticket.createTicket(car);
        Ticket ticket2 = Ticket.createTicket(car);
        assertNotEquals(ticket1, ticket2, "the tickets should be equal based on the id");
    }

    @Test
    public void testTicketShouldBeEqualToItself() {
        Car car = new Car("White", "KA-01-HH-1234");
        Ticket ticket = Ticket.createTicket(car);
        assertEquals(ticket.hashCode(), ticket.hashCode(), "the ticket should be equal to itself");
    }


}
