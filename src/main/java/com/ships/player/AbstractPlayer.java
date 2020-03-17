package com.ships.player;

import com.ships.board.Board;
import com.ships.ship.PointsGenerator;
import com.ships.ship.Ship;


import java.awt.*;
import java.util.ArrayList;

public abstract class AbstractPlayer implements Player {
    protected ArrayList<Ship> ships;
    protected ArrayList<Point> hitPoints;
    protected ArrayList<Point> missPoints;

    protected Player opponent;

    protected boolean sunkShip = false;
    protected int sizeMap;
    protected Board board;

    AbstractPlayer(int sizeMap) {
        this.sizeMap = sizeMap;
        board = new Board(sizeMap);
        ships = new ArrayList<>();
        hitPoints = new ArrayList<>();
        missPoints = new ArrayList<>();
    }

    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }

    @Override
    public ArrayList<Ship> getShips(){
        return ships;
    }
    @Override
    public boolean shootAt(Point point, Player otherPlayer) {
        ArrayList<Ship> ships = otherPlayer.getShips();
        for (Ship ship : ships) {
            ArrayList<Point> shipPoints = ship.getPoints();
            if (shipPoints.contains(point)) {
                hitPoints.add(point);
                return true;
            }
        }
        missPoints.add(point);
        return false;
    }
    @Override
    public boolean loose() {
        ArrayList<Ship> opponentShips = opponent.getShips();
        return opponentShips.containsAll(hitPoints);
    }

    public boolean isSunkShip() {
        return sunkShip;
    }

    public void setSunkShip(boolean bool) {
        sunkShip = bool;
    }


    public boolean allDrowned() {
        return ships.isEmpty();
    }

    public boolean isShottedSetMapAndIsSunk(Point pointShot) {// tutaj skończyłem
        ArrayList<Point> listPointsOfShip;
        for (Ship ship : ships) {
            listPointsOfShip = ship.getPoints();
//            Map[pointShot.y][pointShot.x] = 'x';
            if (listPointsOfShip.contains(pointShot)) {
//                Map[pointShot.y][pointShot.x] = '1';
                listPointsOfShip.remove(pointShot);
                if (listPointsOfShip.isEmpty()) {
                    sunkShip = true;
                    ships.remove(ship);
                }
                return true;
            }
        }
        return false;
    }


    protected boolean isPointsExist(ArrayList<Point> points) {
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
