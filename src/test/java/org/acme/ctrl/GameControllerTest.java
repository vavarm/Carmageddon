package org.acme.ctrl;

import jakarta.ws.rs.core.Response;
import org.acme.common.Coord2D;
import org.acme.dto.CreateGameDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameControllerTest {

    @Test
    void testCreateGame() {
        GameController gameController = new GameController();
        Coord2D<Integer, Integer> coord2D = new Coord2D<>(10, 10);
        CreateGameDTO createGameDTO = new CreateGameDTO(coord2D, 1, 1);

        try (Response response = gameController.createGame(createGameDTO)) {
            assertEquals(201, response.getStatus());

            CreateGameDTO responsecreateGameDTO = (CreateGameDTO) response.getEntity();
            assertEquals(coord2D, responsecreateGameDTO.getSize());
            assertEquals(1, responsecreateGameDTO.getNbGarages());
            assertEquals(1, responsecreateGameDTO.getNbGasStations());
        } catch (Exception e) {
            fail("Error creating game", e);
        }
    }

    @Test
    void testGetGameState() {
        GameController gameController = new GameController();

        try (Response response = gameController.getGameState()) {
            assertEquals(200, response.getStatus());
        } catch (Exception e) {
            fail("Error getting game state", e);
        }
    }
}