package org.acme.model;

import org.acme.common.Coord2D;

public class Garage extends Cell{

    public Garage(Coord2D<Integer, Integer> position) {
        super(position);
    }

    @Override
    public String toString() {
        return "Garage{" +
                "position{" +
                "x=" + position.getx() +
                ", y=" + position.gety() +
                "}" +
                '}';
    }
}
