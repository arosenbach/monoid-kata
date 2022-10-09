package com.alber.model;

public class Cab {

    public boolean isEmpty() {
        return this.seatsAvailable == this.totalSeats;
    }

    public static enum Type {
        BASIC, PREMIUM, POOL;
    }

    private final Type type;
    private final int seatsAvailable;
    private final int totalSeats;
    private final Location currentLocation;
    private final Location finalDestination;

    public Cab(final Type type, final int seatsAvailable, final int totalSeats, final Location currentLocation, final Location finalDestination) {
        this.type = type;
        this.seatsAvailable = seatsAvailable;
        this.totalSeats = totalSeats;
        this.currentLocation = currentLocation;
        this.finalDestination = finalDestination;
    }

    public Location getFinalDestination() {
        return this.finalDestination;
    }

    public Location getCurrentLocation() {
        return this.currentLocation;
    }

    public Type getType() {
        return type;
    }

    public int getSeatsAvailable() {
        return this.seatsAvailable;
    }
}
