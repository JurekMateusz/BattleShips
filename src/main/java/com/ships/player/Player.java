package com.ships.player;

import com.ships.ship.Ship;

import java.awt.*;
import java.util.ArrayList;
//TODO sets/gets =>Lombok
public interface Player {
    String getName();

    ArrayList<Ship> getShips();

    void setName(String name);

    boolean didShipWasSunk();

    void setOpponent(Player opponent);

    ArrayList<Point> getMissPoints();

    void putShips();

    void drawBoard();

    Point selectPointToShoot();

    boolean shootAt(Point point);

    boolean loose();

    void wins();
}
