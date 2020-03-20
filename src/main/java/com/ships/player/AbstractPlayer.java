package com.ships.player;

import com.ships.board.Board;
import com.ships.ship.PointsGenerator;
import com.ships.ship.Ship;
import com.ships.ship.ShipsGenerator;


import java.awt.*;
import java.util.ArrayList;

public abstract class AbstractPlayer implements Player {
    protected String name = "You";

    protected PointsGenerator pointsGenerator;
    protected Board board;

    protected ArrayList<Ship> ships;
    protected ArrayList<Point> hitPoints;
    protected ArrayList<Point> missPoints;

    protected int sizeMap;

    protected Player opponent;

    protected boolean sunkStatus = false;
    protected boolean lastShotStatus = false;

    AbstractPlayer(int sizeMap) {
        this.sizeMap = sizeMap;
        board = new Board(sizeMap);
        ships = new ArrayList<>();
        hitPoints = new ArrayList<>();
        missPoints = new ArrayList<>();
        pointsGenerator = new PointsGenerator(sizeMap);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean didShipWasSunk() {
        return sunkStatus;
    }

    @Override
    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }

    @Override
    public ArrayList<Ship> getShips() {
        return ships;
    }

    @Override
    public ArrayList<Point> getMissPoints() {
        return missPoints;
    }

    @Override
    public void putShips() {
        ships.clear();
        ShipsGenerator shipsGenerator = new ShipsGenerator(sizeMap);
        shipsGenerator.addShipToList(ships, 5);
        shipsGenerator.addShipToList(ships, 4);
        shipsGenerator.addShipToList(ships, 3);
        shipsGenerator.addShipToList(ships, 3);
        shipsGenerator.addShipToList(ships, 2);
        shipsGenerator.addShipToList(ships, 2);
        shipsGenerator.addShipToList(ships, 2);


    }

    @Override
    public Point selectPointToShoot() {
        if (sunkStatus) {
            pointsGenerator.resetDependency();
            sunkStatus = false;
        }
        return pointsGenerator.generatePoint(lastShotStatus);
    }

    @Override
    public boolean shootAt(Point point) {
        ArrayList<Ship> ships = opponent.getShips();
        for (Ship ship : ships) {
            if (ship.getShottedStatus()) {
                continue;
            }
            ArrayList<Point> shipPoints = ship.getPoints();
            if (shipPoints.contains(point)) {
                hitPoints.add(point);
                lastShotStatus = true;
                if (hitPoints.containsAll(shipPoints)) {
                    ship.setShottedStatus(true);
                    sunkStatus = true;
                    lastShotStatus = false;
                }
                return true;
            }
        }
        lastShotStatus = false;
        missPoints.add(point);
        return false;
    }

    @Override
    public boolean loose() {
        for (Ship ship : ships) {
            if (ship.getShottedStatus() == false) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void drawBoard() {
        board.printBoard(missPoints, hitPoints);
    }

    protected boolean pointsExist(ArrayList<Point> points) {
        for (Ship v : ships) {
            ArrayList<Point> listPointsOfShip = v.getPoints();
            for (Point point : points) {
                if (listPointsOfShip.contains(point)) {
                    return true;
                }
            }
        }
        return false;
    }
}
