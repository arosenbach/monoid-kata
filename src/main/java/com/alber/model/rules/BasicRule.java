package com.alber.model.rules;

import com.alber.model.Cab;
import com.alber.model.CabRequest;
import com.alber.model.Location;

import java.util.List;

public class BasicRule implements Rule{
    private final Location source;
    private final Location destination;
    private final int numOfSeats;

    public BasicRule(final CabRequest request) {
        this.source = request.getSource();
        this.destination = request.getDestination();
        this.numOfSeats = request.getNumOfSeats();
    }

    @Override
    public List<Cab> apply(final List<Cab> cabs) {
        return Rule.matchRoutes(source, destination)
                .combine(Rule.matchType(Cab.Type.BASIC))
                .combine(Rule.matchSeats(numOfSeats))
                .apply(cabs);
    }
}
