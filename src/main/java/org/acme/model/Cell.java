package org.acme.model;

import org.acme.common.Coord2D;

public abstract class Cell {
    protected Coord2D<Integer, Integer> position;

    protected Cell(Coord2D<Integer, Integer> position) {
        if (position == null) throw new IllegalArgumentException("Position cannot be null");
        this.position = position;
    }

    public Coord2D<Integer, Integer> getPosition() {
        return position;
    }
}
