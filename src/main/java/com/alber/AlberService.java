package com.alber;

import com.alber.model.Cab;
import com.alber.model.CabRequest;
import com.alber.model.rules.BasicRule;
import com.alber.model.rules.PoolRule;
import com.alber.model.rules.PremiumRule;
import com.alber.model.rules.Rule;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

public class AlberService {
    private final Supplier<List<Cab>> cabsSuppliers;
    private final Map<Cab.Type, Function<CabRequest, Rule>> rules = Map.of(
            Cab.Type.BASIC, BasicRule::new,
            Cab.Type.PREMIUM, PremiumRule::new,
            Cab.Type.POOL, PoolRule::new
    );

    public AlberService(Supplier<List<Cab>> cabsSuppliers) {
        this.cabsSuppliers = cabsSuppliers;
    }

    public List<Cab> getCabs(CabRequest request) {
        final List<Cab> cabs = cabsSuppliers.get();
        return rules.get(request.getType())
                .apply(request)
                .apply(cabs);
    }
}
