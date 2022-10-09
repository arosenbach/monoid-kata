package com.alber.model.rules;

import com.alber.model.Cab;
import com.alber.model.Location;

import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

@FunctionalInterface
public interface Rule {

    static Rule identity() {
        return cabs -> cabs;
    }

    default Rule combine(Rule after) {
        Objects.requireNonNull(after);
        return cabs -> after.apply(this.apply(cabs));
    }

    static Rule matchRoutes(Location source, Location destination) {
        return cabs -> cabs
                .stream()
                .filter(c -> c.getFinalDestination().equals(destination) && c.getCurrentLocation().equals(source))
                .collect(toList());
    }

    static Rule matchType(Cab.Type type) {
        return cabs -> cabs
                .stream()
                .filter(cab -> cab.getType().equals(type))
                .collect(toList());
    }

    static Rule matchSeats(int numOfSeats) {
        return cabs -> cabs
                .stream()
                .filter(cab -> cab.getSeatsAvailable() >= numOfSeats)
                .collect(toList());
    }

    List<Cab> apply(List<Cab> cabs);
}