package org.acme.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.acme.common.Coord2D;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GasStationTest {

    static GasStation gasStation;

    @BeforeAll
    static void setUp() {
        gasStation = new GasStation(new Coord2D<>(1, 2));
    }

    @Test
    void testEquals() {
        assertTrue(gasStation.equals(new GasStation(new Coord2D<>(1, 2))));
    }

    @Test
    void testToString() {
        assertEquals("{\"position\": {\"x\": 1, \"y\": 2}}", gasStation.toString());
    }

    @Test
    void testToStringValidJSON() {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            mapper.readTree(gasStation.toString());
        } catch (Exception e) {
            fail("toString() should return a valid JSON string");
        }
    }
}