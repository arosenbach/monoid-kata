package com.alber;

import com.alber.model.Cab;
import com.alber.model.CabRequest;
import com.alber.model.Location;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AlberServiceTest {

    public static final Location CARREFOUR_MEYLAN = new Location(45.206170715617404, 5.770099152660413);
    public static final Location MUSEE_GRENOBLE = new Location(45.198915189003976, 5.73308442665873);
    public static final Location GRAND_PLACE = new Location(45.161250029710324, 5.73057967076388);
    public static final Location IKEA = new Location(45.18283207554567, 5.775386970660653);

    @Nested
    @DisplayName("POOL")
    class Pool {

        @Test
        @DisplayName("it returns a cab when routes match and enough seats are available")
        void getCabs() {
            // Arrange
            final Cab aCab = new Cab(Cab.Type.POOL, 1, 3, CARREFOUR_MEYLAN, MUSEE_GRENOBLE);
            final Supplier<List<Cab>> supplier = () -> List.of(aCab);
            final AlberService sut = new AlberService(supplier);

            // Act
            final List<Cab> cabs = sut.getCabs(new CabRequest(CARREFOUR_MEYLAN, MUSEE_GRENOBLE, Cab.Type.POOL, 1));

            // Assert
            assertEquals(List.of(aCab), cabs);
        }


        @Test
        @DisplayName("it returns empty list when no routes match")
        void getCabs_no_routes() {
            // Arrange
            final Supplier<List<Cab>> supplier = () -> List.of(new Cab(Cab.Type.POOL, 1, 3, CARREFOUR_MEYLAN, MUSEE_GRENOBLE));
            final AlberService sut = new AlberService(supplier);

            // Act
            final List<Cab> cabs = sut.getCabs(new CabRequest(GRAND_PLACE, IKEA, Cab.Type.POOL, 2));

            // Assert
            assertEquals(List.of(), cabs);
        }

        @Test
        @DisplayName("it returns empty list when not enough seats are available")
        void getCabs_not_enough_seats() {
            // Arrange
            final Cab aCab = new Cab(Cab.Type.POOL, 1, 3, CARREFOUR_MEYLAN, MUSEE_GRENOBLE);
            final Supplier<List<Cab>> supplier = () -> List.of(aCab);
            final AlberService sut = new AlberService(supplier);

            // Act
            final List<Cab> cabs = sut.getCabs(new CabRequest(CARREFOUR_MEYLAN, MUSEE_GRENOBLE, Cab.Type.POOL, 10));

            // Assert
            assertEquals(List.of(), cabs);
        }


        @Test
        @DisplayName("it returns empty list when there's no POOL cabs")
        void getCabs_no_matching_types() {
            // Arrange
            final Cab aCab = new Cab(Cab.Type.PREMIUM, 3, 3, CARREFOUR_MEYLAN, MUSEE_GRENOBLE);
            final Supplier<List<Cab>> supplier = () -> List.of(aCab);
            final AlberService sut = new AlberService(supplier);

            // Act
            final List<Cab> cabs = sut.getCabs(new CabRequest(CARREFOUR_MEYLAN, MUSEE_GRENOBLE, Cab.Type.POOL, 1));

            // Assert
            assertEquals(List.of(), cabs);
        }

    }


    @Nested
    @DisplayName("PREMIUM")
    class Premium {

        @Test
        @DisplayName("it returns a cab when routes match and the cab is empty")
        void getCabs() {
            // Arrange
            final Cab aCab = new Cab(Cab.Type.PREMIUM, 3, 3, CARREFOUR_MEYLAN, MUSEE_GRENOBLE);
            final Supplier<List<Cab>> supplier = () -> List.of(aCab);
            final AlberService sut = new AlberService(supplier);

            // Act
            final List<Cab> cabs = sut.getCabs(new CabRequest(CARREFOUR_MEYLAN, MUSEE_GRENOBLE, Cab.Type.PREMIUM, 1));

            // Assert
            assertEquals(List.of(aCab), cabs);
        }


        @Test
        @DisplayName("it returns empty list when no routes match")
        void getCabs_no_routes() {
            // Arrange
            final Supplier<List<Cab>> supplier = () -> List.of(new Cab(Cab.Type.PREMIUM, 3, 3, CARREFOUR_MEYLAN, MUSEE_GRENOBLE));
            final AlberService sut = new AlberService(supplier);

            // Act
            final List<Cab> cabs = sut.getCabs(new CabRequest(GRAND_PLACE, IKEA, Cab.Type.PREMIUM, 1));

            // Assert
            assertEquals(List.of(), cabs);
        }

        @Test
        @DisplayName("it returns empty list when not enough seats are available")
        void getCabs_not_enough_seats() {
            // Arrange
            final Cab aCab = new Cab(Cab.Type.PREMIUM, 3, 3, CARREFOUR_MEYLAN, MUSEE_GRENOBLE);
            final Supplier<List<Cab>> supplier = () -> List.of(aCab);
            final AlberService sut = new AlberService(supplier);

            // Act
            final List<Cab> cabs = sut.getCabs(new CabRequest(CARREFOUR_MEYLAN, MUSEE_GRENOBLE, Cab.Type.PREMIUM, 10));

            // Assert
            assertEquals(List.of(), cabs);
        }


        @Test
        @DisplayName("it returns empty list when no empty cabs are available")
        void getCabs_no_empty_cab() {
            // Arrange
            final Cab aCab = new Cab(Cab.Type.PREMIUM, 1, 3, CARREFOUR_MEYLAN, MUSEE_GRENOBLE);
            final Supplier<List<Cab>> supplier = () -> List.of(aCab);
            final AlberService sut = new AlberService(supplier);

            // Act
            final List<Cab> cabs = sut.getCabs(new CabRequest(CARREFOUR_MEYLAN, MUSEE_GRENOBLE, Cab.Type.PREMIUM, 1));

            // Assert
            assertEquals(List.of(), cabs);
        }


        @Test
        @DisplayName("it returns empty list when there's no PREMIUM cabs")
        void getCabs_no_matching_types() {
            // Arrange
            final Cab aCab = new Cab(Cab.Type.POOL, 3, 3, CARREFOUR_MEYLAN, MUSEE_GRENOBLE);
            final Supplier<List<Cab>> supplier = () -> List.of(aCab);
            final AlberService sut = new AlberService(supplier);

            // Act
            final List<Cab> cabs = sut.getCabs(new CabRequest(CARREFOUR_MEYLAN, MUSEE_GRENOBLE, Cab.Type.PREMIUM, 1));

            // Assert
            assertEquals(List.of(), cabs);
        }
    }


    @Nested
    @DisplayName("BASIC")
    class Basic {

        @Test
        @DisplayName("it returns a cab when routes match and enough seats are available")
        void getCabs() {
            // Arrange
            final Cab aCab = new Cab(Cab.Type.BASIC, 1, 3, CARREFOUR_MEYLAN, MUSEE_GRENOBLE);
            final Supplier<List<Cab>> supplier = () -> List.of(aCab);
            final AlberService sut = new AlberService(supplier);

            // Act
            final List<Cab> cabs = sut.getCabs(new CabRequest(CARREFOUR_MEYLAN, MUSEE_GRENOBLE, Cab.Type.BASIC, 1));

            // Assert
            assertEquals(List.of(aCab), cabs);
        }


        @Test
        @DisplayName("it returns empty list when no routes match")
        void getCabs_no_routes() {
            // Arrange
            final Supplier<List<Cab>> supplier = () -> List.of(new Cab(Cab.Type.BASIC, 1, 3, CARREFOUR_MEYLAN, MUSEE_GRENOBLE));
            final AlberService sut = new AlberService(supplier);

            // Act
            final List<Cab> cabs = sut.getCabs(new CabRequest(GRAND_PLACE, IKEA, Cab.Type.BASIC, 1));

            // Assert
            assertEquals(List.of(), cabs);
        }

        @Test
        @DisplayName("it returns empty list when not enough seats are available")
        void getCabs_not_enough_seats() {
            // Arrange
            final Cab aCab = new Cab(Cab.Type.BASIC, 1, 3, CARREFOUR_MEYLAN, MUSEE_GRENOBLE);
            final Supplier<List<Cab>> supplier = () -> List.of(aCab);
            final AlberService sut = new AlberService(supplier);

            // Act
            final List<Cab> cabs = sut.getCabs(new CabRequest(CARREFOUR_MEYLAN, MUSEE_GRENOBLE, Cab.Type.BASIC, 10));

            // Assert
            assertEquals(List.of(), cabs);
        }


        @Test
        @DisplayName("it returns empty list when there's no POOL cabs")
        void getCabs_no_matching_types() {
            // Arrange
            final Cab aCab = new Cab(Cab.Type.PREMIUM, 3, 3, CARREFOUR_MEYLAN, MUSEE_GRENOBLE);
            final Supplier<List<Cab>> supplier = () -> List.of(aCab);
            final AlberService sut = new AlberService(supplier);

            // Act
            final List<Cab> cabs = sut.getCabs(new CabRequest(CARREFOUR_MEYLAN, MUSEE_GRENOBLE, Cab.Type.BASIC, 1));

            // Assert
            assertEquals(List.of(), cabs);
        }

    }
}