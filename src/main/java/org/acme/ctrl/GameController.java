package org.acme.ctrl;

import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.dto.CreateGameDTO;
import org.acme.svc.GameService;
import org.acme.svc.impl.GameServiceImpl;

@Path("/game")
public class GameController {

    private final GameService gameService;

    @Inject
    public GameController() {
        this.gameService = new GameServiceImpl();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createGame(CreateGameDTO createGameDTO){
        // TODO : envoyer avec MQTT
        Log.info("Creating game with size: x=" + createGameDTO.getSize().getx() + " y=" + createGameDTO.getSize().gety());
        if ( gameService.createGame(createGameDTO) ) {
            return Response.status(201).entity(createGameDTO).build();
        }
        return Response.status(400).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGameState(){
        // TODO : envoyer avec MQTT
        Log.info("Getting game state");
        String gameState = gameService.getGameState();
        if (gameState != null) {
            return Response.status(200).entity(gameState).build();
        }
        return Response.status(500).build();
    }
}
