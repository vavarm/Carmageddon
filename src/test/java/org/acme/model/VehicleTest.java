package org.acme.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.acme.common.Coord2D;
import org.acme.common.Direction;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VehicleTest {

    static Vehicle vehicle;

    @BeforeEach
    void setUp() {
        vehicle = new Vehicle("Vehicle1", Direction.UP, new Coord2D<Integer, Integer>(1, 1));
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
    void getCurrentFuel() {
        assertEquals(100, vehicle.getCurrentFuel());
    }

    @Test
    void setCurrentFuel() {
        vehicle.setCurrentFuel(50);
        assertEquals(50, vehicle.getCurrentFuel());
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