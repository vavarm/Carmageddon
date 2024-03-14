package org.acme.model;

import org.acme.common.Coord2D;

public class GasStation extends Cell{

    public GasStation(Coord2D<Integer, Integer> position) {
        super(position);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GasStation)) return false;
        GasStation gasStation = (GasStation) o;
        return position.equals(gasStation.position);
    }

    @Override
    public String toString() {
        return "{" +
                "\"position\": {" +
                "\"x\": " + position.getx() +
                ", \"y\": " + position.gety() +
                "}" +
                '}';
    }
}
