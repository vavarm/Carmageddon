package org.acme.svc.impl;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.common.Coord2D;
import org.acme.dto.CreateGameDTO;
import org.acme.model.Game;
import org.acme.model.Garage;
import org.acme.model.GasStation;
import org.acme.svc.GameService;

import java.util.ArrayList;

@ApplicationScoped
public class GameServiceImpl implements GameService {

    public boolean createGame(CreateGameDTO createGameDTO) {
        Integer nbGarages = createGameDTO.getNbGarages();
        Integer nbGasStations = createGameDTO.getNbGasStations();
        ArrayList<Garage> garages = new ArrayList<>();
        ArrayList<GasStation> gasStations = new ArrayList<>();

        ArrayList<Coord2D<Integer, Integer>> coordNotEmpty = new ArrayList<>();

        for (int i = 0; i < nbGarages-1; i++) {
            int x = (int) (Math.random() * createGameDTO.getSize().getx()-1);
            int y = (int) (Math.random() * createGameDTO.getSize().gety()-1);
            Coord2D<Integer, Integer> coord = new Coord2D<>(x, y);
            if (coordNotEmpty.contains(coord)) {
                i--;
                continue;
            }
            coordNotEmpty.add(coord);
            garages.add(new Garage(coord));
        }

        for (int i = 0; i < nbGasStations-1; i++) {
            int x = (int) (Math.random() * createGameDTO.getSize().getx()-1);
            int y = (int) (Math.random() * createGameDTO.getSize().gety()-1);
            Coord2D<Integer, Integer> coord = new Coord2D<>(x, y);
            if (coordNotEmpty.contains(coord)) {
                i--;
                continue;
            }
            coordNotEmpty.add(coord);
            gasStations.add(new GasStation(coord));
        }

        Game.createNewGame(createGameDTO.getSize(), garages, gasStations);
        return Game.getInstance() != null;
    }

    public String getGameState() {
        return Game.getInstance().toString();
    }
}
