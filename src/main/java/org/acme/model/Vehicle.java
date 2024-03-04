package org.acme.model;

import org.acme.common.*;

public class Vehicle extends Cell {
    private String pseudo;
    private int currentFuel;
    private final static int MAX_FUEL = 100;
    private Direction direction;

    public Vehicle(String pseudo, Direction direction, Coord2D<Integer, Integer> position) {
        super(position);
        assert pseudo != null;
        assert direction != null;
        this.pseudo = pseudo;
        this.currentFuel = MAX_FUEL;
        this.direction = direction;
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

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        assert direction != null;
        this.direction = direction;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "pseudo='" + pseudo + '\'' +
                ", currentFuel=" + currentFuel +
                ", direction=" + direction +
                ", position{" +
                "x=" + position.getx() +
                ", y=" + position.gety() +
                "}" +
                '}';
    }
}
