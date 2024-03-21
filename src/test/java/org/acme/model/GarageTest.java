package org.acme.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.acme.common.Coord2D;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GarageTest {

    static Garage garage;

    @BeforeAll
    static void setUp() {
        garage = new Garage(new Coord2D<>(1, 2));
    }

    @Test
    void testEquals() {
        assertEquals(garage, new Garage(new Coord2D<>(1, 2)));
        assertNotEquals("garage", garage);
    }

    @Test
    void hashCodeTest() {
        Garage garage2 = new Garage(new Coord2D<>(1, 2));
        assertEquals(garage.hashCode(), garage2.hashCode());
    }

    @Test
    void testToString() {
        assertEquals("{\"position\": {\"x\": 1, \"y\": 2}}", garage.toString());
    }

    @Test
    void testToStringValidJSON() {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            mapper.readTree(garage.toString());
        } catch (Exception e) {
            fail("toString() should return a valid JSON string");
        }
    }
}