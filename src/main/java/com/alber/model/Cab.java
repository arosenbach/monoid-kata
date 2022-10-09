package com.alber.model;

import java.util.Objects;

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

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Cab cab = (Cab) o;
        return seatsAvailable == cab.seatsAvailable && totalSeats == cab.totalSeats && type == cab.type && Objects.equals(currentLocation, cab.currentLocation) && Objects.equals(finalDestination, cab.finalDestination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, seatsAvailable, totalSeats, currentLocation, finalDestination);
    }
}
