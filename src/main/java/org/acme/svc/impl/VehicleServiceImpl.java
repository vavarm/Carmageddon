package org.acme.svc.impl;

import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.common.Calculation;
import org.acme.common.Coord2D;
import org.acme.common.Direction;
import org.acme.common.MoveState;
import org.acme.dto.MoveVehicleDTO;
import org.acme.model.Game;
import org.acme.model.Garage;
import org.acme.model.GasStation;
import org.acme.model.Vehicle;
import org.acme.svc.VehicleService;

import java.security.SecureRandom;
import java.util.List;

@ApplicationScoped
public class VehicleServiceImpl implements VehicleService {

    private SecureRandom random = new SecureRandom();

    public boolean createVehicle(String pseudo) {
        List<Garage> garages = Game.getInstance().getGarages();
        if (garages.isEmpty()) {
            Log.error("No garages available");
            return false;
        }
        int randomGarage = random.nextInt(garages.size());
        Coord2D<Integer, Integer> positionStart = Game.getInstance().getGarages().get(randomGarage).getPosition();
        Vehicle vehicle = new Vehicle(pseudo, Direction.UP, positionStart);
        Game.getInstance().addVehicle(vehicle);
        return true;
    }

    public MoveState moveVehicle(MoveVehicleDTO moveVehicleDTO) {
        Vehicle vehicle = Game.getInstance().getVehicles().stream()
                .filter(v -> v.getPseudo().equals(moveVehicleDTO.getPseudo()))
                .findFirst()
                .orElse(null);
        if (vehicle == null) return MoveState.FAILURE;

        if ( vehicle.getOrientation() == moveVehicleDTO.getDirection() ) {
            vehicle.setCurrentFuel(vehicle.getCurrentFuel()-1);

            switch (vehicle.getOrientation()) {
                case UP:
                    vehicle.setPosition(new Coord2D<>(vehicle.getPosition().getx(), vehicle.getPosition().gety() + Calculation.moins()));
                    break;
                case DOWN:
                    vehicle.setPosition(new Coord2D<>(vehicle.getPosition().getx(), vehicle.getPosition().gety() + Calculation.plus()));
                    break;
                case LEFT:
                    vehicle.setPosition(new Coord2D<>(vehicle.getPosition().getx() + Calculation.calculate("moins"), vehicle.getPosition().gety()));
                    break;
                case RIGHT:
                    vehicle.setPosition(new Coord2D<>(vehicle.getPosition().getx() + Calculation.calculate("plus"), vehicle.getPosition().gety()));
                    break;
            }

            if (isOnGasStation(vehicle) || isOnGarage(vehicle)) {
                return MoveState.SUCCESS;
            }
            if (!isOnGasStation(vehicle) && !isOnGarage(vehicle) && isOnOtherVehicle(vehicle)) {
                return MoveState.SUCCESS;
            }

            return MoveState.SUCCESS;
        }
        vehicle.setOrientation(moveVehicleDTO.getDirection());
        return MoveState.SUCCESS;
    }

    private boolean isOnGasStation(Vehicle vehicle) {
        GasStation gasStation = Game.getInstance().getGasStations().stream()
                .filter(g -> g.getPosition().equals(vehicle.getPosition()))
                .findFirst()
                .orElse(null);

        if (gasStation != null && vehicle.getPosition() == gasStation.getPosition()) {
            vehicle.refuel();
            return true;
        }
        return false;
    }

    private boolean isOnGarage(Vehicle vehicle) {
        Garage garage = Game.getInstance().getGarages().stream()
                .filter(g -> g.getPosition().equals(vehicle.getPosition()))
                .findFirst()
                .orElse(null);

        return garage != null && vehicle.getPosition() == garage.getPosition();
    }

    private boolean isOnOtherVehicle(Vehicle vehicle) {
        Vehicle otherVehicle = Game.getInstance().getVehicles().stream()
                .filter(v -> v.getPosition().equals(vehicle.getPosition()))
                .findFirst()
                .orElse(null);
        if (otherVehicle != null && otherVehicle != vehicle) {
            Game.getInstance().removeVehicle(otherVehicle);
            return true;
        }
        return false;
    }
}
