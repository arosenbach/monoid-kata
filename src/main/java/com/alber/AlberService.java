package com.alber;

import com.alber.model.Cab;
import com.alber.model.CabRequest;
import com.alber.model.Location;

import java.util.List;
import java.util.function.Supplier;

import static java.util.stream.Collectors.toList;

public class AlberService {

    private final Supplier<List<Cab>> cabsSuppliers;

    public AlberService(Supplier<List<Cab>> cabsSuppliers) {
        this.cabsSuppliers = cabsSuppliers;
    }

    public List<Cab> getCabs(CabRequest request) {
        final List<Cab> cabs = cabsSuppliers.get();
        if (request.getType().equals(Cab.Type.POOL)) {
            final List<Cab> matchedCabs = matchRoutes(request.getSource(), request.getDestination(), cabs);
            final List<Cab> poolCabs = matchType(Cab.Type.POOL, matchedCabs);
            return matchSeats(request.getNumOfSeats(), poolCabs);
        }
        if (request.getType().equals(Cab.Type.PREMIUM)) {
            final List<Cab> matchedCabs = matchRoutes(request.getSource(), request.getDestination(), cabs);
            final List<Cab> premiumCabs = matchType(Cab.Type.PREMIUM, matchedCabs);
            final List<Cab> emptyCabs = matchEmptyCabs(premiumCabs);
            return matchSeats(request.getNumOfSeats(), emptyCabs);
        }

        final List<Cab> matchedCabs = matchRoutes(request.getSource(), request.getDestination(), cabs);
        final List<Cab> basicCabs = matchType(Cab.Type.BASIC, matchedCabs);
        return matchSeats(request.getNumOfSeats(), basicCabs);

    }

    private List<Cab> matchEmptyCabs(final List<Cab> cabs) {
        return cabs.stream().filter(Cab::isEmpty).collect(toList());
    }


    private List<Cab> matchRoutes(Location source, Location destination, List<Cab> cabs) {
        //retrieve cabs
        return cabs
                .stream()
                .filter(c -> c.getFinalDestination().equals(destination) && c.getCurrentLocation().equals(source))
                .collect(toList());
    }


    private List<Cab> matchType(Cab.Type type, List<Cab> cabs) {
        return cabs
                .stream()
                .filter(cab -> cab.getType().equals(type))
                .collect(toList());
    }

    private List<Cab> matchSeats(int numOfSeats, List<Cab> cabs) {
        return cabs
                .stream()
                .filter(cab -> cab.getSeatsAvailable() >= numOfSeats)
                .collect(toList());
    }
}
