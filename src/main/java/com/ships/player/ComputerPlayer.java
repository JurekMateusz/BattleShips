package com.ships.player;

import com.ships.ship.PointsGenerator;
import com.ships.ship.ShipsGenerator;

import java.awt.*;

public class ComputerPlayer extends AbstractPlayer {
    private PointsGenerator pointsGenerator = new PointsGenerator(sizeMap);

    public ComputerPlayer(int sizeMap) {
        super(sizeMap);
    }

    @Override
    public Point selectPointToShoot() {

        return null;//TODO new class PointGenerator
    }

    @Override
    public void putShips() {
        ShipsGenerator shipsGenerator = new ShipsGenerator(sizeMap);

        int lengthShip = 6;
        shipsGenerator.addShipToList(ships, lengthShip);
//Should give 2x 4ship ,2x3ship 2x 2ship
        for (int lengthShips = 2; lengthShips < 5; lengthShips++) {
            int numberOfShipGivenLength = 2;
            while (numberOfShipGivenLength-- > 0)
                shipsGenerator.addShipToList(ships, lengthShip);
        }
    }

    @Override
    public void wins() {

    }


    @Override
    public void drawBoards() {

    }


}