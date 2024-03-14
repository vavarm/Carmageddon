package org.acme.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.acme.common.Coord2D;
import org.acme.common.Direction;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    static Game game;

    @BeforeEach
    public void setUp() {
        Coord2D<Integer, Integer> size = new Coord2D<>(10, 10);
        List<Garage> garages = new ArrayList<>();
        garages.add(new Garage(new Coord2D<>(1, 1)));
        List<GasStation> gasStations = new ArrayList<>();
        gasStations.add(new GasStation(new Coord2D<>(2, 2)));
        game = new Game(size, garages, gasStations);
    }

    @Test
    void createNewGameInstance() {
        List<Garage> garages = new ArrayList<>();
        garages.add(new Garage(new Coord2D<>(1, 1)));
        List<GasStation> gasStations = new ArrayList<>();
        gasStations.add(new GasStation(new Coord2D<>(2, 2)));
        Game.createNewGame(new Coord2D<>(10, 10), garages, gasStations);
        assertTrue(Game.getInstance().equals(game));
    }

    @Test
    void addVehicle() {
        game.addVehicle(new Vehicle("Vehicle1", Direction.UP, new Coord2D<Integer, Integer>(1, 1)));
        assertEquals(1, game.getVehicles().size());
    }

    @Test
    void removeVehicle() {
        Vehicle vehicle = new Vehicle("Vehicle1", Direction.UP, new Coord2D<Integer, Integer>(1, 1));
        game.addVehicle(vehicle);
        int numVehicles = game.getVehicles().size();
        game.removeVehicle(vehicle);
        assertEquals(numVehicles - 1, game.getVehicles().size());
    }

    @Test
    void getGarages() {
        assertEquals(1, game.getGarages().size());
    }

    @Test
    void getGasStations() {
        assertEquals(1, game.getGasStations().size());
    }

    @Test
    void getVehicles() {
        assertEquals(0, game.getVehicles().size());
    }

    @Test
    void getSize() {
        assertEquals(new Coord2D<>(10, 10), game.getSize());
    }

    @Test
    void testToString() {
        assertEquals("\"Game\":{\"size\":{\"x\":10, \"y\":10}, \"garages\":[{\"position\": {\"x\": 1, \"y\": 1}}], \"gasStations\":[{\"position\": {\"x\": 2, \"y\": 2}}], \"vehicles\":[]}", game.toString());
    }

    @Test
    void testToStringValidJSON() {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            mapper.readTree(game.toString());
        } catch (Exception e) {
            fail("toString() should return a valid JSON string");
        }
    }
}