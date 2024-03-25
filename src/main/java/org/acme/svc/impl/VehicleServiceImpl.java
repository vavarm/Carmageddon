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
        if(pseudo == null) {
            Log.error("Pseudo cannot be null");
            return false;
        }
        if (Game.getInstance().getVehicles().stream().anyMatch(v -> v.getPseudo().equals(pseudo))) {
            Log.error("Pseudo already exists");
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
        Coord2D<Integer, Integer> positionBeforeMove = vehicle.getPosition();

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

            if (isOutOfGrid(vehicle)) {
                vehicle.setPosition(positionBeforeMove);
                return MoveState.FAILURE;
            }

            if (isOnGarage(vehicle)) {
                Log.info("Is on Garage");
                return MoveState.SUCCESS;
            }
            if (isOnGasStation(vehicle)) {
                Log.info("Time to refuel");
                vehicle.refuel();
                return MoveState.SUCCESS;
            }

            Vehicle otherVehicle = isOnOtherVehicle(vehicle);
            if (otherVehicle != null) Game.getInstance().removeVehicle(otherVehicle);
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
        Log.info("GasStation :" + gasStation);
        return gasStation != null;
    }

    private boolean isOnGarage(Vehicle vehicle) {
        Garage garage = Game.getInstance().getGarages().stream()
                .filter(g -> g.getPosition().equals(vehicle.getPosition()))
                .findFirst()
                .orElse(null);
        Log.info("Garage :" + garage);
        return garage != null;
    }

    private Vehicle isOnOtherVehicle(Vehicle vehicle) {
        Vehicle otherVehicle = Game.getInstance().getVehicles().stream()
                .filter(v -> v.getPosition().equals(vehicle.getPosition()) && !v.getPseudo().equals(vehicle.getPseudo()))
                .findFirst()
                .orElse(null);
        Log.info("Other Vehicle :" + otherVehicle);
        if (otherVehicle != vehicle) {
            return otherVehicle;
        }
        return null;
    }

    private boolean isOutOfGrid(Vehicle vehicle) {
        return vehicle.getPosition().getx() < 0 || vehicle.getPosition().getx() > Game.getInstance().getSize().getx()-1
                || vehicle.getPosition().gety() < 0 || vehicle.getPosition().gety() > Game.getInstance().getSize().gety()-1;
    }
}
