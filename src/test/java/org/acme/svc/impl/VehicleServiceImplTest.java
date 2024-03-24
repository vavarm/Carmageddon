package org.acme.svc.impl;

import org.acme.common.Coord2D;
import org.acme.common.Direction;
import org.acme.dto.CreateGameDTO;
import org.acme.dto.MoveVehicleDTO;
import org.junit.jupiter.api.BeforeEach;
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
    void moveVehicle() {
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
}