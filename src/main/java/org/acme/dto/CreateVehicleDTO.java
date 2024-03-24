package org.acme.dto;

import org.acme.common.Direction;

public class CreateVehicleDTO {
    private String pseudo;

    public CreateVehicleDTO() {
        this.pseudo = "";
    }

    public CreateVehicleDTO(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }
}
