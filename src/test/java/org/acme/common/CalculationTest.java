package org.acme.common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculationTest {

    @Test
    void calculate() {
        assertEquals(0, Calculation.calculate("zero"));
        assertEquals(-1, Calculation.calculate("moins"));
        assertEquals(2, Calculation.calculate("method1"));
        assertEquals(1, Calculation.calculate("plus"));
        assertEquals(0, Calculation.calculate("unknown"));
    }

    @Test
    void moins() {
        assertEquals(-1, Calculation.moins());
    }

    @Test
    void method1() {
        assertEquals(2, Calculation.method1());
    }

    @Test
    void plus() {
        assertEquals(1, Calculation.plus());
    }
}