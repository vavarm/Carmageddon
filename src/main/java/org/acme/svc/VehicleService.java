package org.acme.svc;

import org.acme.common.MoveState;
import org.acme.dto.MoveVehicleDTO;

public interface VehicleService {

    boolean createVehicle(String pseudo);

    MoveState moveVehicle(MoveVehicleDTO moveVehicleDTO);
}
