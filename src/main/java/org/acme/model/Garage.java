package org.acme.model;

import org.acme.common.Coord2D;

public class Garage extends Cell{

    public Garage(Coord2D<Integer, Integer> position) {
        super(position);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Garage)) return false;
        Garage garage = (Garage) o;
        return position.equals(garage.position);
    }

    @Override
    public int hashCode() {
        return position.hashCode();
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
