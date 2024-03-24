package org.acme.ctrl;

import jakarta.ws.rs.core.Response;
import org.acme.common.Coord2D;
import org.acme.common.Direction;
import org.acme.common.MoveState;
import org.acme.dto.CreateGameDTO;
import org.acme.dto.MoveVehicleDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VehicleControllerTest {

    VehicleController vehicleController;

    @BeforeEach
    void setUp() {
        GameController gameController = new GameController();
        gameController.createGame(new CreateGameDTO(new Coord2D<>(10, 10), 1, 1));

        vehicleController = new VehicleController();
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

        try (Response response = vehicleController.createVehicle(pseudo)) {
            assertEquals(201, response.getStatus());
            assertEquals(pseudo, response.getEntity());
        } catch (Exception e) {
            fail("Error creating vehicle", e);
        }

        Direction direction = Direction.UP;
        MoveVehicleDTO moveVehicleDTO = new MoveVehicleDTO(pseudo, direction);

        try (Response response = vehicleController.moveVehicle(moveVehicleDTO)) {
            assertEquals(200, response.getStatus());
            assertEquals(MoveState.SUCCESS, response.getEntity());
        } catch (Exception e) {
            fail("Error moving vehicle", e);
        }
    }
}