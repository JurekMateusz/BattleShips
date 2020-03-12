package com.ships.player;

import com.ships.ship.Ship;

import java.awt.*;
import java.util.ArrayList;

public class Player {
    private final char[] lettersInMap = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
    protected ArrayList<Ship> ships = new ArrayList<>();
    protected boolean sunkShip = false;
    protected int sizeMap;
    protected char[][] Map;

    Player(int sizeMap) {
        this.sizeMap = sizeMap;
        Map = new char[sizeMap][sizeMap];
        iniciateMap();
    }

    public boolean isSunkShip() {
        return sunkShip;
    }

    public void iniciateMap() {
        for (int i = 0; i < sizeMap; i++) {
            for (int j = 0; j < sizeMap; j++) {
                Map[i][j] = '-';
            }
        }
    }

    public void setSunkShip(boolean bool) {
        sunkShip = bool;
    }

    public char getElementMap(int x, int y) {
        return Map[x][y];
    }

    protected void addPointsToMap() {
        for (Ship ship : ships) {
            ArrayList<Point> arr = ship.getListOfPoints();
            for (Point point : arr) {
                Map[point.y][point.x] = '1';
            }
        }
    }

    public boolean allDrowned() {
        return ships.isEmpty();
    }

    protected void printMap() {
        System.out.print(System.lineSeparator() + "   ");
        for (int i = 1; i <= sizeMap; i++) {
            System.out.print(i + "  ");
        }

        for (int i = 0; i < sizeMap; i++) {
            System.out.print(System.lineSeparator() + lettersInMap[i] + "  ");
            for (int j = 0; j < sizeMap; j++) {
                System.out.print(Map[i][j] + "  ");
            }
        }
        System.out.println();
    }


    protected boolean isPointsExist(ArrayList<Point> points) {
        for (Ship v : ships) {
            ArrayList<Point> listPointsOfShip = v.getListOfPoints();
            for (Point point : points) {
                if (listPointsOfShip.contains(point)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isShottedSetMapAndIsSunk(Point pointShot) {// tutaj skończyłem
        ArrayList<Point> listPointsOfShip;
        for (Ship ship : ships) {
            listPointsOfShip = ship.getListOfPoints();
            Map[pointShot.y][pointShot.x] = 'x';
            if (listPointsOfShip.contains(pointShot)) {
                Map[pointShot.y][pointShot.x] = '1';
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
}
