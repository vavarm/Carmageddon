package org.acme.ctrl;

import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.common.MoveState;
import org.acme.dto.CreateVehicleDTO;
import org.acme.dto.MoveVehicleDTO;
import org.acme.svc.MQTTService;
import org.acme.svc.VehicleService;
import org.acme.svc.impl.MQTTServiceImpl;
import org.acme.svc.impl.VehicleServiceImpl;

@Path("/vehicle")
public class VehicleController {

    private final VehicleService vehicleService;

    private final MQTTService mqttService;

    @Inject
    public VehicleController() {
        vehicleService = new VehicleServiceImpl();
        mqttService = new MQTTServiceImpl();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createVehicle(CreateVehicleDTO dto){
        Log.info("Creating vehicle with pseudo: " + dto.getPseudo());
        if ( vehicleService.createVehicle(dto.getPseudo()) ) {
            mqttService.sendGameState();
            return Response.status(201).entity(dto).build();
        }
        return Response.status(400).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response moveVehicle(MoveVehicleDTO moveVehicleDTO){
        Log.info("Moving vehicle with pseudo: " + moveVehicleDTO.getPseudo() + " in direction: " + moveVehicleDTO.getDirection());
        MoveState moveState = vehicleService.moveVehicle(moveVehicleDTO);
        Log.info("Move state: " + moveState);
        if (moveState == MoveState.SUCCESS) {
            mqttService.sendGameState();
            return Response.status(200).entity(moveState).build();
        }
        return Response.status(400).build();
    }
}
