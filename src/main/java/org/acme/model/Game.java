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

    private Game(Coord2D<Integer, Integer> size, List<Garage> garages, List<GasStation> gasStations) {
        assert garages != null;
        assert gasStations != null;
        assert size != null;
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
        assert vehicle != null;
        vehicles.add(vehicle);
    }

    public void removeVehicle(Vehicle vehicle) {
        assert vehicle != null;
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
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("size{");
        sb.append("x=").append(size.getx());
        sb.append(", y=").append(size.gety());
        sb.append("}, garages=");
        for (Garage garage : garages) {
            sb.append(garage.toString());
            sb.append(", ");
        }
        sb.append("}, gasStations=");
        for (GasStation gasStation : gasStations) {
            sb.append(gasStation.toString());
            sb.append(", ");
        }
        sb.append("}, vehicles=");
        for (Vehicle vehicle : vehicles) {
            sb.append(vehicle.toString());
            sb.append(", ");
        }
        sb.append("}");
        sb.append("}");
        return sb.toString();
    }
}
