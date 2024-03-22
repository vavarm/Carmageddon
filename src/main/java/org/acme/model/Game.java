package org.acme.model;

import org.acme.common.Coord2D;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {
    private List<Garage> garages;

    private List<GasStation> gasStations;

    private List<Vehicle> vehicles;

    private Coord2D<Integer, Integer> size;

    private static Game instance;

    protected Game(Coord2D<Integer, Integer> size, List<Garage> garages, List<GasStation> gasStations) {
        if (garages == null) throw new IllegalArgumentException("Garages cannot be null");
        if (gasStations == null) throw new IllegalArgumentException("Gas stations cannot be null");
        if (size == null) throw new IllegalArgumentException("Size cannot be null");
        this.garages = garages;
        this.gasStations = gasStations;
        this.vehicles = new ArrayList<>();
        this.size = size;
    }

    public static void createNewGame(Coord2D<Integer, Integer> size, List<Garage> garages, List<GasStation> gasStations) {
        instance = new Game(size, garages, gasStations);
    }

    public static Game getInstance() {
        return instance;
    }

    public void addVehicle(Vehicle vehicle) {
        if (vehicle == null) throw new IllegalArgumentException("Vehicle cannot be null");
        vehicles.add(vehicle);
    }

    public void removeVehicle(Vehicle vehicle) {
        if (vehicle == null) throw new IllegalArgumentException("Vehicle cannot be null");
        vehicles.remove(vehicle);
    }

    public List<Garage> getGarages() {
        return Collections.unmodifiableList(garages);
    }

    public List<GasStation> getGasStations() {
        return Collections.unmodifiableList(gasStations);
    }

    public List<Vehicle> getVehicles() {
        return Collections.unmodifiableList(vehicles);
    }

    public Coord2D<Integer, Integer> getSize() {
        return size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Game)) return false;
        Game game = (Game) o;
        return getGarages().equals(game.getGarages()) &&
                getGasStations().equals(game.getGasStations()) &&
                getVehicles().equals(game.getVehicles()) &&
                getSize().equals(game.getSize());
    }

    @Override
    public int hashCode() {
        return getGarages().hashCode() + getGasStations().hashCode() + getVehicles().hashCode() + getSize().hashCode();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\"Game\":{");
        sb.append("\"size\":{");
        sb.append("\"x\":").append(size.getx());
        sb.append(", ");
        sb.append("\"y\":").append(size.gety());
        sb.append("}, ");
        sb.append("\"garages\":[");
        for (Garage garage : garages) {
            sb.append(garage.toString());
            if (garages.indexOf(garage) != garages.size() - 1)
                sb.append(", ");
        }
        sb.append("], ");
        sb.append("\"gasStations\":[");
        for (GasStation gasStation : gasStations) {
            sb.append(gasStation.toString());
            if (gasStations.indexOf(gasStation) != gasStations.size() - 1)
             sb.append(", ");
        }
        sb.append("], ");
        sb.append("\"vehicles\":[");
        for (Vehicle vehicle : vehicles) {
            sb.append(vehicle.toString());
            if (vehicles.indexOf(vehicle) != vehicles.size() - 1)
                sb.append(", ");
        }
        sb.append("]");
        sb.append("}");
        return sb.toString();
    }
}
