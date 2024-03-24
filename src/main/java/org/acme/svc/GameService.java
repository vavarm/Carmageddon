package org.acme.svc;

import org.acme.dto.CreateGameDTO;

public interface GameService {

    public boolean createGame(CreateGameDTO createGameDTO);

    public String getGameState();
}
