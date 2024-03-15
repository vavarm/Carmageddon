package org.acme.ctrl;

import io.quarkus.logging.Log;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.common.Direction;
import org.acme.common.MoveState;
import org.acme.dto.MoveVehicleDTO;

@Path("/vehicle")
public class VehicleController {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createVehicle(String pseudo){
        Log.info("Creating vehicle with pseudo: " + pseudo);
        return Response.status(200).entity(pseudo).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response moveVehicle(MoveVehicleDTO moveVehicleDTO){
        Log.info("Moving vehicle with pseudo: " + moveVehicleDTO.pseudo + " in direction: " + moveVehicleDTO.direction);
        return Response.status(200).entity(MoveState.SUCCESS).build();
    }
}
