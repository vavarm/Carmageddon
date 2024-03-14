package org.acme.model;

import org.acme.common.Coord2D;

public abstract class Cell {
    protected Coord2D<Integer, Integer> position;

    public Cell(Coord2D<Integer, Integer> position) {
        assert position != null;
        this.position = position;
    }

    public Coord2D<Integer, Integer> getPosition() {
        return position;
    }
}
