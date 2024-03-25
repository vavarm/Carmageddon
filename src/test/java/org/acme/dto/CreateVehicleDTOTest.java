package org.acme.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreateVehicleDTOTest {

    CreateVehicleDTO vehicleDTO;

    @BeforeEach
    void setUp() {
        vehicleDTO = new CreateVehicleDTO("toto");
    }

    @Test
    void createVehicleDTOWithEmptyParameter(){
        vehicleDTO = new CreateVehicleDTO();
        assertEquals("", vehicleDTO.getPseudo());
    }

    @Test
    void setPseudo() {
        vehicleDTO.setPseudo("tata");
        assertEquals("tata", vehicleDTO.getPseudo());
    }
}