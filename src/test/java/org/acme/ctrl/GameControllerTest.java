package org.acme.ctrl;

import jakarta.ws.rs.core.Response;
import org.acme.common.Coord2D;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameControllerTest {

    @Test
    void testCreateGame() {
        GameController gameController = new GameController();
        Coord2D<Integer, Integer> coord2D = new Coord2D<>(1, 1);

        try (Response response = gameController.createGame(coord2D)) {
            assertEquals(201, response.getStatus());

            Coord2D<?, ?> responseCoord2D = (Coord2D<?, ?>) response.getEntity();
            assertEquals(coord2D, responseCoord2D);
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