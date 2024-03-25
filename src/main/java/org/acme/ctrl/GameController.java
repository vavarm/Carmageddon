package org.acme.ctrl;

import io.quarkus.logging.Log;
import io.smallrye.reactive.messaging.providers.extension.EmitterImpl;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.dto.CreateGameDTO;
import org.acme.model.Game;
import org.acme.svc.GameService;
import org.acme.svc.MQTTService;
import org.acme.svc.impl.GameServiceImpl;
import org.acme.svc.impl.MQTTServiceImpl;

@Path("/game")
public class GameController {

    private final GameService gameService;

    private final MQTTService mqttService;

    @Inject
    public GameController() {
        this.gameService = new GameServiceImpl();
        this.mqttService = new MQTTServiceImpl();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createGame(CreateGameDTO createGameDTO){
        Log.info("Creating game with size: x=" + createGameDTO.getSize().getx() + " y=" + createGameDTO.getSize().gety());
        if ( gameService.createGame(createGameDTO) ) {
            mqttService.sendGameState();
            return Response.status(201).entity(createGameDTO).build();
        }
        return Response.status(400).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGameState(){
        Log.info("Getting game state");
        String gameState = gameService.getGameState();
        if (gameState != null) {
            mqttService.sendGameState();
            return Response.status(200).entity(gameState).build();
        }
        return Response.status(500).build();
    }
}
