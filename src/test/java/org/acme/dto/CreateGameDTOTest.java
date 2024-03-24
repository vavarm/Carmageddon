package org.acme.dto;

import org.acme.common.Coord2D;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreateGameDTOTest {

    CreateGameDTO createGameDTO;

    @BeforeEach
    void setUp() {
        createGameDTO = new CreateGameDTO();
    }
    @Test
    void setSize() {
        Coord2D<Integer, Integer> coord2D = new Coord2D<>(5, 5);
        createGameDTO.setSize(coord2D);
        assertEquals(coord2D, createGameDTO.getSize());
    }

    @Test
    void setNbGarages() {
        createGameDTO.setNbGarages(2);
        assertEquals(2, createGameDTO.getNbGarages());
    }

    @Test
    void setNbGasStations() {
        createGameDTO.setNbGasStations(2);
        assertEquals(2, createGameDTO.getNbGasStations());
    }
}