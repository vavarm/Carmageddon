package org.acme.svc;

import org.acme.common.Coord2D;
import org.acme.model.Game;

import java.util.ArrayList;

public class GameService {

    private GameService() {
    }

    public static boolean createGame(Coord2D<Integer, Integer> size) {
        // TODO : Send with MQTT
        Game.createNewGame(size, new ArrayList<>(), new ArrayList<>());
        return Game.getInstance() != null;
    }

    public static String getGameState() {
        // TODO : Send with MQTT
        return Game.getInstance().toString();
    }
}
