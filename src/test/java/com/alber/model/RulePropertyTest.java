package com.alber.model;

import com.alber.model.rules.Rule;
import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.Combinators;
import net.jqwik.api.ForAll;
import net.jqwik.api.Functions;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RulePropertyTest {


    @Property
    void right_identity(@ForAll("rule") Rule rule, @ForAll("cabs") List<Cab> cabs) {
        assertEquals(rule.apply(cabs),
                rule.combine(Rule.identity()).apply(cabs));
    }

    @Property
    void left_identity(@ForAll("rule") Rule rule, @ForAll("cabs") List<Cab> cabs) {
        assertEquals(rule.apply(cabs),
                Rule.identity().combine(rule).apply(cabs));
    }

    @Property
    void associativity(@ForAll("rule") Rule rule1,
                       @ForAll("rule") Rule rule2,
                       @ForAll("rule") Rule rule3,
                       @ForAll("cabs") List<Cab> cabs) {
        assertEquals((rule1.combine(rule2)).combine(rule3).apply(cabs),
                rule1.combine((rule2.combine(rule3))).apply(cabs));
    }

    @Property
    void bonus_reduceable(@ForAll("rule") Rule rule1,
                          @ForAll("rule") Rule rule2,
                          @ForAll("rule") Rule rule3,
                          @ForAll("cabs") List<Cab> cabs) {
        assertEquals("Reduce-able", "FIX ME!");
    }


    @Provide
    Arbitrary<Rule> rule() {
        return Functions.function(Rule.class).returning(cabs());
    }

    @Provide
    Arbitrary<List<Cab>> cabs() {
        return Combinators.combine(
                        Arbitraries.of(Cab.Type.class),
                        Arbitraries.integers().between(0, 10),
                        Arbitraries.integers().between(0, 10),
                        arbitraryLocation(),
                        arbitraryLocation()
                ).as(Cab::new)
                .list();
    }

    @Provide
    Arbitrary<Location> arbitraryLocation() {
        return Combinators.combine(Arbitraries.doubles(), Arbitraries.doubles()).as(Location::new);
    }
}