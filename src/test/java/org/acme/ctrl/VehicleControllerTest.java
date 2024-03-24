package org.acme.ctrl;

import jakarta.ws.rs.core.Response;
import org.acme.common.Coord2D;
import org.acme.common.Direction;
import org.acme.common.MoveState;
import org.acme.dto.MoveVehicleDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VehicleControllerTest {

    VehicleController vehicleController;

    @BeforeEach
    void setUp() {
        GameController gameController = new GameController();
        gameController.createGame(new Coord2D<>(10, 10));

        vehicleController = new VehicleController();
        vehicleController.createVehicle("test");
    }

    @Test
    void testCreateVehicle() {
        String pseudo = "test";

        try (Response response = vehicleController.createVehicle(pseudo)) {
            assertEquals(201, response.getStatus());
            assertEquals(pseudo, response.getEntity());
        } catch (Exception e) {
            fail("Error creating vehicle", e);
        }
    }

    @Test
    void testMoveVehicle() {
        String pseudo = "test";
        Direction direction = Direction.UP;

        try (Response response = vehicleController.moveVehicle(new MoveVehicleDTO(pseudo, direction))) {
            assertEquals(200, response.getStatus());
            assertEquals(MoveState.SUCCESS, response.getEntity());
        } catch (Exception e) {
            fail("Error moving vehicle", e);
        }
    }
}