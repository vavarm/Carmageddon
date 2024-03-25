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
        assertEquals(Game.getInstance(), game);
    }

    @Test
    void createGameInstanceWithNullParameters() {
        List<Garage> garages = new ArrayList<>();
        List<GasStation> gasStations = new ArrayList<>();
        Coord2D<Integer, Integer> size = new Coord2D<>(10, 10);
        assertThrows(IllegalArgumentException.class, () -> Game.createNewGame(null, garages, gasStations));
        assertThrows(IllegalArgumentException.class, () -> Game.createNewGame(size, null, gasStations));
        assertThrows(IllegalArgumentException.class, () -> Game.createNewGame(size, garages, null));
    }

    @Test
    void addVehicle() {
        game.addVehicle(new Vehicle("Vehicle1", Direction.UP, new Coord2D<Integer, Integer>(1, 1)));
        assertEquals(1, game.getVehicles().size());
    }

    @Test
    void addVehicleNull() {
        assertThrows(IllegalArgumentException.class, () -> game.addVehicle(null));
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
    void removeVehicleNull() {
        assertThrows(IllegalArgumentException.class, () -> game.removeVehicle(null));
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
    void testEquals() {
        Coord2D<Integer, Integer> size = game.getSize();
        List<Garage> garages = game.getGarages();
        List<GasStation> gasStations = game.getGasStations();
        Game game2 = new Game(size, garages, gasStations);

        assertEquals(game, game2);
        assertEquals(game, game);
        assertEquals(game.getGarages(), game2.getGarages());
        assertEquals(game.getGasStations(), game2.getGasStations());
        assertEquals(game.getVehicles(), game2.getVehicles());
        assertEquals(game.getSize(), game2.getSize());
        assertNotEquals("game", game);
    }

    @Test
    void hashCodeTest() {
        Coord2D<Integer, Integer> size = game.getSize();
        List<Garage> garages = game.getGarages();
        List<GasStation> gasStations = game.getGasStations();
        Game game2 = new Game(size, garages, gasStations);

        assertEquals(game.hashCode(), game2.hashCode());
    }

    @Test
    void testToString() {
        assertEquals("{\"size\":{\"x\":10, \"y\":10}, \"garages\":[{\"position\": {\"x\": 1, \"y\": 1}}], \"gasStations\":[{\"position\": {\"x\": 2, \"y\": 2}}], \"vehicles\":[]}", game.toString());
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