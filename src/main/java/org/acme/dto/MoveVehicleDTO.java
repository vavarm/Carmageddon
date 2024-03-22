package org.acme.dto;

import org.acme.common.Direction;

public class MoveVehicleDTO {
    private String pseudo;

    private Direction direction;

    public MoveVehicleDTO() {
        this.pseudo = "";
        this.direction = Direction.UP;
    }

    public MoveVehicleDTO(String pseudo, Direction direction) {
        this.pseudo = pseudo;
        this.direction = direction;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
