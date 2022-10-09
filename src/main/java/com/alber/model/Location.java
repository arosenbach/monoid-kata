package com.alber.model;

import java.util.Objects;

public class Location {
    private final double latitude;
    private final double longitude;

    public Location(final double latitude, final double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double distanceInMeters(Location other) {
        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(other.latitude - latitude);
        double lonDistance = Math.toRadians(other.longitude - longitude);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(latitude)) * Math.cos(Math.toRadians(other.longitude))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        distance = Math.pow(distance, 2);

        return Math.sqrt(distance);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Location location = (Location) o;
        return Double.compare(location.latitude, latitude) == 0 && Double.compare(location.longitude, longitude) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitude, longitude);
    }
}
