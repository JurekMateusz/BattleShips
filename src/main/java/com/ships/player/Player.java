package com.ships.player;

import com.ships.ship.Ship;

import java.awt.*;
import java.util.ArrayList;

public interface Player {
    ArrayList<Ship> getShips();

    void putShips();

    void drawBoards();

    Point selectPointToShoot();

    boolean shootAt(Point point, Player otherPlayer);

    boolean loose();

    void wins();
}
