package com.alber.model;


import com.alber.model.rules.Rule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Rule")
public
class RuleTest {

    public static final Location CARREFOUR_MEYLAN = new Location(45.206170715617404, 5.770099152660413);
    public static final Location MUSEE_GRENOBLE = new Location(45.198915189003976, 5.73308442665873);

    @Nested
    @DisplayName("is a monoid with Rule::combine")
    class Combine {

        @Test
        @DisplayName("right identity: x • empty ≡ x")
        void rightIdentity() {
            final Rule rule1 = cabs -> cabs.stream().filter(cab -> cab.getSeatsAvailable() == 1).collect(toList());
            final Rule rule2 = rule1.combine(Rule.identity());
            assertEquals(rule1.apply(List.of(new Cab(Cab.Type.POOL, 1, 3, CARREFOUR_MEYLAN, MUSEE_GRENOBLE))), rule2.apply(List.of(new Cab(Cab.Type.POOL, 1, 3, CARREFOUR_MEYLAN, MUSEE_GRENOBLE))));
        }

        @Test
        @DisplayName("left identity: empty • x ≡ x")
        void leftIdentity() {
            assertEquals("Left identity", "FIX ME!");
        }

        //
        @Test
        @DisplayName("associativity: (x • y) • z ≡ x • (y • z)")
        void associativity() {
            assertEquals("Associativity", "FIX ME!");
        }
    }
}
