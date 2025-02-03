package org.example;

import java.util.Objects;
import java.util.UUID;

public class Ticket {
    private final String registrationNumber;
    private final String ticketId;

    public Ticket(String registrationNumber, String ticketId) {
        this.registrationNumber = registrationNumber;
        this.ticketId = ticketId;
    }

    public static Ticket createTicket(Car car) {
       String id = UUID.randomUUID().toString(); //generate unique id
       String registerNumber = car.provideRegistrationNumber();
        return new Ticket(id, registerNumber);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(registrationNumber, ticket.registrationNumber) &&
                Objects.equals(ticketId, ticket.ticketId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(registrationNumber, ticketId);
    }

}
