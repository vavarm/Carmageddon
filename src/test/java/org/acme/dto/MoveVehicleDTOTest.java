package org.acme.dto;

import org.acme.common.Direction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoveVehicleDTOTest {

    MoveVehicleDTO moveVehicleDTO;

    @BeforeEach
    void setUp() {
        moveVehicleDTO = new MoveVehicleDTO("Vehicle1", Direction.UP);
    }

    @Test
    void newMoveVehicleDTO() {
        MoveVehicleDTO moveVehicleDTO2 = new MoveVehicleDTO();
        assertEquals("", moveVehicleDTO2.getPseudo());
        assertEquals(Direction.UP, moveVehicleDTO2.getDirection());
    }

    @Test
    void setPseudo() {
        moveVehicleDTO.setPseudo("Vehicle2");
        assertEquals("Vehicle2", moveVehicleDTO.getPseudo());
    }

    @Test
    void setDirection() {
        moveVehicleDTO.setDirection(Direction.DOWN);
        assertEquals(Direction.DOWN, moveVehicleDTO.getDirection());
    }
}