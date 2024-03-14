package org.acme.common;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Coord2DTest {

    private static Coord2D<Integer, Integer> coord2D;

    @BeforeAll
    static void setUp() {
        coord2D = new Coord2D<Integer, Integer>(1, 2);
    }

    @Test
    void getx() {
        assertEquals(1, coord2D.getx());
    }

    @Test
    void gety() {
        assertEquals(2, coord2D.gety());
    }

    @Test
    void testHashCode() {
        assertEquals(coord2D.getx().hashCode() ^ coord2D.gety().hashCode(), coord2D.hashCode());
    }

    @Test
    void testEquals() {
        assertTrue(coord2D.equals(new Coord2D<Integer, Integer>(1, 2)));
    }
}