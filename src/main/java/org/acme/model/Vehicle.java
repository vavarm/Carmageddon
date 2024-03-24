package org.acme.model;

import org.acme.common.*;

public class Vehicle extends Cell {
    private String pseudo;
    private int currentFuel;
    private static final int MAX_FUEL = 100;
    private Direction orientation;

    public Vehicle(String pseudo, Direction direction, Coord2D<Integer, Integer> position) {
        super(position);
        if (pseudo == null) throw new IllegalArgumentException("Pseudo cannot be null");
        if (direction == null) throw new IllegalArgumentException("Direction cannot be null");
        this.pseudo = pseudo;
        this.currentFuel = MAX_FUEL;
        this.orientation = direction;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        if (pseudo == null) throw new IllegalArgumentException("Pseudo cannot be null");
        this.pseudo = pseudo;
    }

    public void refuel() {
        this.currentFuel = MAX_FUEL;
    }

    public int getCurrentFuel() {
        return currentFuel;
    }

    public void setCurrentFuel(int currentFuel) {
        if (currentFuel < 0 ) throw new IllegalArgumentException("Fuel cannot be negative");
        this.currentFuel = currentFuel;
    }

    public Direction getOrientation() {
        return orientation;
    }

    public void setOrientation(Direction orientation) {
        if (orientation == null) throw new IllegalArgumentException("Direction cannot be null");
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
    public int hashCode() {
        return pseudo.hashCode() + currentFuel + orientation.hashCode() + position.hashCode();
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
