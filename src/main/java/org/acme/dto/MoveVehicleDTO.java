package org.acme.dto;

import org.acme.common.Direction;

public class MoveVehicleDTO {
    public String pseudo;

    public Direction direction;

    public MoveVehicleDTO() {
    }

    public MoveVehicleDTO(String pseudo, Direction direction) {
        this.pseudo = pseudo;
        this.direction = direction;
    }
}
