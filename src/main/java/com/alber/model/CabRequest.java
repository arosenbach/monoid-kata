package com.alber.model;

public class CabRequest {
    private final Location source;
    private final Location destination;
    private final Cab.Type type;
    private final int seats;

    public CabRequest(final Location source, final Location destination, final Cab.Type type, final int seats) {
        this.source = source;
        this.destination = destination;
        this.type = type;
        this.seats = seats;
    }

    public Cab.Type getType() {
        return this.type;
    }

    public int getNumOfSeats() {
        return seats;
    }

    public Location getSource() {
        return source;
    }

    public Location getDestination() {
        return destination;
    }
}
