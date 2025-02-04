package org.example;

import java.util.Objects;
import java.util.UUID;

public class Ticket {
    private final String ticketId;
    private final Car car;

    private Ticket(String ticketId, Car car) {
        if (ticketId == null || car == null) {
            throw new IllegalArgumentException("Ticket Id or Car cannot be null");
        }
            this.ticketId = ticketId;
            this.car = car;


    }
    public static Ticket createTicket (Car car){
        return new Ticket(UUID.randomUUID().toString(), car);
    }
    public boolean isForCar(Car otherCar){
        return this.car == otherCar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ticket)) return false;
        Ticket ticket = (Ticket) o;
        return ticketId.equals(ticket.ticketId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketId);
    }
    @Override
    public String toString() {
        return "Ticket{" + "ticketId='" + ticketId + "'}";
    }

}
