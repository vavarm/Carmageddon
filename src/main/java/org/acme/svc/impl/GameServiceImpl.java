package org.acme.svc.impl;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.common.Coord2D;
import org.acme.dto.CreateGameDTO;
import org.acme.model.Game;
import org.acme.model.Garage;
import org.acme.model.GasStation;
import org.acme.svc.GameService;

import java.security.SecureRandom;
import java.util.ArrayList;

@ApplicationScoped
public class GameServiceImpl implements GameService {

    private static SecureRandom random = new SecureRandom();

    public boolean createGame(CreateGameDTO createGameDTO) {
        Integer nbGarages = createGameDTO.getNbGarages();
        Integer nbGasStations = createGameDTO.getNbGasStations();
        ArrayList<Garage> garages = new ArrayList<>();
        ArrayList<GasStation> gasStations = new ArrayList<>();

        ArrayList<Coord2D<Integer, Integer>> coordNotEmpty = new ArrayList<>();
        int i = 0;
        while (i < nbGarages) {
            int xi = random.nextInt(createGameDTO.getSize().getx());
            int yi = random.nextInt(createGameDTO.getSize().gety());
            Coord2D<Integer, Integer> coordi = new Coord2D<>(xi, yi);
            if (coordNotEmpty.contains(coordi)) {
                continue;
            }
            coordNotEmpty.add(coordi);
            garages.add(new Garage(coordi));
            i++;
        }

        int j = 0;
        while (j < nbGasStations) {
            int xj = random.nextInt(createGameDTO.getSize().getx());
            int yj = random.nextInt(createGameDTO.getSize().gety());
            Coord2D<Integer, Integer> coordj = new Coord2D<>(xj, yj);
            if (coordNotEmpty.contains(coordj)) {
                continue;
            }
            coordNotEmpty.add(coordj);
            gasStations.add(new GasStation(coordj));
            j++;
        }

        Game.createNewGame(createGameDTO.getSize(), garages, gasStations);
        return Game.getInstance() != null;
    }

    public String getGameState() {
        return Game.getInstance().toString();
    }
}
