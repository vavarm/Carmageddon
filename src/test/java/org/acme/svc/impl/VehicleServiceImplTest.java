package org.acme.svc.impl;

import org.acme.common.Coord2D;
import org.acme.common.Direction;
import org.acme.dto.CreateGameDTO;
import org.acme.dto.MoveVehicleDTO;
import org.acme.model.Game;
import org.acme.model.Garage;
import org.acme.model.GasStation;
import org.acme.model.Vehicle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VehicleServiceImplTest {

    @Test
    void createVehicle_NoGarage() {
        VehicleServiceImpl vehicleServiceImpl = new VehicleServiceImpl();
        GameServiceImpl gameServiceImpl = new GameServiceImpl();
        gameServiceImpl.createGame(new CreateGameDTO(new Coord2D<>(10, 10), 0, 0));

        String pseudo = "test";
        try {
            vehicleServiceImpl.createVehicle(pseudo);
        } catch (Exception e) {
            assertEquals("No garage available", e.getMessage());
        }
    }

    @Test
    void moveVehicle_UP() {
        VehicleServiceImpl vehicleServiceImpl = new VehicleServiceImpl();
        GameServiceImpl gameServiceImpl = new GameServiceImpl();
        gameServiceImpl.createGame(new CreateGameDTO(new Coord2D<>(10, 10), 2, 2));

        String pseudo = "test";
        try {
            vehicleServiceImpl.createVehicle(pseudo);
        } catch (Exception e) {
            fail("Error creating vehicle", e);
        }

        MoveVehicleDTO moveVehicleDTO = new MoveVehicleDTO(pseudo, Direction.UP);
        try {
            vehicleServiceImpl.moveVehicle(moveVehicleDTO);
        } catch (Exception e) {
            fail("Error moving vehicle", e);
        }
    }

    @Test
    void moveVehicle_DOWN() {
        VehicleServiceImpl vehicleServiceImpl = new VehicleServiceImpl();
        GameServiceImpl gameServiceImpl = new GameServiceImpl();
        gameServiceImpl.createGame(new CreateGameDTO(new Coord2D<>(10, 10), 2, 2));

        String pseudo = "test";
        try {
            vehicleServiceImpl.createVehicle(pseudo);
        } catch (Exception e) {
            fail("Error creating vehicle", e);
        }

        MoveVehicleDTO moveVehicleDTO = new MoveVehicleDTO(pseudo, Direction.DOWN);
        try {
            vehicleServiceImpl.moveVehicle(moveVehicleDTO);
            vehicleServiceImpl.moveVehicle(moveVehicleDTO);
        } catch (Exception e) {
            fail("Error moving vehicle", e);
        }
    }

    @Test
    void moveVehicle_LEFT() {
        VehicleServiceImpl vehicleServiceImpl = new VehicleServiceImpl();
        GameServiceImpl gameServiceImpl = new GameServiceImpl();
        gameServiceImpl.createGame(new CreateGameDTO(new Coord2D<>(10, 10), 2, 2));

        String pseudo = "test";
        try {
            vehicleServiceImpl.createVehicle(pseudo);
        } catch (Exception e) {
            fail("Error creating vehicle", e);
        }

        MoveVehicleDTO moveVehicleDTO = new MoveVehicleDTO(pseudo, Direction.LEFT);
        try {
            vehicleServiceImpl.moveVehicle(moveVehicleDTO);
            vehicleServiceImpl.moveVehicle(moveVehicleDTO);
        } catch (Exception e) {
            fail("Error moving vehicle", e);
        }
    }

    @Test
    void moveVehicle_RIGHT() {
        VehicleServiceImpl vehicleServiceImpl = new VehicleServiceImpl();
        GameServiceImpl gameServiceImpl = new GameServiceImpl();
        gameServiceImpl.createGame(new CreateGameDTO(new Coord2D<>(10, 10), 2, 2));

        String pseudo = "test";
        try {
            vehicleServiceImpl.createVehicle(pseudo);
        } catch (Exception e) {
            fail("Error creating vehicle", e);
        }

        MoveVehicleDTO moveVehicleDTO = new MoveVehicleDTO(pseudo, Direction.RIGHT);
        try {
            vehicleServiceImpl.moveVehicle(moveVehicleDTO);
            vehicleServiceImpl.moveVehicle(moveVehicleDTO);
        } catch (Exception e) {
            fail("Error moving vehicle", e);
        }
    }

    @Test
    void createVehicle_PseudoNull() {
        VehicleServiceImpl vehicleServiceImpl = new VehicleServiceImpl();
        GameServiceImpl gameServiceImpl = new GameServiceImpl();
        gameServiceImpl.createGame(new CreateGameDTO(new Coord2D<>(10, 10), 1, 1));

        String pseudo = null;
        try {
            vehicleServiceImpl.createVehicle(pseudo);
        } catch (Exception e) {
            assertEquals("Pseudo cannot be null", e.getMessage());
        }
    }

    @Test
    void createVehicle_PseudoAlreadyExists() {
        VehicleServiceImpl vehicleServiceImpl = new VehicleServiceImpl();
        GameServiceImpl gameServiceImpl = new GameServiceImpl();
        gameServiceImpl.createGame(new CreateGameDTO(new Coord2D<>(10, 10), 1, 1));

        String pseudo = "test";
        try {
            vehicleServiceImpl.createVehicle(pseudo);
        } catch (Exception e) {
            fail("Error creating vehicle", e);
        }

        try {
            vehicleServiceImpl.createVehicle(pseudo);
        } catch (Exception e) {
            assertEquals("Pseudo already exists", e.getMessage());
        }
    }

    @Test
    void moveVehicle_isOutOfGrid(){
        VehicleServiceImpl vehicleServiceImpl = new VehicleServiceImpl();
        GameServiceImpl gameServiceImpl = new GameServiceImpl();
        gameServiceImpl.createGame(new CreateGameDTO(new Coord2D<>(1, 1), 1, 0));

        String pseudo = "test";
        try {
            vehicleServiceImpl.createVehicle(pseudo);
        } catch (Exception e) {
            fail("Error creating vehicle", e);
        }

        MoveVehicleDTO moveVehicleDTO = new MoveVehicleDTO(pseudo, Direction.UP);
        try {
            vehicleServiceImpl.moveVehicle(moveVehicleDTO);
            vehicleServiceImpl.moveVehicle(moveVehicleDTO);
        } catch (Exception e) {
            assertEquals("Vehicle is out of grid", e.getMessage());
        }
    }

    @Test
    void moveVehicle_isOnGarage(){
        VehicleServiceImpl vehicleServiceImpl = new VehicleServiceImpl();
        GameServiceImpl gameServiceImpl = new GameServiceImpl();
        gameServiceImpl.createGame(new CreateGameDTO(new Coord2D<>(2, 2), 1, 0));

        Garage garage = Game.getInstance().getGarages().getFirst();
        garage.setPosition(new Coord2D<>(1, 1));

        String pseudo = "test";
        try {
            vehicleServiceImpl.createVehicle(pseudo);
        } catch (Exception e) {
            fail("Error creating vehicle", e);
        }

        MoveVehicleDTO moveVehicleDTO_UP = new MoveVehicleDTO(pseudo, Direction.UP);
        MoveVehicleDTO moveVehicleDTO_DOWN = new MoveVehicleDTO(pseudo, Direction.DOWN);

        vehicleServiceImpl.moveVehicle(moveVehicleDTO_UP);
        vehicleServiceImpl.moveVehicle(moveVehicleDTO_DOWN);
        vehicleServiceImpl.moveVehicle(moveVehicleDTO_DOWN);
    }

    @Test
    void moveVehicle_isOnGasStation(){
        VehicleServiceImpl vehicleServiceImpl = new VehicleServiceImpl();
        GameServiceImpl gameServiceImpl = new GameServiceImpl();
        gameServiceImpl.createGame(new CreateGameDTO(new Coord2D<>(2, 2), 1, 1));

        Garage garage = Game.getInstance().getGarages().getFirst();
        garage.setPosition(new Coord2D<>(1, 1));

        GasStation gasStation = Game.getInstance().getGasStations().getFirst();
        gasStation.setPosition(new Coord2D<>(1, 0));

        String pseudo = "test";
        try {
            vehicleServiceImpl.createVehicle(pseudo);
        } catch (Exception e) {
            fail("Error creating vehicle", e);
        }

        MoveVehicleDTO moveVehicleDTO_UP = new MoveVehicleDTO(pseudo, Direction.UP);
        vehicleServiceImpl.moveVehicle(moveVehicleDTO_UP);
    }

}