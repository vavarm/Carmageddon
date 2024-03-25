package org.acme.ctrl;

import jakarta.ws.rs.core.Response;
import org.acme.common.Coord2D;
import org.acme.common.Direction;
import org.acme.common.MoveState;
import org.acme.dto.CreateGameDTO;
import org.acme.dto.CreateVehicleDTO;
import org.acme.dto.MoveVehicleDTO;
import org.acme.model.Game;
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
        CreateVehicleDTO dto = new CreateVehicleDTO(pseudo);
        try (Response response = vehicleController.createVehicle(dto)) {
            assertEquals(201, response.getStatus());
            assertEquals(dto, response.getEntity());
        } catch (Exception e) {
            fail("Error creating vehicle", e);
        }
    }

    @Test
    void testMoveVehicle() {
        String pseudo = "test";
        CreateVehicleDTO dto = new CreateVehicleDTO(pseudo);
        try (Response response = vehicleController.createVehicle(dto)) {
            assertEquals(201, response.getStatus());
            assertEquals(dto, response.getEntity());
        } catch (Exception e) {
            fail("Error creating vehicle", e);
        }

        Direction direction = Direction.UP;
        MoveVehicleDTO moveVehicleDTO = new MoveVehicleDTO(pseudo, direction);

        Game.getInstance().getVehicles().getFirst().setOrientation(Direction.DOWN);

        try (Response response = vehicleController.moveVehicle(moveVehicleDTO)) {
            assertEquals(200, response.getStatus());
            assertEquals(MoveState.SUCCESS, response.getEntity());
        } catch (Exception e) {
            fail("Error moving vehicle", e);
        }
    }
}