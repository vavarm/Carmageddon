package org.acme.ctrl;

import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.common.MoveState;
import org.acme.dto.MoveVehicleDTO;
import org.acme.svc.VehicleService;
import org.acme.svc.impl.VehicleServiceImpl;

@Path("/vehicle")
public class VehicleController {

    private final VehicleService vehicleService;

    @Inject
    public VehicleController() {
        vehicleService = new VehicleServiceImpl();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createVehicle(String pseudo){
        // TODO : envoyer avec MQTT
        Log.info("Creating vehicle with pseudo: " + pseudo);
        if ( vehicleService.createVehicle(pseudo) ) {
            return Response.status(201).entity(pseudo).build();
        }
        return Response.status(400).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response moveVehicle(MoveVehicleDTO moveVehicleDTO){
        // TODO : envoyer avec MQTT
        Log.info("Moving vehicle with pseudo: " + moveVehicleDTO.getPseudo() + " in direction: " + moveVehicleDTO.getDirection());
        MoveState moveState = vehicleService.moveVehicle(moveVehicleDTO);
        Log.info("Move state: " + moveState);
        if (moveState == MoveState.SUCCESS) {
            return Response.status(200).entity(moveState).build();
        }
        return Response.status(400).build();
    }
}
