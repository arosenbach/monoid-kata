package com.alber.model;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Rule")
public
class RuleTest {

    @Nested
    @DisplayName("is a monoid with Rule::combine")
    class Combine {

        @Test
        @DisplayName("right identity: x • empty ≡ x")
        void rightIdentity() {
            assertEquals("Right identity", "FIX ME!");
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
