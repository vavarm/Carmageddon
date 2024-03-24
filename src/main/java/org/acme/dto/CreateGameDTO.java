package org.acme.dto;

import org.acme.common.Coord2D;

public class CreateGameDTO {

    private Coord2D<Integer, Integer> size;

    private Integer nbGarages;

    private Integer nbGasStations;

    public CreateGameDTO() {
        this.size = new Coord2D<>(10, 10);
        this.nbGarages = 1;
        this.nbGasStations = 1;
    }

    public CreateGameDTO(Coord2D<Integer, Integer> size, Integer nbGarages, Integer nbGasStations) {
        this.size = size;
        this.nbGarages = nbGarages;
        this.nbGasStations = nbGasStations;
    }


    public Coord2D<Integer, Integer> getSize() {
        return size;
    }

    public void setSize(Coord2D<Integer, Integer> size) {
        this.size = size;
    }

    public Integer getNbGarages() {
        return nbGarages;
    }

    public void setNbGarages(Integer nbGarages) {
        this.nbGarages = nbGarages;
    }

    public Integer getNbGasStations() {
        return nbGasStations;
    }

    public void setNbGasStations(Integer nbGasStations) {
        this.nbGasStations = nbGasStations;
    }
}
