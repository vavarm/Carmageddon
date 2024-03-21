package org.acme.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.acme.common.Coord2D;
import org.acme.common.Direction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.lang.Integer.MIN_VALUE;
import static org.junit.jupiter.api.Assertions.*;

class VehicleTest {

    static Vehicle vehicle;

    @BeforeEach
    void setUp() {
        vehicle = new Vehicle("Vehicle1", Direction.UP, new Coord2D<Integer, Integer>(1, 1));
    }

    @Test
    void testCreateVehicleWithNullParameter() {
        Coord2D<Integer, Integer> position = new Coord2D<>(1, 1);
        assertThrows(IllegalArgumentException.class, () -> new Vehicle(null, Direction.UP, position));
        assertThrows(IllegalArgumentException.class, () -> new Vehicle("Vehicle1", null, position));
    }

    @Test
    void getPseudo() {
        assertEquals("Vehicle1", vehicle.getPseudo());
    }

    @Test
    void setPseudo() {
        vehicle.setPseudo("Vehicle2");
        assertEquals("Vehicle2", vehicle.getPseudo());
    }

    @Test
    void setPseudoNull() {
        assertThrows(IllegalArgumentException.class, () -> vehicle.setPseudo(null));
    }

    @Test
    void getCurrentFuel() {
        assertEquals(100, vehicle.getCurrentFuel());
    }

    @Test
    void setCurrentFuel() {
        vehicle.setCurrentFuel(50);
        assertEquals(50, vehicle.getCurrentFuel());
    }

    @Test
    void setCurrentFuelNegativeValue() {
        assertThrows(IllegalArgumentException.class, () -> vehicle.setCurrentFuel(-1));
        assertThrows(IllegalArgumentException.class, () -> vehicle.setCurrentFuel(MIN_VALUE));
    }

    @Test
    void getOrientation() {
        assertEquals(Direction.UP, vehicle.getOrientation());
    }

    @Test
    void setOrientation() {
        vehicle.setOrientation(Direction.DOWN);
        assertEquals(Direction.DOWN, vehicle.getOrientation());
    }

    @Test
    void setOrientationNull() {
        assertThrows(IllegalArgumentException.class, () -> vehicle.setOrientation(null));
    }

    @Test
    void equals() {
        Vehicle vehicle2 = new Vehicle("Vehicle1", Direction.UP, new Coord2D<Integer, Integer>(1, 1));
        assertEquals(vehicle, vehicle2);
        assertEquals(vehicle.getPseudo(), vehicle2.getPseudo());
        assertEquals(vehicle.getPosition(), vehicle2.getPosition());
        assertNotEquals("vehicle", vehicle);

    }

    @Test
    void hashCodeTest() {
        Vehicle vehicle2 = new Vehicle("Vehicle1", Direction.UP, new Coord2D<Integer, Integer>(1, 1));
        assertEquals(vehicle.hashCode(), vehicle2.hashCode());
    }

    @Test
    void testToString() {
        assertEquals("{\"pseudo\": \"Vehicle1\", \"currentFuel\": 100, \"orientation\": \"UP\", \"position\": {\"x\": 1, \"y\": 1}}", vehicle.toString());
    }

    @Test
    void testToStringValidJSON() {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            mapper.readTree(vehicle.toString());
        } catch (Exception e) {
            fail("toString() should return a valid JSON string");
        }
    }
}