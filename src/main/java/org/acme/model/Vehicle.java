package org.acme.model;

import org.acme.common.*;

public class Vehicle extends Cell {
    private String pseudo;
    private int currentFuel;
    private final static int MAX_FUEL = 100;
    private Direction orientation;

    public Vehicle(String pseudo, Direction direction, Coord2D<Integer, Integer> position) {
        super(position);
        assert pseudo != null;
        assert direction != null;
        this.pseudo = pseudo;
        this.currentFuel = MAX_FUEL;
        this.orientation = direction;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        assert pseudo != null;
        this.pseudo = pseudo;
    }

    public int getCurrentFuel() {
        return currentFuel;
    }

    public void setCurrentFuel(int currentFuel) {
        assert currentFuel >= 0;
        this.currentFuel = currentFuel;
    }

    public Direction getOrientation() {
        return orientation;
    }

    public void setOrientation(Direction orientation) {
        assert orientation != null;
        this.orientation = orientation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vehicle)) return false;
        Vehicle vehicle = (Vehicle) o;
        return pseudo.equals(vehicle.pseudo) &&
                currentFuel == vehicle.currentFuel &&
                orientation == vehicle.orientation &&
                position.equals(vehicle.position);
    }

    @Override
    public String toString() {
        return "{" +
                "\"pseudo\": \"" + pseudo + '\"' +
                ", \"currentFuel\": " + currentFuel +
                ", \"orientation\": \"" + orientation + '\"' +
                ", \"position\": {" +
                "\"x\": " + position.getx() +
                ", \"y\": " + position.gety() +
                "}" +
                '}';
    }
}
