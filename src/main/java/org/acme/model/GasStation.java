package org.acme.model;

import org.acme.common.Coord2D;

public class GasStation extends Cell{

    public GasStation(Coord2D<Integer, Integer> position) {
        super(position);
    }

    @Override
    public String toString() {
        return "GasStation{" +
                "position{" +
                "x=" + position.getx() +
                ", y=" + position.gety() +
                "}" +
                '}';
    }
}
