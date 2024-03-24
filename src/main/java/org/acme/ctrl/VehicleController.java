package org.acme.ctrl;

import io.quarkus.logging.Log;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.common.MoveState;
import org.acme.dto.MoveVehicleDTO;
import org.acme.svc.VehicleService;

@Path("/vehicle")
public class VehicleController {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createVehicle(String pseudo){
        Log.info("Creating vehicle with pseudo: " + pseudo);
        if ( VehicleService.createVehicle(pseudo) ) {
            return Response.status(201).entity(pseudo).build();
        }
        return Response.status(400).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response moveVehicle(MoveVehicleDTO moveVehicleDTO){
        Log.info("Moving vehicle with pseudo: " + moveVehicleDTO.getPseudo() + " in direction: " + moveVehicleDTO.getDirection());
        MoveState moveState = VehicleService.moveVehicle(moveVehicleDTO.getPseudo(), moveVehicleDTO.getDirection());
        Log.info("Move state: " + moveState);
        if (moveState == MoveState.SUCCESS) {
            return Response.status(200).entity(moveState).build();
        }
        return Response.status(400).build();
    }
}
