package org.acme.svc;

import org.acme.common.Coord2D;
import org.acme.common.Direction;
import org.acme.common.MoveState;
import org.acme.model.Game;
import org.acme.model.Vehicle;

public class VehicleService {

    private VehicleService() {
    }

    public static boolean createVehicle(String pseudo) {
        Vehicle vehicle = new Vehicle(pseudo, Direction.UP, new Coord2D<>(0, 0));
        Game.getInstance().addVehicle(vehicle);
        return true;
    }

    public static MoveState moveVehicle(String pseudo, Direction direction) {
        Vehicle vehicle = Game.getInstance().getVehicles().stream()
                .filter(v -> v.getPseudo().equals(pseudo))
                .findFirst()
                .orElse(null);
        if (vehicle == null) return MoveState.FAILURE;
        vehicle.setOrientation(direction);
        return MoveState.SUCCESS;
    }
}
