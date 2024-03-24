package org.acme.ctrl;

import io.quarkus.logging.Log;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.common.Coord2D;
import org.acme.svc.GameService;

@Path("/game")
public class GameController {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createGame(Coord2D<Integer, Integer> coord2D){
        Log.info("Creating game with size: x=" + coord2D.getx() + " y=" + coord2D.gety());
        if ( GameService.createGame(coord2D) ) {
            return Response.status(201).entity(coord2D).build();
        }
        return Response.status(400).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGameState(){
        Log.info("Getting game state");
        String gameState = GameService.getGameState();
        if (gameState != null) {
            return Response.status(200).entity(gameState).build();
        }
        return Response.status(500).build();
    }
}
