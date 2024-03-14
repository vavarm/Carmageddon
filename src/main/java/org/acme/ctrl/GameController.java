package org.acme.ctrl;

import io.quarkus.logging.Log;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.common.Coord2D;

@Path("/game")
public class GameController {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createGame(Coord2D<Integer, Integer> coord2D){
        Log.info("Creating game with coord2D: " + coord2D);
        return Response.status(200).entity(coord2D).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getGameState(){
        Log.info("Getting game state");
        return Response.status(200).build();
    }
}