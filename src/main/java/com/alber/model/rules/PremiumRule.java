package com.alber.model.rules;

import com.alber.model.Cab;
import com.alber.model.CabRequest;
import com.alber.model.Location;

import java.util.List;

import static java.util.stream.Collectors.toList;


public class PremiumRule implements Rule {
    private final Location source;
    private final Location destination;
    private final int numOfSeats;

    public PremiumRule(final CabRequest request) {
        this.source = request.getSource();
        this.destination = request.getDestination();
        this.numOfSeats = request.getNumOfSeats();
    }

    @Override
    public List<Cab> apply(final List<Cab> cabs) {
        return Rule.matchRoutes(source, destination)
                .combine(Rule.matchType(Cab.Type.PREMIUM))
                .combine(matchEmptyCabs())
                .combine(Rule.matchSeats(numOfSeats))
                .apply(cabs);
    }

    private Rule matchEmptyCabs() {
        return cabs -> cabs.stream()
                .filter(Cab::isEmpty)
                .collect(toList());
    }

}
