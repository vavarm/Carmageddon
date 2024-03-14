package org.acme.ctrl;

import jakarta.ws.rs.core.Response;
import org.acme.common.Direction;
import org.acme.common.MoveState;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VehicleControllerTest {

    @Test
    void testCreateVehicle() {
        VehicleController vehicleController = new VehicleController();
        String pseudo = "test";

        try (Response response = vehicleController.createVehicle(pseudo)) {
            assertEquals(response.getStatus(), 200);
            assertEquals(response.getEntity(), pseudo);
        } catch (Exception e) {
            fail("Error creating vehicle", e);
        }
    }

    @Test
    void testMoveVehicle() {
        VehicleController vehicleController = new VehicleController();
        String pseudo = "test";
        Direction direction = Direction.up;

        try (Response response = vehicleController.moveVehicle(pseudo, direction)) {
            assertEquals(response.getStatus(), 200);
            assertEquals(response.getEntity(), MoveState.SUCCESS);
        } catch (Exception e) {
            fail("Error moving vehicle", e);
        }
    }
}