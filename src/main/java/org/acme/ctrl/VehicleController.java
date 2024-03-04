package org.acme.ctrl;

import io.quarkus.logging.Log;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.common.Direction;
import org.acme.common.MoveState;

@Path("/vehicle")
public class VehicleController {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createVehicle(String pseudo){
        Log.info("Creating vehicle with pseudo: " + pseudo);
        return Response.status(200).entity(pseudo).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response moveVehicle(String pseudo, Direction direction){
        Log.info("Moving vehicle with pseudo: " + pseudo + " in direction: " + direction);
        return Response.status(200).entity(MoveState.SUCCESS).build();
    }
}
