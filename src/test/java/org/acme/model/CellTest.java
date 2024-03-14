package org.acme.model;

import org.acme.common.Coord2D;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {

    private static Cell cell;

    @BeforeAll
    static void setUp() {
        cell = new Garage(new Coord2D<>(1, 2));
    }

    @Test
    void getPosition() {
        assertEquals(new Coord2D<>(1, 2), cell.getPosition());
    }
}